package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Dept;
import sl.pageModel.Json;
import sl.pageModel.LoginInfo;
import sl.pageModel.Role;
import sl.pageModel.SessionInfo;
import sl.pageModel.User;
import sl.service.LoginInfoServiceI;
import sl.service.UserServiceI;
import sl.util.IpUtil;
import sl.util.ResourceUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "loginInfoAction", results = {
		@Result(name = "toList", location = "/admin/loginInfo/list.jsp"),
		@Result(name = "toAdd", location = "/admin/loginInfo/add.jsp"),
		@Result(name = "toEdit", location = "/admin/loginInfo/edit.jsp"),
		@Result(name = "toShow", location = "/admin/loginInfo/show.jsp")})
public class LoginInfoAction extends BaseAction implements ModelDriven<LoginInfo> {
	/**
	 * Logger for this class
	 */
	private LoginInfo loginInfo = new LoginInfo();

	@Override
	public LoginInfo getModel() {
		return loginInfo;
	}

	private static final Logger logger = Logger.getLogger(LoginInfoAction.class);

	private LoginInfoServiceI loginInfoService;
	


	public LoginInfoServiceI getLoginInfoService() {
		return loginInfoService;
	}


	@Autowired
	public void setLoginInfoService(LoginInfoServiceI loginInfoService) {
		this.loginInfoService = loginInfoService;
	}



		//跳转到页面strat
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

			return "toEdit";
		}

		// 查看页面
		public String toShow() {

			return "toShow";
		}
	
	//end
	
		// 返回普通列表
		public void dataGrid() {
			super.writeJson(loginInfoService.datagrid(loginInfo));
		}
		
	public void add() {

		Json j = new Json();
		try {
			loginInfoService.save(loginInfo);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}

	
	
}
