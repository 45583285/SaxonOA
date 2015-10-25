package sl.service;
import sl.pageModel.User;
public interface UserServiceI extends BaseServiceI<User> {
/*	public void save(User user);

	

	public DataGrid datagrid(User user);

	public void remove(String ids);

	public User getById(String uuid);

	public void update(User user);*/

	public User login(User user);
	
	public void userRole(User user);

}
