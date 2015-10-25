package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Auth;
import sl.pageModel.Info;
import sl.pageModel.Json;
import sl.service.InfoServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "infoAction", results = {
		@Result(name = "toList", location = "/admin/info/list.jsp"),
		@Result(name = "toAdd", location = "/admin/info/add.jsp"),
		@Result(name = "toEdit", location = "/admin/info/edit.jsp"),
		@Result(name = "toShow", location = "/admin/info/show.jsp") })
public class InfoAction extends BaseAction implements ModelDriven<Info> {
	/**
	 * Logger for this class
	 */
	private Info info = new Info();

	@Override
	public Info getModel() {
		return info;
	}

	private static final Logger logger = Logger.getLogger(InfoAction.class);

	private InfoServiceI infoService;

	// 页面跳转---

	public InfoServiceI getInfoService() {
		return infoService;
	}

	@Autowired
	public void setInfoServiceI(InfoServiceI infoService) {
		this.infoService = infoService;
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
		Info editObject = infoService.getById("sysinfo");
		ActionContext.getContext().getSession().put("editObject", editObject);
//		logger.info(editObject.getUuid());
		return "toEdit";
	}

	// 查看页面
	public String toShow() {
	//	Auth editObject = authService.getById(auth.getUuid());
	//	ActionContext.getContext().getSession().put("editObject", editObject);
		return "toShow";
	}

	// 页面调整---end
	public void add() {



	}

	public void treeGrid() {
		// super.writeJson(authService.datagrid(auth));
		//super.writeJson(authService.treeGrid(auth));
	}
	//给角色选择权限
	public void treeGridForRole() {
		// super.writeJson(authService.datagrid(auth));
		//super.writeJson(authService.treeGridForRole(auth));
	}


	public void remove() {
		
	}

	// 修改
	public void update() {
		
	}
	
	
	
	
	
}
