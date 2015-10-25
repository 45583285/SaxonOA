package sl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

import sl.dao.BulletinDaoI;
import sl.model.SxnBulletin;
import sl.pageModel.Bulletin;
import sl.pageModel.DataGrid;
import sl.pageModel.SessionInfo;
import sl.service.BulletinServiceI;
import sl.util.DateUtils;
import sl.util.ResourceUtil;
@Service("bulletinService")
public class BulletinServiceImpl extends BaseServiceImpl implements BulletinServiceI  {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(BulletinServiceImpl.class);
	private BulletinDaoI bulletinDao;

	public BulletinDaoI getBulletinDao() {
		return bulletinDao;
	}

	@Autowired
	public void setBulletinDao(BulletinDaoI bulletinDao) {
		this.bulletinDao = bulletinDao;
	}
	DateUtils du = new DateUtils();
	@Override
	public void save(Bulletin bulletin) {
		SxnBulletin u = new SxnBulletin();
		BeanUtils.copyProperties(bulletin, u);
		u.setUuid(UUID.randomUUID().toString());
		u.setCreateTime(du.getCurrentTime());  //当前时间
		u.setCreateUser(bulletin.getCreateUser());   
		bulletinDao.save(u);
	}

	@Override
	public DataGrid datagrid(Bulletin bulletin) {

		DataGrid dg = new DataGrid();
		String hql = "from SxnBulletin t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(bulletin, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(bulletin, hql);
		List<SxnBulletin> l = bulletinDao.find(hql, params, bulletin.getPage(), bulletin.getRows());
		List<Bulletin> nl = new ArrayList<Bulletin>();
		changeModel(l, nl);
		dg.setTotal(bulletinDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}
	private void changeModel(List<SxnBulletin> l, List<Bulletin> nl) {
		if (l != null && l.size() > 0) {
			for (SxnBulletin t : l) {
				Bulletin u = new Bulletin();
				
				BeanUtils.copyProperties(t, u);
				u.setMain("");//因为json报错，所以将内容情况，有待后期完善
				nl.add(u);
			}
		}
	}

	private String addOrder(Bulletin bulletin, String hql) {
		if (bulletin.getSort() != null) {
			hql += " order by " + bulletin.getSort() + "+0 " + bulletin.getOrder();
		}
		return hql;
	}

	private String addWhere(Bulletin bulletin, String hql, Map<String, Object> params) {
		if (bulletin.getUuid() != null && !bulletin.getUuid().trim().equals("")) {
			hql += " where t.title like :name";
			params.put("name", "%%" + bulletin.getTitle().trim() + "%%");
		}
		return hql;
	}

	@Override
	public Bulletin getById(String uuid) {
		Bulletin Bulletin = new Bulletin();
		SxnBulletin m = bulletinDao.get("from SxnBulletin t where t.uuid='"+uuid+"'");
		BeanUtils.copyProperties(m, Bulletin);
		return Bulletin;
	}

	@Override
	public List<Bulletin> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		String[] nids = ids.split(",");
		String hql = "delete SxnBulletin t where t.uuid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		bulletinDao.executeHql(hql);
	}

	@Override
	public List<Bulletin> treeGrid(Bulletin o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Bulletin o) {
		// TODO Auto-generated method stub
		SxnBulletin b = new SxnBulletin();
		BeanUtils.copyProperties(o, b);
		b.setUpdateTime(du.getCurrentTime());
		bulletinDao.update(b);
	//	d.setUpdateTime(du.getCurrentTime());
	}


}
