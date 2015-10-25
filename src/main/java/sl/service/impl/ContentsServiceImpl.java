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

import sl.dao.ContentsDaoI;
import sl.dao.MenusDaoI;
import sl.model.SxnContents;
import sl.model.SxnMenu;
import sl.pageModel.Common;
import sl.pageModel.Contents;
import sl.pageModel.DataGrid;
import sl.pageModel.Menus;
import sl.service.ContentsServiceI;
import sl.util.DateUtils;
@Service("contentsService")
public class ContentsServiceImpl implements ContentsServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ContentsServiceImpl.class);
	private MenusDaoI menusDao;
	
	public MenusDaoI getMenusDao() {
		return menusDao;
	}
	@Autowired
	public void setMenusDao(MenusDaoI menusDao) {
		this.menusDao = menusDao;
	}
	private ContentsDaoI contentsDao;
	
	public ContentsDaoI getContentsDao() {
		return contentsDao;
	}
	@Autowired
	public void setContentsDao(ContentsDaoI contentsDao) {
		this.contentsDao = contentsDao;
	}
	DateUtils du = new DateUtils();
	@Override
	public void save(Contents o) {
		// TODO Auto-generated method stub
		SxnContents u = new SxnContents();
		BeanUtils.copyProperties(o, u);
		u.setUuid(UUID.randomUUID().toString());
/*		if (auth.getPid() != null && !auth.getPid().trim().equals("")) {
			SysAuth a = authDao.get(" from SysAuth a where a.uuid='" + auth.getPid() +"'");
			u.setSysAuth(a);
		}*/
		SxnMenu m = new SxnMenu();
		if(o.getType()!=null){
			m = menusDao.get("from SxnMenu t where t.uuid='"+o.getType()+"'");
			u.setSxnMenu(m);
		}
		u.setCreateTime(du.getCurrentTime());  //当前时间
		u.setCreateUser(o.getCreateUser());   
		logger.info(o.getAuthor());
		contentsDao.save(u);
	}

	@Override
	public DataGrid datagrid(Contents o) {
		DataGrid dg = new DataGrid();
		String hql = "from SxnContents t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(o, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(o, hql);
		List<SxnContents> l = contentsDao.find(hql, params, o.getPage(), o.getRows());
		List<Contents> nl = new ArrayList<Contents>();
		changeModel(l, nl);
		dg.setTotal(contentsDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}
	

	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Contents> treeGrid(Contents o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contents getById(String uuid) {
		// TODO Auto-generated method stub
		Contents contents = new Contents();
		SxnContents m = contentsDao.get("from SxnContents t where t.uuid='"+uuid+"'");
		BeanUtils.copyProperties(m, contents);
		return contents;
	}

	@Override
	public void update(Contents o) {
		// TODO Auto-generated method stub
		
	}
	
	//网站中的方法  ----------------------   start
	@Override
	public List<Contents> searchCommon(Common common) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from SxnContents t ";
		hql += " where t.sxnMenu.uuid like :type";
		params.put("type", "%%" + common.getUuid() + "%%");
		if (common.getSort() != null) {
			hql += " order by " + common.getSort() +" "+ common.getOrder();
		}
		logger.info(hql);
		List<SxnContents> l = contentsDao.find(hql, params, common.getPage(), common.getRows());
		List<Contents> nl = new ArrayList<Contents>();
		changeModel(l, nl);
		return nl;
	}
	
	
	public List<Menus> getCommonById(Common common) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from SxnMenu t where t.sxnMenu.uuid ='"+ common.getUuid()+"' order by sort";
	//	hql += " where t.puuid like :puuid";
	//	params.put("puuid", "%%" + common.getUuid() + "%%");
	//	hql += " order by sort ";
		List<SxnMenu> l = menusDao.find(hql);
		List<Menus> nl = new ArrayList<Menus>();
		changeModel1(l, nl);
		return nl;
	}
	
	private void changeModel1(List<SxnMenu> l, List<Menus> nl) {
		if (l != null && l.size() > 0) {
			for (SxnMenu t : l) {
				Menus u = new Menus();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}
	//网站中的方法  ----------------------  end
	//公用方法
	private void changeModel(List<SxnContents> l, List<Contents> nl) {
		if (l != null && l.size() > 0) {
			for (SxnContents t : l) {
				Contents u = new Contents();
				BeanUtils.copyProperties(t, u);
				u.setType(t.getSxnMenu().getMenuName());
				nl.add(u);
			}
		}
	}
	
	private String addOrder(Contents contents, String hql) {
		if (contents.getSort() != null) {
			hql += " order by " + contents.getSort() + "+0 " + contents.getOrder();
		}
		return hql;
	}
	
	private String addWhere(Contents contents, String hql, Map<String, Object> params) {
		
		hql += " where 1=1";
		
		if (contents.getUuid() != null && !contents.getUuid().trim().equals("")) {
			hql += "  and t.sxnMenu.uuid = '"+contents.getUuid().trim()+"'";
			
		}
		if (contents.getTitle() != null && !contents.getTitle().trim().equals("")) {
			hql += "  and t.title like '%%"+contents.getTitle().trim()+"%%'";
			
		}
		return hql;
	}

	@Override
	public List<Contents> getAll() {
		List<Contents> nl = new ArrayList<Contents>();
		String hql = "from SxnContents t";
		List<SxnContents> l = contentsDao.find(hql);
		if (l != null && l.size() > 0) {
			for (SxnContents t : l) {
				Contents m = new Contents();
				BeanUtils.copyProperties(t, m);
				nl.add(m);
			}
		}
		return nl;
	}
	@Override
	public Common getPages(Common common) {
		Common c = new Common();
		Long m = contentsDao.count("select count(*) from SxnContents t where t.sxnMenu.uuid='"+common.getUuid()+"'");
		int n = (int) ((m%common.getRows()==0)?m/common.getRows():(m/common.getRows()+1));
		logger.info(common.getRows());
		if(n<=1){
			n=(int) 1;
		}
		
		logger.info(n);
		c.setPages(n+"");
		return c;
	}

	
	
}
