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

import sl.dao.LoginInfoDaoI;
import sl.model.SysLoginInfo;
import sl.model.SysRole;
import sl.pageModel.DataGrid;
import sl.pageModel.LoginInfo;
import sl.pageModel.Role;
import sl.service.LoginInfoServiceI;
import sl.util.DateUtils;

@Service("loginInfoService")
public class LoginInfoServiceImpl extends BaseServiceImpl implements LoginInfoServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(LoginInfoServiceImpl.class);

	private LoginInfoDaoI loginInfoDao;
	

	public LoginInfoDaoI getLoginInfoDao() {
		return loginInfoDao;
	}
	@Autowired
	public void setLoginInfoDao(LoginInfoDaoI loginInfoDao) {
		this.loginInfoDao = loginInfoDao;
	}
	DateUtils du = new DateUtils();
	@Override
	public void save(LoginInfo o) {
		// TODO Auto-generated method stub
		SysLoginInfo u = new SysLoginInfo();
		BeanUtils.copyProperties(o, u);
		u.setUuid(UUID.randomUUID().toString());
		u.setCreateUser(getSession().getLoginNames());
		u.setCreateTime(du.getCurrentTime());
		loginInfoDao.save(u);
	}

	@Override
	public DataGrid datagrid(LoginInfo o) {
		DataGrid dg = new DataGrid();
		String hql = "from SysLoginInfo t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(o, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(o, hql);
		List<SysLoginInfo> l = loginInfoDao.find(hql, params, o.getPage(), o.getRows());
		List<LoginInfo> nl = new ArrayList<LoginInfo>();
		changeModel(l, nl);
		dg.setTotal(loginInfoDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	
	private void changeModel(List<SysLoginInfo> l, List<LoginInfo> nl) {
		if (l != null && l.size() > 0) {
			for (SysLoginInfo t : l) {
				LoginInfo u = new LoginInfo();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(LoginInfo li, String hql) {

		//if (li.getCreateTime()!= null) {
			hql += " order by CREATE_TIME desc";
			//hql += " order by " + li.getCreateTime()+li.getOrder();
		//}
		return hql;
	}

	private String addWhere(LoginInfo role, String hql, Map<String, Object> params) {
		
			hql += " where 1=1";

		return hql;
	}
	
	
	
	@Override
	public List<LoginInfo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LoginInfo> treeGrid(LoginInfo o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginInfo getById(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(LoginInfo o) {
		// TODO Auto-generated method stub
		
	}
	
	
}
