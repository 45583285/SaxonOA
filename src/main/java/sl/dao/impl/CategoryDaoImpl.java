package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.CategoryDaoI;
import sl.model.ShopCategory;
@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<ShopCategory> implements CategoryDaoI {

}
