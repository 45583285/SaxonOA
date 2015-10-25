package sl.service;

import java.util.List;

import sl.pageModel.Auth;
import sl.pageModel.DataGrid;

public interface BaseServiceI<T> {
	
	public void save(T o);  //保存

	public DataGrid datagrid(T o); 

	public List<T>  getAll();
	
	public void remove(String ids);
	
	public List<T> treeGrid(T o);

	public T getById(String uuid);

	public void update(T o);

}
