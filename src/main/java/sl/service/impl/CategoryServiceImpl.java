package sl.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.CategoryDaoI;
import sl.model.ShopCategory;
import sl.pageModel.Category;
import sl.pageModel.DataGrid;
import sl.service.CategoryServiceI;
import sl.util.DateUtils;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CategoryServiceImpl.class);
	
	private CategoryDaoI categoryDao;
	public CategoryDaoI getCategoryDao() {
		return categoryDao;
	}
	@Autowired
	public void setCategoryDao(CategoryDaoI categoryDao) {
		this.categoryDao = categoryDao;
	}
	DateUtils du = new DateUtils();
	@Override
	public void save(Category o) {
		ShopCategory u = new ShopCategory();
		BeanUtils.copyProperties(o, u);
		u.setUuid(UUID.randomUUID().toString());
		u.setCreateTime(du.getCurrentTime());
		categoryDao.save(u);
	}
	@Override
	public DataGrid datagrid(Category o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Category> treeGrid(Category o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Category getById(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(Category o) {
		// TODO Auto-generated method stub
		
	}

	

}
