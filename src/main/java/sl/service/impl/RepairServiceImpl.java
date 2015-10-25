package sl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.MenuDaoI;
import sl.dao.UserDaoI;
import sl.model.SysMenu;
import sl.model.SysUser;
import sl.service.RepairServiceI;
import sl.util.Encrypt;

@Service("repairService")
public class RepairServiceImpl implements RepairServiceI {
	private MenuDaoI menuDao;

	public MenuDaoI getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(MenuDaoI menuDao) {
		this.menuDao = menuDao;
	}
	
	
	private UserDaoI userDao;

	public UserDaoI getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	
	
	@Override
	public void repair() {
		 repairMenu();
		 repairUser();
	}
	
	
	public void repairMenu() {
		SysMenu root = new SysMenu();
		root.setUuid("0");
		root.setText("首页");
		root.setUrl("");
		menuDao.saveOrUpdate(root);
		
		SysMenu xtgl = new SysMenu();
		xtgl.setUuid("xtgl");
		xtgl.setText("系统管理");
		xtgl.setSysMenu(root);
		xtgl.setUrl("");
		menuDao.saveOrUpdate(xtgl);
		
		SysMenu bmgl = new SysMenu();
		bmgl.setUuid("bmgl");
		bmgl.setText("部门管理");
		bmgl.setSysMenu(xtgl);
		bmgl.setUrl("/dept/list.jsp");
		menuDao.saveOrUpdate(bmgl);

		SysMenu yhgl = new SysMenu();
		yhgl.setUuid("yhgl");
		yhgl.setText("用户管理");
		yhgl.setUrl("/user/list.jsp");
		yhgl.setSysMenu(xtgl);
		menuDao.saveOrUpdate(yhgl);
		
		SysMenu jsgl = new SysMenu();
		jsgl.setUuid("jsgl");
		jsgl.setText("角色管理");
		jsgl.setSysMenu(xtgl);
		jsgl.setUrl("");
		menuDao.saveOrUpdate(jsgl);
		
		SysMenu qxgl = new SysMenu();
		qxgl.setUuid("qxgl");
		qxgl.setText("权限管理");
		qxgl.setSysMenu(xtgl);
		qxgl.setUrl("");
		menuDao.saveOrUpdate(qxgl);

		SysMenu cdgl = new SysMenu();
		cdgl.setUuid("cdgl");
		cdgl.setText("菜单管理");
		cdgl.setSysMenu(xtgl);
		cdgl.setUrl("");
		menuDao.saveOrUpdate(cdgl);
		
	}
	
	public void repairUser() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "admin");
		SysUser u = userDao.get("from SysUser u where u.sysName = :name and u.uuid != '0'", params);
		if(u !=null){
			u.setName(UUID.randomUUID().toString());
		}
		SysUser admin = new SysUser();
		admin.setUuid("0");
		admin.setSysName("admin");
		admin.setName("管理员");
		admin.setPwd(Encrypt.e("123456"));
		admin.setCreateTime(new Date().toString());
		userDao.saveOrUpdate(admin);
	}
	

}
