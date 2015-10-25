package sl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.AuthDaoI;
import sl.dao.RoleAuthDaoI;
import sl.model.SysAuth;
import sl.model.SysRoleAuth;
import sl.pageModel.Auth;
import sl.pageModel.DataGrid;
import sl.service.AuthServiceI;
@Service("authService")
public class AuthServiceImpl implements AuthServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(AuthServiceImpl.class);
	private AuthDaoI authDao;
	private RoleAuthDaoI roleAuthDao;
	public RoleAuthDaoI getRoleAuthDao() {
		return roleAuthDao;
	}
	@Autowired
	public void setRoleAuthDao(RoleAuthDaoI roleAuthDao) {
		this.roleAuthDao = roleAuthDao;
	}
	public AuthDaoI getAuthDao() {
		return authDao;
	}

	@Autowired
	public void setAuthDao(AuthDaoI authDao) {
		this.authDao = authDao;
	}

	@Override
	public void save(Auth auth) {
		SysAuth u = new SysAuth();
		BeanUtils.copyProperties(auth, u);
		u.setUuid(UUID.randomUUID().toString());
		if (auth.getPid() != null && !auth.getPid().trim().equals("")) {
			SysAuth a = authDao.get(" from SysAuth a where a.uuid='" + auth.getPid() +"'");
			u.setSysAuth(a);
		}
		u.setCreateTime(new Date());
		authDao.save(u);
	}


	@Override
	public DataGrid datagrid(Auth auth) {
		DataGrid dg = new DataGrid();
		String hql = "from SysAuth t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(auth, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(auth, hql);
		List<SysAuth> l = authDao.find(hql, params, auth.getPage(), auth.getRows());
		List<Auth> nl = new ArrayList<Auth>();
		changeModel(l, nl);
		dg.setTotal(authDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}
	private void changeModel(List<SysAuth> l, List<Auth> nl) {
		if (l != null && l.size() > 0) {
			for (SysAuth t : l) {
				Auth u = new Auth();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(Auth auth, String hql) {
		if (auth.getSort() != null) {
			hql += " order by " + auth.getSort() + "+0 " + auth.getOrder();
		}
		return hql;
	}

	private String addWhere(Auth auth, String hql, Map<String, Object> params) {
		if (auth.getUuid() != null && !auth.getUuid().trim().equals("")) {
			hql += " where t.name like :name";
			params.put("name", "%%" + auth.getName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public List<Auth> getAll() {
		
		List<Auth> nl = new ArrayList<Auth>();
		String hql = "from SysAuth t";
		List<SysAuth> l = authDao.find(hql);
		if (l != null && l.size() > 0) {
			for (SysAuth t : l) {
				Auth m = new Auth();
				BeanUtils.copyProperties(t, m);
				m.setId(t.getUuid());
				m.setText(t.getName());
				nl.add(m);
			}
		}
		return nl;
		
		
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete SysAuth t where t.uuid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		authDao.executeHql(hql);
	}

	@Override
	public  List<Auth> treeGrid(Auth auth) {
		List<Auth> nl = new ArrayList<Auth>();
		String hql = "from SysAuth t where t.sysAuth is null";
		List<SysAuth> l = authDao.find(hql);
		if (l != null && l.size() > 0) {
			for (SysAuth t : l) {
				Auth m = new Auth();
				BeanUtils.copyProperties(t, m);
				m.setId(t.getUuid());
				m.setText(t.getName());
				m.setIconCls(t.getIconcls());
			//	m.setChecked("true");
				String hql1 = "from SysAuth t  where t.sysAuth.uuid = '"+t.getUuid().toString()+"' order by sort+0 asc";
				List<Auth> nl1 = new ArrayList<Auth>();
				List<SysAuth> l1 = authDao.find(hql1);
				for (SysAuth t1 : l1) {
					Auth m1 = new Auth();
					BeanUtils.copyProperties(t1, m1);
					m1.setId(t1.getUuid());
					m1.setText(t1.getName());
					m1.setIconCls(t1.getIconcls());
				//	m1.setChecked("true");
					String hql2 = "from SysAuth t  where t.sysAuth.uuid = '"+t1.getUuid().toString()+"' order by sort+0 asc";
					List<Auth> nl2 = new ArrayList<Auth>();
					List<SysAuth> l2 = authDao.find(hql2);
					for (SysAuth t2 : l2) {
						Auth m2 = new Auth();
						BeanUtils.copyProperties(t2, m2);
						m2.setId(t2.getUuid());
						m2.setText(t2.getName());
						m2.setIconCls(t2.getIconcls());
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
	public Auth getById(String uuid) {
		// TODO Auto-generated method stub
		Auth auth = new Auth();
		SysAuth m = authDao.get("from SysAuth t where t.uuid='"+uuid+"'");
		BeanUtils.copyProperties(m, auth);
		if(m.getSysAuth()!=null){
			auth.setPid(m.getSysAuth().getUuid().toString());
		}
		return auth;
	}

	@Override
	public void update(Auth auth) {
		SysAuth d = new SysAuth();
		BeanUtils.copyProperties(auth, d);
		SysAuth m1 = authDao.get("from SysAuth t where t.uuid='"+auth.getPid()+"'");
		d.setSysAuth(m1);
		d.setUpdataTime(new Date());
		authDao.update(d);
		
	}
//提供给角色选权限
	
	@Override
	public  List<Auth> treeGridForRole(Auth auth) {
		List<Auth> nl = new ArrayList<Auth>();
		String hql = "from SysAuth t where t.sysAuth is null";
		List<SysAuth> l = authDao.find(hql);
		if (l != null && l.size() > 0) {
			for (SysAuth t : l) {
				Auth m = new Auth();
				BeanUtils.copyProperties(t, m);
				m.setId(t.getUuid());
				m.setText(t.getName());
				List<SysRoleAuth> checkl1 = roleAuthDao.find("from SysRoleAuth t where t.sysRole.uuid = '"
						+ auth.getUuid() + "' and t.sysAuth.uuid = '"+ t.getUuid() + "'");
			//	logger.info("checkl1.size():"+checkl1.size());
				if(checkl1.size()>0){
					m.setChecked("true");
				}
				
			//	m.setChecked("true");
				String hql1 = "from SysAuth t  where t.sysAuth.uuid = '"+t.getUuid().toString()+"' order by sort+0 asc";
				List<Auth> nl1 = new ArrayList<Auth>();
				List<SysAuth> l1 = authDao.find(hql1);
				for (SysAuth t1 : l1) {
					Auth m1 = new Auth();
					BeanUtils.copyProperties(t1, m1);
					m1.setId(t1.getUuid());
					m1.setText(t1.getName());
					List<SysRoleAuth> checkl2 = roleAuthDao.find("from SysRoleAuth t where t.sysRole.uuid = '"
							+ auth.getUuid() + "' and t.sysAuth.uuid = '"+ t1.getUuid() + "'");
				//	logger.info("checkl2.size():"+checkl2.size());
					if(checkl2.size()>0){
						m1.setChecked("true");
					}
				//	m1.setChecked("true");
					String hql2 = "from SysAuth t  where t.sysAuth.uuid = '"+t1.getUuid().toString()+"' order by sort+0 asc";
					List<Auth> nl2 = new ArrayList<Auth>();
					List<SysAuth> l2 = authDao.find(hql2);
					for (SysAuth t2 : l2) {
						Auth m2 = new Auth();
						BeanUtils.copyProperties(t2, m2);
						m2.setId(t2.getUuid());
						m2.setText(t2.getName());
						List<SysRoleAuth> checkl3 = roleAuthDao.find("from SysRoleAuth t where t.sysRole.uuid = '"
								+ auth.getUuid() + "' and t.sysAuth.uuid = '"+ t2.getUuid() + "'");
						//logger.info("checkl3.size():"+checkl3.size());
						if(checkl3.size()>0){
							m2.setChecked("true");
						}
					//	SysRoleAuth s = roleAuthDao.get("from SysRoleAuth t where t.sysAuth.uuid='"+t2.getUuid()+"'");
						
					//	logger.info(s.getUuid().toString()+"1");
/*						
						String s = roleAuthDao.get("from SysRoleAuth t where t.sysRole.uuid='"
								+ roleId + "' and t.sysAuth.uuid='"+ t2.getUuid() + "'").toString();
						
						
						logger.info(t2.getUuid());
						if(!s.equals("")){
							m2.setChecked("true");
						}
						*/
						
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
	
	
	
	
	

}
