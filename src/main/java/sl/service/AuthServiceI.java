package sl.service;

import java.util.List;

import sl.pageModel.Auth;
public interface AuthServiceI extends BaseServiceI<Auth>  {

	List<Auth> treeGridForRole(Auth auth);

/*	public void save(Auth auth);

	public DataGrid datagrid(Auth auth);

	public List<Auth>  getAllAuth();
	
	public void remove(String ids);
	
	public List<Auth> treeGrid(Auth auth);

	public Auth getById(String uuid);

	public void update(Auth auth);*/
	
}
