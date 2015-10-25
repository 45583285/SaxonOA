package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Auth;
import sl.pageModel.Json;
import sl.pageModel.User;
import sl.service.AuthServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "authAction", results = {
		@Result(name = "toList", location = "/admin/auth/list.jsp"),
		@Result(name = "toAdd", location = "/admin/auth/add.jsp"),
		@Result(name = "toEdit", location = "/admin/auth/edit.jsp"),
		@Result(name = "toShow", location = "/admin/auth/show.jsp") })
public class AuthAction extends BaseAction implements ModelDriven<Auth> {
	/**
	 * Logger for this class
	 */
	private Auth auth = new Auth();

	@Override
	public Auth getModel() {
		return auth;
	}

	private static final Logger logger = Logger.getLogger(AuthAction.class);

	private AuthServiceI authService;

	// 页面跳转---

	public AuthServiceI getAuthService() {
		return authService;
	}

	@Autowired
	public void setAuthService(AuthServiceI authService) {
		this.authService = authService;
	}

	// 列表页面
	public String toList() {

		return "toList";
	}

	// 新增页面
	public String toAdd() {

		return "toAdd";
	}

	// 修改页面
	public String toEdit() {
		Auth editObject = authService.getById(auth.getUuid());
		ActionContext.getContext().getSession().put("editObject", editObject);
//		logger.info(editObject.getUuid());
		return "toEdit";
	}

	// 查看页面
	public String toShow() {
		Auth editObject = authService.getById(auth.getUuid());
		ActionContext.getContext().getSession().put("editObject", editObject);
		return "toShow";
	}

	// 页面调整---end
	public void add() {

		Json j = new Json();
		try {
			authService.save(auth);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}

	public void treeGrid() {
		// super.writeJson(authService.datagrid(auth));
		super.writeJson(authService.treeGrid(auth));
	}
	//给角色选择权限
	public void treeGridForRole() {
		// super.writeJson(authService.datagrid(auth));
		super.writeJson(authService.treeGridForRole(auth));
	}
	public void getAllAuth() {
		super.writeJson(authService.getAll());
	}

	public void remove() {
		authService.remove(auth.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	// 修改
	public void update() {
		authService.update(auth);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		super.writeJson(j);
	}
	
	
	
	
	
}
