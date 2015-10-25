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
import sl.dao.RoleDaoI;
import sl.model.SysAuth;
import sl.model.SysRole;
import sl.model.SysRoleAuth;
import sl.pageModel.Auth;
import sl.pageModel.DataGrid;
import sl.pageModel.Role;
import sl.service.RoleServiceI;
@Service("roleService")
public class RoleServiceImpl implements RoleServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(RoleServiceImpl.class);

	private RoleDaoI roleDao;
	private RoleAuthDaoI roleAuthDao;
	private AuthDaoI authDao;
	
	public AuthDaoI getAuthDao() {
		return authDao;
	}
	@Autowired
	public void setAuthDao(AuthDaoI authDao) {
		this.authDao = authDao;
	}
	public RoleAuthDaoI getRoleAuthDao() {
		return roleAuthDao;
	}
	@Autowired
	public void setRoleAuthDao(RoleAuthDaoI roleAuthDao) {
		this.roleAuthDao = roleAuthDao;
	}
	public RoleDaoI getRoleDao() {
		return roleDao;
	}
@Autowired
	public void setRoleDao(RoleDaoI roleDao) {
		this.roleDao = roleDao;
	}
@Override
public DataGrid datagrid(Role role) {

		DataGrid dg = new DataGrid();
		String hql = "from SysRole t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(role, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(role, hql);
		List<SysRole> l = roleDao.find(hql, params, role.getPage(), role.getRows());
		List<Role> nl = new ArrayList<Role>();
		changeModel(l, nl);
		dg.setTotal(roleDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
}

private void changeModel(List<SysRole> l, List<Role> nl) {
	if (l != null && l.size() > 0) {
		for (SysRole t : l) {
			Role u = new Role();
			BeanUtils.copyProperties(t, u);
			nl.add(u);
		}
	}
}

private String addOrder(Role role, String hql) {
	if (role.getSort() != null) {
		hql += " order by " + role.getSort() + "+0 " + role.getOrder();
	}
	return hql;
}

private String addWhere(Role role, String hql, Map<String, Object> params) {
	if (role.getName() != null && !role.getName().trim().equals("")) {
		hql += " where t.name like :name";
		params.put("name", "%%" + role.getName().trim() + "%%");
	}
	return hql;
}
@Override
public void save(Role role) {
	// TODO Auto-generated method stub
	SysRole u = new SysRole();
	BeanUtils.copyProperties(role, u);
	u.setUuid(UUID.randomUUID().toString());
	u.setCreateTime(new Date().toString());
	roleDao.save(u);
}
@Override
public Role getById(String uuid) {
	Role role = new Role();
	SysRole m = roleDao.get("from SysRole t where t.uuid='"+uuid+"'");
	BeanUtils.copyProperties(m, role);
	return role;
}
@Override
public void remove(String ids) {
	String[] nids = ids.split(",");
	String hql = "delete SysRole t where t.uuid in (";
	for (int i = 0; i < nids.length; i++) {
		if (i > 0) {
			hql += ",";
		}
		hql += "'" + nids[i] + "'";
	}
	hql += ")";
	roleDao.executeHql(hql);
}


@Override
public void update(Role role) {
	// TODO Auto-generated method stub
	SysRole d = new SysRole();
	BeanUtils.copyProperties(role, d);
	roleDao.update(d);
}
@Override
public void roleAuth(Role role) {
		SysRole m = roleDao.get("from SysRole t where t.uuid='"
				+ role.getUuid() + "'");
		String[] nids = role.getIds().split(",");
		
		
		String hql = "delete SysRoleAuth t where t.sysRole.uuid ='"+ role.getUuid() + "'";
		roleAuthDao.executeHql(hql);
		
		
		for (int i = 0; i < nids.length; i++) {
			SysRoleAuth ra = new SysRoleAuth();
			SysAuth a1 = authDao.get("from SysAuth t where t.uuid='"
					+ nids[i].toString() + "'");
			ra.setUuid(UUID.randomUUID().toString());
			ra.setSysRole(m);
			ra.setSysAuth(a1);
			roleAuthDao.save(ra);
		
	}
	
	
}
@Override
public List<Role> treeGrid(Role role) {
	List<Role> nl = new ArrayList<Role>();
	String hql = "from SysRole t";
	List<SysRole> l = roleDao.find(hql);
	if (l != null && l.size() > 0) {
		for (SysRole t : l) {
			Role m = new Role();
			BeanUtils.copyProperties(t, m);
			m.setId(t.getUuid());
			m.setText(t.getName());
			nl.add(m);
		}
	}
	return nl;
}
@Override
public List<Role> getAll() {
	// TODO Auto-generated method stub
	return null;
}


}
