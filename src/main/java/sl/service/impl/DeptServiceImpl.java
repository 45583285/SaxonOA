package sl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.DeptDaoI;
import sl.model.SysDept;
import sl.pageModel.DataGrid;
import sl.pageModel.Dept;
import sl.service.DeptServiceI;
import sl.util.DateUtils;
@Service("deptService")
public class DeptServiceImpl implements DeptServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DeptServiceImpl.class);
	private static final Class<SysDept> SysDept = null;
	private DeptDaoI deptDao;

	public DeptDaoI getDeptDao() {
		return deptDao;
	}

	@Autowired
	public void setDeptDao(DeptDaoI deptDao) {
		this.deptDao = deptDao;
	}
	DateUtils du = new DateUtils();
	@Override
	public void save(Dept dept) {
		
		SysDept m = new SysDept();
		BeanUtils.copyProperties(dept, m);
		m.setUuid(UUID.randomUUID().toString());
		m.setCreateTime(du.getCurrentTime());  //当前时间
		m.setCreateUser(dept.getCreateUser());   
		if(dept.getPid() != null && !dept.getPid().trim().equals("")){
			SysDept sd = deptDao.get("from SysDept t where t.uuid='"+dept.getPid()+"'");
			m.setSysDept(sd);
		}
		deptDao.save(m);
	}
	@Override
	public DataGrid datagrid(Dept dept) {
		DataGrid dg = new DataGrid();
		String hql = "from SysDept t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(dept, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(dept, hql);
		List<SysDept> l = deptDao.find(hql, params, dept.getPage(), dept.getRows());
		List<Dept> nl = new ArrayList<Dept>();
		changeModel(l, nl);
		dg.setTotal(deptDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}
	private void changeModel(List<SysDept> l, List<Dept> nl) {
		if (l != null && l.size() > 0) {
			for (SysDept t : l) {
				Dept u = new Dept();
				BeanUtils.copyProperties(t, u);
			//	logger.info(t.getSysDept().getDeptName());
				
				if (t.getSysDept().getUuid() != null && !t.getSysDept().getUuid().trim().equals("")) {
					u.setPid(t.getSysDept().getName());
				}
				
				
				nl.add(u);
			}
		}
	}

	private String addOrder(Dept dept, String hql) {
		if (dept.getSort() != null) {
			hql += " order by " + dept.getSort() + "+0 " + dept.getOrder();
		}
		//logger.info(hql);
		
		
		return hql;
	}

	private String addWhere(Dept dept, String hql, Map<String, Object> params) {
		if (dept.getUuid() != null && !dept.getUuid().trim().equals("")) {
			hql += " where t.deptId like :name";
			params.put("name", "%%" + dept.getUuid().trim() + "%%");
		}
		return hql;
	}

	@Override
	public List<Dept> getTreeNode() {
		List<Dept> nl = new ArrayList<Dept>();
		String hql = "from SysDept t";
		List<SysDept> l = deptDao.find(hql);
		if (l != null && l.size() > 0) {
			for (SysDept t : l) {
				Dept m = new Dept();
				BeanUtils.copyProperties(t, m);
				m.setId(t.getUuid());
				m.setText(t.getName());
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<Dept> treeGrid(Dept dept) {
		List<Dept> nl = new ArrayList<Dept>();
		String hql = "from SysDept t where t.sysDept is null";
		List<SysDept> l = deptDao.find(hql);
		if (l != null && l.size() > 0) {
			for (SysDept t : l) {
				Dept m = new Dept();
				BeanUtils.copyProperties(t, m);
				m.setId(t.getUuid());
				m.setText(t.getName());
				String hql1 = "from SysDept t  where t.sysDept.uuid = '"+t.getUuid().toString()+"' order by sort+0 asc";
				//logger.info(hql1);
				List<Dept> nl1 = new ArrayList<Dept>();
				List<SysDept> l1 = deptDao.find(hql1);
				for (SysDept t1 : l1) {
					Dept m1 = new Dept();
					BeanUtils.copyProperties(t1, m1);
					m1.setId(t1.getUuid());
					m1.setText(t1.getName());
					String hql2 = "from SysDept t  where t.sysDept.uuid = '"+t1.getUuid().toString()+"' order by sort+0 asc";
					logger.info(hql2);
					List<Dept> nl2 = new ArrayList<Dept>();
					List<SysDept> l2 = deptDao.find(hql2);
					for (SysDept t2 : l2) {
						Dept m2 = new Dept();
						BeanUtils.copyProperties(t2, m2);
						m2.setId(t2.getUuid());
						m2.setText(t2.getName());
						nl2.add(m2);
					}
					m1.setChildren(nl2);
					nl1.add(m1);
				}
				m.setChildren(nl1);
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete SysDept t where t.uuid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		deptDao.executeHql(hql);		
	}

	@Override
	public Dept getById(String uuid) {
		// TODO Auto-generated method stub
		Dept dept = new Dept();
		SysDept m = deptDao.get("from SysDept t where t.uuid='"+uuid+"'");
		BeanUtils.copyProperties(m, dept);
		if(m.getSysDept()!=null){
			dept.setPid(m.getSysDept().getUuid().toString());
		}
		return dept;
	}

	@Override
	public void update(Dept dept) {
		SysDept d = new SysDept();
		BeanUtils.copyProperties(dept, d,new String[] { "create_user" });
		SysDept m1 = deptDao.get("from SysDept t where t.uuid='"+dept.getPid()+"'");
		d.setSysDept(m1);
		d.setUpdateTime(du.getCurrentTime());
		deptDao.update(d);
	}

	@Override
	public void delete(String ids) {
		SysDept m1 = deptDao.get("from SysDept t where t.uuid='"+ids+"'");
		deptDao.delete(m1);
		
	}

	@Override
	public List<Dept> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
