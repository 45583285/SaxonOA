package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Contents;
import sl.pageModel.Json;
import sl.pageModel.LoginInfo;
import sl.pageModel.SessionInfo;
import sl.pageModel.User;
import sl.service.ContentsServiceI;
import sl.util.IpUtil;
import sl.util.ResourceUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "contentsAction", results = {
		@Result(name = "toList", location = "/contents/list.jsp"),
		@Result(name = "toAdd", location = "/contents/add.jsp"),
		@Result(name = "toEdit", location = "/contents/edit.jsp"),
		@Result(name = "toShow", location = "/contents/show.jsp") ,
		@Result(name = "checkRole", location = "/contents/checkRole.jsp") })
public class ContentsAction extends BaseAction implements ModelDriven<Contents> {
	/**
	 * Logger for this class
	 */
	private Contents contents = new Contents();

	@Override
	public Contents getModel() {
		return contents;
	}
	
	private static final Logger logger = Logger.getLogger(ContentsAction.class);

	private ContentsServiceI contentsService;

	

	public ContentsServiceI getContentsService() {
		return contentsService;
	}
	@Autowired
	public void setContentsService(ContentsServiceI contentsService) {
		this.contentsService = contentsService;
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
	//		User editObject = userService.getById(user.getUuid());
	//		ActionContext.getContext().getSession().put("editObject", editObject);
//			logger.info(editObject.getUuid());
			return "toEdit";
		}

		// 查看页面
		public String toShow() {
		//	User editObject = userService.getById(user.getUuid());
		//	ActionContext.getContext().getSession().put("editObject", editObject);
			return "toShow";
		}
	
	//end
	
		// 返回普通列表
		public void dataGrid() {
			super.writeJson(contentsService.datagrid(contents));
		}



	public void add() {

		Json j = new Json();
		try {
			contentsService.save(contents);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}

	public void datagrid() {
		//super.writeJson(userService.datagrid(user));
	}
	public void remove() {
	//	userService.remove(user.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	
	// 修改
	public void update() {
	//	userService.update(user);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		super.writeJson(j);
	}
	
	
	
}
