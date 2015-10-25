package sl.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Filein;
import sl.pageModel.Json;
import sl.service.DbsyServiceI;
import sl.service.FileinServiceI;
import sl.util.FileUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "dbsyAction", results = {
		@Result(name = "toList", location = "/admin/dbsy/list.jsp"),
		@Result(name = "toAdd", location = "/admin/dbsy/add.jsp"),
		@Result(name = "toEdit", location = "/admin/dbsy/edit.jsp"),
		@Result(name = "toShow", location = "/admin/dbsy/show.jsp") })
public class DbsyAction extends BaseAction {
	/**
	 * Logger for this class
	 */

	private static final Logger logger = Logger.getLogger(DbsyAction.class);

	private DbsyServiceI dbsyService;

	public DbsyServiceI getDbsyService() {
		return dbsyService;
	}

	@Autowired
	public void setDbsyService(DbsyServiceI dbsyService) {
		this.dbsyService = dbsyService;
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
			
			super.writeJson(dbsyService.datagrid());
		}

	
		public void fileUpload(){

		}
		
	
	public void add() {

	}
	public void submit() {

		 
	}
	
	public void remove() {

	}
	
	// 修改
	public void update() {

	}


	

}
