package sl.service;

import java.util.List;

import sl.pageModel.Menu;

public interface MenuServiceI extends BaseServiceI<Menu> {
//	public void save(Menu menu);

	
	public List<Menu> getTreeNode(String uuid);

	public List<Menu> getAllTreeNode();

}
