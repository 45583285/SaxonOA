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
import sl.dao.DeptDaoI;
import sl.dao.RoleAuthDaoI;
import sl.dao.RoleDaoI;
import sl.dao.UserDaoI;
import sl.dao.UserRoleDaoI;
import sl.model.SysDept;
import sl.model.SysRole;
import sl.model.SysRoleAuth;
import sl.model.SysUser;
import sl.model.SysUserRole;
import sl.pageModel.DataGrid;
import sl.pageModel.User;
import sl.service.UserServiceI;
import sl.util.Encrypt;
@Service("userService")
public class UserServiceImpl implements UserServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);

	private UserDaoI userDao;
	private RoleDaoI roleDao;
	private AuthDaoI authDao;
	private UserRoleDaoI userRoleDao;
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
	public RoleDaoI getRoleDao() {
		return roleDao;
	}
	@Autowired
	public void setRoleDao(RoleDaoI roleDao) {
		this.roleDao = roleDao;
	}

	public UserRoleDaoI getUserRoleDao() {
		return userRoleDao;
	}
	@Autowired
	public void setUserRoleDao(UserRoleDaoI userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public UserDaoI getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	
	private DeptDaoI deptDao;
	public DeptDaoI getDeptDao() {
		return deptDao;
	}
	@Autowired
	public void setDeptDao(DeptDaoI deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public void save(User user) {
		SysUser u = new SysUser();
		BeanUtils.copyProperties(user, u, new String[] { "pwd" });
		u.setPwd(Encrypt.e(user.getPwd()));
		u.setUuid(UUID.randomUUID().toString());
		u.setCreateTime(new Date().toString());
		SysDept d  = new SysDept();
		if(user.getPid()!=null){
			d = deptDao.get("from SysDept t where t.uuid='"+user.getPid()+"'");
			u.setSysDept(d);
		}

		userDao.save(u);
	}

	@Override
	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name",user.getName() );
		params.put("pwd", Encrypt.e(user.getPwd()));
		SysUser u = userDao.get("from SysUser u where u.sysName = :name and u.pwd = :pwd", params);
		if (u != null) {
		//	logger.info("dddd12221"+u.getUuid().toString());
			List<SysUserRole> ur = userRoleDao.find("from SysUserRole t where t.sysUser.uuid='"+u.getUuid().toString()+"'");
		//	logger.info("dddd12221");
			String roleIds = "";
			String roleNames = "";
			String authIds = "";
			String authNames = "";
			String authUrls = "";
		//	logger.info("dddd12221");
			for (SysUserRole t : ur) {
				SysRole r = roleDao.get(SysRole.class, t.getSysRole().getUuid().toString()); 
				List<SysRoleAuth> ra =roleAuthDao.find("from SysRoleAuth t where t.sysRole.uuid='"+r.getUuid()+"'");
				for (SysRoleAuth a : ra) {
					if(authIds==""){
						authIds=a.getSysAuth().getUuid();
						authNames=a.getSysAuth().getName();
						authUrls = a.getSysAuth().getUrl();
					}else{
						authIds+=authIds+","+a.getSysAuth().getUuid();
						authNames=authNames+","+a.getSysAuth().getName();
						authUrls =authUrls +","+ a.getSysAuth().getUrl();
					}
				}
				if(roleIds==""){
					roleIds=t.getSysRole().getUuid();
					roleNames=t.getSysRole().getName();
				}else{
					roleIds+=roleIds+","+t.getSysRole().getUuid();
					roleNames+=roleNames+","+t.getSysRole().getName();
				}
				
				
			}
			user.setSysName(u.getSysName());
			user.setName(u.getName());
			user.setAuthIds(authIds);
			user.setAuthNames(authNames);
			user.setRoleNames(roleNames);
			user.setRoleIds(roleIds);
			user.setAuthUrls(authUrls);
		//	logger.info("dddd12221");
			return user;
		}
		return null;
	}
	
	
	@Override
	public DataGrid datagrid(User user) {
		DataGrid dg = new DataGrid();
		String hql = "from SysUser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(user, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(user, hql);
		List<SysUser> l = userDao.find(hql, params, user.getPage(), user.getRows());
		List<User> nl = new ArrayList<User>();
		changeModel(l, nl);
		dg.setTotal(userDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}
	private void changeModel(List<SysUser> l, List<User> nl) {
		if (l != null && l.size() > 0) {
			for (SysUser t : l) {
				User u = new User();
				BeanUtils.copyProperties(t, u, new String[] { "pwd" });
				if(t.getSysDept().getUuid()!=null && !t.getSysDept().getUuid().trim().equals("")){
					u.setDeptName(t.getSysDept().getName());
				}
				nl.add(u);
			}
		}
	}

	private String addOrder(User user, String hql) {
		if (user.getSort() != null) {
			hql += " order by " + user.getSort() + "+0 " + user.getOrder();
		}
		return hql;
	}

	private String addWhere(User user, String hql, Map<String, Object> params) {
		
		String temp;
		temp = "0";
		if((user.getName() != null && !user.getName().trim().equals("")) || (user.getUuid() != null && !user.getUuid().trim().equals(""))){
			hql += " where ";
		}
		
		if (user.getName() != null && !user.getName().trim().equals("")) {
			hql += "  t.name like :name";
			params.put("name", "%%" + user.getName().trim() + "%%");
			temp = "1";
		}
		

		
		if (user.getUuid() != null && !user.getUuid().trim().equals("")) {
			if(temp=="1"){
				hql += "  and ";
			}
			hql += "  t.sysDept.uuid like :uuid";
			params.put("uuid", "%%" + user.getUuid().trim() + "%%");
		}
		return hql;
	}
	
	
	
	@Override
	public void remove(String ids) {
		
	//	logger.info(ids);
		String[] nids = ids.split(",");
		String hql = "delete SysUser t where t.uuid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		userDao.executeHql(hql);
	}

	@Override
	public User getById(String uuid) {
		User user = new User();
		SysUser m = userDao.get("from SysUser t where t.uuid='"+uuid+"'");
		BeanUtils.copyProperties(m, user);
		if (m.getSysDept() != null) {
			user.setPid(m.getSysDept().getUuid().toString());
		}
		return user;
	}

	@Override
	public void update(User user) {
		SysUser d = new SysUser();
		BeanUtils.copyProperties(user, d);
		SysDept m1 = deptDao.get("from SysDept t where t.uuid='"+user.getPid()+"'");
		d.setSysDept(m1);
		userDao.update(d);
	}
	@Override
	public void userRole(User user) {
		SysUser m = userDao.get("from SysUser t where t.uuid='"
				+ user.getUuid() + "'");
		String[] nids = user.getIds().split(",");
		for (int i = 0; i < nids.length; i++) {
			SysUserRole ra = new SysUserRole();
			SysRole a1 = roleDao.get("from SysRole t where t.uuid='"
					+ nids[i].toString() + "'");
			ra.setUuid(UUID.randomUUID().toString());
			ra.setSysUser(m);
			ra.setSysRole(a1);
			userRoleDao.save(ra);
		}
	}
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> treeGrid(User o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
