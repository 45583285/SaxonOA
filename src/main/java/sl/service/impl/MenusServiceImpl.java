package sl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.MenusDaoI;
import sl.model.SxnMenu;
import sl.pageModel.DataGrid;
import sl.pageModel.Menus;
import sl.service.MenusServiceI;
import sl.util.DateUtils;
@Service("menusService")
public class MenusServiceImpl implements MenusServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MenusServiceImpl.class);
	
	private MenusDaoI menusDao;

	public MenusDaoI getMenusDao() {
		return menusDao;
	}
	@Autowired
	public void setMenusDao(MenusDaoI menusDao) {
		this.menusDao = menusDao;
	}
	DateUtils du = new DateUtils();
	@Override
	public void save(Menus o) {
		// TODO Auto-generated method stub
		SxnMenu u = new SxnMenu();
		BeanUtils.copyProperties(o, u);
		u.setUuid(UUID.randomUUID().toString());
		if (o.getPid() != null && !o.getPid().trim().equals("")) {
			SxnMenu a = menusDao.get(" from SxnMenu a where a.uuid='" + o.getPid() +"'");
			u.setSxnMenu(a);
		}
		u.setCreateTime(du.getCurrentTime());
		menusDao.save(u);
	}

	@Override
	public DataGrid datagrid(Menus o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menus> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Menus> treeGrid(Menus o) {
		List<Menus> nl = new ArrayList<Menus>();
		String hql = "from SxnMenu t where t.sxnMenu is null order by sort+0 asc";
		List<SxnMenu> l = menusDao.find(hql);
		if (l != null && l.size() > 0) {
			for (SxnMenu t : l) {
				Menus m = new Menus();
				BeanUtils.copyProperties(t, m);
				m.setId(t.getUuid());
				m.setText(t.getMenuName());
			//	m.setPname(t.getSxnMenu().getMenuName());
				String hql1 = "from SxnMenu t  where t.sxnMenu.uuid = '"+t.getUuid().toString()+"' order by sort+0 asc";
				List<Menus> nl1 = new ArrayList<Menus>();
				List<SxnMenu> l1 = menusDao.find(hql1);
				for (SxnMenu t1 : l1) {
					Menus m1 = new Menus();
					BeanUtils.copyProperties(t1, m1);
					m1.setId(t1.getUuid());
					m1.setText(t1.getMenuName());
				//	m1.setPname(t1.getSxnMenu().getMenuName());
				//	m1.setChecked("true");			
					nl1.add(m1);
				}
				m.setChildren(nl1);
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public Menus getById(String uuid) {
		Menus menus = new Menus();
		SxnMenu m = menusDao.get("from SxnMenu t where t.uuid='"+uuid+"'");
		BeanUtils.copyProperties(m, menus);
		menus.setPname(m.getSxnMenu().getMenuName());
		menus.setPid(m.getSxnMenu().getUuid());
		return menus;
	}

	@Override
	public void update(Menus o) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
