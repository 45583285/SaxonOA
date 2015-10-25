package sl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.MenuDaoI;
import sl.model.SysMenu;
import sl.pageModel.DataGrid;
import sl.pageModel.Menu;
import sl.service.MenuServiceI;
@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private MenuDaoI menuDao;

	public MenuDaoI getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(MenuDaoI menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public void save(Menu menu) {
		SysMenu m = new SysMenu();
		BeanUtils.copyProperties(menu, m);
		m.setUuid(UUID.randomUUID().toString());
		menuDao.save(m);
	}


	@Override
	public List<Menu> getTreeNode(String uuid) {
		List<Menu> nl = new ArrayList<Menu>();
		String hql = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (uuid == null || uuid.equals("")) {
			// 查询所有根节点
			hql = "from SysMenu m where m.sysMenu is null";
		} else {
			// 异步加载当前id下的子节点
			hql = "from SysMenu m where m.sysMenu.uuid = :uuid ";
			params.put("uuid", uuid);
		}
		List<SysMenu> l = menuDao.find(hql, params);
		if (l != null && l.size() > 0) {
			for (SysMenu t : l) {
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
		    	m.setId(t.getUuid());
		    //	m.setUrl(t.getUrl());
				Set<SysMenu> set = t.getSysMenus();
				if (set != null && !set.isEmpty()) {
					m.setState("closed");// 节点以文件夹的形式体现
				} else {
					m.setState("open");// 节点以文件的形式体现
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<Menu> getAllTreeNode() {
		List<Menu> nl = new ArrayList<Menu>();
		String hql = "from SysMenu t";
		List<SysMenu> l = menuDao.find(hql);
		if (l != null && l.size() > 0) {
			for (SysMenu t : l) {
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", t.getUrl());
				m.setId(t.getUuid());
				m.setAttributes(attributes);
				SysMenu tm = t.getSysMenu();// 获得当前节点的父节点对象
				if (tm != null) {
					m.setPid(tm.getUuid());
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public DataGrid datagrid(Menu o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Menu> treeGrid(Menu o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu getById(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Menu o) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
