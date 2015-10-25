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
import sl.util.DateUtils;
import sl.util.IpUtil;
import sl.util.ResourceUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "userAction", results = {
		@Result(name = "toList", location = "/admin/user/list.jsp"),
		@Result(name = "toAdd", location = "/admin/user/add.jsp"),
		@Result(name = "toEdit", location = "/admin/user/edit.jsp"),
		@Result(name = "toShow", location = "/admin/user/show.jsp") ,
		@Result(name = "checkRole", location = "/admin/user/checkRole.jsp") })
public class UserAction extends BaseAction implements ModelDriven<User> {
	/**
	 * Logger for this class
	 */
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}
	
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
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
		
		// 跳转到赋权页面
		public String checkRole() {
			User editObject = new User();
			editObject.setUuid(user.getUuid().toString());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "checkRole";
		}

		// 新增页面
		public String toAdd() {

			return "toAdd";
		}

		// 修改页面
		public String toEdit() {
			User editObject = userService.getById(user.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
//			logger.info(editObject.getUuid());
			return "toEdit";
		}

		// 查看页面
		public String toShow() {
			User editObject = userService.getById(user.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "toShow";
		}
	
	//end
	
		// 返回普通列表
		public void dataGrid() {
			super.writeJson(userService.datagrid(user));
		}

	public void reg() {

		Json j = new Json();
		try {
			userService.save(user);
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}

	public void login() {
		//logger.info("dddddd");
		User u = userService.login(user);
	//	logger.info(user.getSysName());
	//	logger.info(user.getPwd());
		Json j = new Json();
		if (u != null) {
			//logger.info("11111");
			LoginInfo li = new LoginInfo();
			li.setInfo("登录");
			loginInfoService.save(li);
		//	logger.info("22222");
			j.setSuccess(true);
			j.setMsg("登陆成功！");
		} else {
			j.setMsg("登录失败，用户名或密码错误！");
		}

		super.writeJson(j);
	}
	
	
	public void doNotNeedSession_login() {
		Json j = new Json();
	//	logger.info("dddddd");
		User u = userService.login(user);
	//	logger.info("dddddd");
		if (u != null) {
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUserId(u.getUuid());
			sessionInfo.setLoginName(u.getSysName());
			sessionInfo.setLoginNames(u.getName());
			sessionInfo.setIp(IpUtil.getIpAddr(ServletActionContext.getRequest()));
			ServletActionContext.getRequest().getSession().setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
			sessionInfo.setAuthIds(u.getAuthIds());
			sessionInfo.setAuthNames(u.getAuthNames());
			sessionInfo.setRoleIds(u.getRoleIds());
			sessionInfo.setRoleNames(u.getRoleNames());
			sessionInfo.setAuthUrls(u.getAuthUrls());
		//	logger.info(u.getRoleNames());
			j.setObj(sessionInfo);
			LoginInfo li = new LoginInfo();
			li.setInfo("登录");
			li.setUserName(IpUtil.getIpAddr(ServletActionContext.getRequest()));
			loginInfoService.save(li);
			j.setMsg("登录成功！");
			j.setSuccess(true);
		} else {
			j.setSuccess(false);
			j.setMsg("登录失败！用户名或密码错误！");
		}
		super.writeJson(j);
	}
	
	public void doNotNeedSession_logout() {
		
		try {
			LoginInfo li = new LoginInfo();
			li.setInfo("退出");
			li.setUserName(IpUtil.getIpAddr(ServletActionContext.getRequest()));
			loginInfoService.save(li);
		} catch (Exception e) {
			// TODO: handle exception
		}
		ServletActionContext.getRequest().getSession().invalidate();
		Json j = new Json();
		j.setMsg("退出成功！");
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	public void add() {

		Json j = new Json();
		try {
			userService.save(user);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}

	public void datagrid() {
		super.writeJson(userService.datagrid(user));
	}
	public void remove() {
		userService.remove(user.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	
	// 修改
	public void update() {
		userService.update(user);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		super.writeJson(j);
	}
	
	//添加角色
	public void userRole(){
		
		userService.userRole(user);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("成功！");
		super.writeJson(j);
	}
	
	
}
