package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Json;
import sl.pageModel.Role;
import sl.pageModel.SessionInfo;
import sl.pageModel.User;
import sl.service.RoleServiceI;
import sl.util.IpUtil;
import sl.util.ResourceUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "roleAction", results = {
		@Result(name = "toList", location = "/admin/role/list.jsp"),
		@Result(name = "toAdd", location = "/admin/role/add.jsp"),
		@Result(name = "toEdit", location = "/admin/role/edit.jsp"),
		@Result(name = "toShow", location = "/admin/role/show.jsp"),
		@Result(name = "checkAuth", location = "/admin/role/checkAuth.jsp") })
public class RoleAction extends BaseAction implements ModelDriven<Role> {
	/**
	 * Logger for this class
	 */
	private Role role = new Role();

	@Override
	public Role getModel() {
		return role;
	}

	private static final Logger logger = Logger.getLogger(RoleAction.class);

	private RoleServiceI roleService;



	public RoleServiceI getRoleService() {
		return roleService;
	}
@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

		//跳转到页面strat
	// 列表页面
		public String toList() {

			return "toList";
		}

		// 跳转到赋权页面
		public String checkAuth() {
			Role editObject = new Role();
			editObject.setUuid(role.getUuid().toString());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "checkAuth";
		}
		// 新增页面
		public String toAdd() {

			return "toAdd";
		}
		// 修改页面
		public String toEdit() {
			Role editObject = roleService.getById(role.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
//			logger.info(editObject.getUuid());
			return "toEdit";
		}

		// 查看页面
		public String toShow() {
			Role editObject = roleService.getById(role.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "toShow";
		}
	
	//end
	
		// 返回普通列表
		public void dataGrid() {
			super.writeJson(roleService.datagrid(role));
		}
		// 返回树列表
		public void treeGrid() {
			super.writeJson(roleService.treeGrid(role));
		}
		public void add() {

			Json j = new Json();
			try {
				roleService.save(role);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			super.writeJson(j);
		}
		
		public void remove() {
			roleService.remove(role.getIds());
			Json j = new Json();
			j.setSuccess(true);
			j.setMsg("删除成功！");
			super.writeJson(j);
		}
		
		// 修改
		public void update() {
			roleService.update(role);
			Json j = new Json();
			j.setSuccess(true);
			j.setMsg("修改成功！");
			super.writeJson(j);
		}
		
		//保存角色和权限的管理
		
		public void roleAuth(){
			roleService.roleAuth(role);
			Json j = new Json();
			j.setSuccess(true);
			j.setMsg("成功！");
			super.writeJson(j);
		}
		
		
}
