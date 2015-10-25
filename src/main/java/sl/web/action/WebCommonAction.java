package sl.web.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Common;
import sl.pageModel.Contents;
import sl.pageModel.Menus;
import sl.service.ContentsServiceI;
import sl.service.MenusServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "webCommonAction", results = {
		@Result(name = "toCommon", location = "/webpc/common/common.jsp"),
		@Result(name = "toCommonInfo", location = "/webpc/common/commoninfo.jsp")
		})
public class WebCommonAction extends WebBaseAction implements ModelDriven<Common>  {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WebCommonAction.class);
	// 欄目信息
	private ContentsServiceI contentsService;
	public ContentsServiceI getContentsService() {
		return contentsService;
	}
	@Autowired
	public void setContentsService(ContentsServiceI contentsService) {
		this.contentsService = contentsService;
	}
	private MenusServiceI menusService;
	public MenusServiceI getMenusService() {
		return menusService;
	}
	@Autowired
	public void setMenusService(MenusServiceI menusService) {
		this.menusService = menusService;
	}
	private Common common = new Common();

	@Override
	public Common getModel() {
		return common;
	}
	public String toCommon() {
		Menus editObject = menusService.getById(common.getUuid());
		ActionContext.getContext().getSession().put("editObject", editObject);
		//super.writeJson(menusService.getById(common.getUuid()));
		return "toCommon";
	}
    //獲取內容
	public void getAll() {
		super.writeJson(contentsService.getAll());
	}
	//获取内容分页
	
	public void searchCommon() {
		super.writeJson(contentsService.searchCommon(common));

	}
	
	public void getCommonById(){
		super.writeJson(contentsService.getCommonById(common));
	}
	
	public String toCommonInfo() {
		Contents editObject = contentsService.getById(common.getUuid());
		ActionContext.getContext().getSession().put("contents", editObject);
		return "toCommonInfo";
	}
	
	public void getPages(){
		super.writeJson(contentsService.getPages(common));
	}

}
