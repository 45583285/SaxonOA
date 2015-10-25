package sl.service;

import java.util.List;

import sl.pageModel.DataGrid;
import sl.pageModel.Role;

public interface RoleServiceI extends BaseServiceI<Role> {

/*		public DataGrid datagrid(Role role);

		public void save(Role role);

		public Role getById(String uuid);

		public void remove(String ids);

		public void update(Role role);



		public List<Role> treeGrid(Role role);*/
		
		public void roleAuth(Role role);

}
