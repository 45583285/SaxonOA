package sl.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.InfoDaoI;
import sl.model.SysInfo;
import sl.pageModel.DataGrid;
import sl.pageModel.Info;
import sl.service.InfoServiceI;
@Service("infoService")
public class InfoServiceImpl implements InfoServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(InfoServiceImpl.class);
	private InfoDaoI infoDao;
	
	public InfoDaoI getInfoDao() {
		return infoDao;
	}
	@Autowired
	public void setInfoDao(InfoDaoI infoDao) {
		this.infoDao = infoDao;
	}

	@Override
	public void save(Info o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataGrid datagrid(Info o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Info> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Info> treeGrid(Info o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Info getById(String uuid) {
		Info info = new Info();
		SysInfo m = infoDao.get("from SysInfo t where t.uuid='"+uuid+"'");
		BeanUtils.copyProperties(m, info);
		return info;
	}

	@Override
	public void update(Info o) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
