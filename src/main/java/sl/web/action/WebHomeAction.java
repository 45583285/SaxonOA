package sl.web.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Auth;
import sl.pageModel.Common;
import sl.pageModel.Info;
import sl.service.ContentsServiceI;
import sl.service.InfoServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "webHomeAction", results = {
		@Result(name = "toHome", location = "/webpc/index.jsp")
		})
public class WebHomeAction extends WebBaseAction  implements ModelDriven<Common>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WebHomeAction.class);
	// 欄目信息
	private ContentsServiceI contentsService;
	public ContentsServiceI getContentsService() {
		return contentsService;
	}
	@Autowired
	public void setContentsService(ContentsServiceI contentsService) {
		this.contentsService = contentsService;
	}
	private InfoServiceI infoService;

	// 系統信息

	public InfoServiceI getInfoService() {
		return infoService;
	}

	@Autowired
	public void setInfoServiceI(InfoServiceI infoService) {
		this.infoService = infoService;
	}
	public String toHome() {

		return "toHome";
	}
	
	private Common common = new Common();

	@Override
	public Common getModel() {
		return common;
	}
    //獲取內容
	public void getAll() {
		super.writeJson(contentsService.getAll());

	}
	
	public void getContentsByMenusId(){
		super.writeJson(contentsService.searchCommon(common));
	}
	
	//獲取系統信息
	public void getSysInfo() {
		Info editObject = infoService.getById("sysinfo");
		ActionContext.getContext().getSession().put("sysInfo", editObject);
	}
	
	
}
