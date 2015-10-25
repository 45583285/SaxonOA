package sl.service;

import java.util.List;

import sl.pageModel.Bulletin;
import sl.pageModel.DataGrid;
import sl.pageModel.Dept;
import sl.pageModel.Menu;

public interface DeptServiceI extends BaseServiceI<Dept> {
	
/*	public void save(Dept dept);

	public DataGrid datagrid(Dept dept);*/

	public List<Dept> getTreeNode();
	
	public List<Dept> treeGrid(Dept dept);
	//public void delete(String ids);
/*	public void remove(String ids);

	public Dept getById(String uuid);

	public void update(Dept dept);

	public void delete(String ids);*/
	public void delete(String ids);

}
