package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Json;
import sl.pageModel.Process;
import sl.pageModel.User;
import sl.service.ProcessServiceI;
import sl.util.JsonToXML;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "processAction", results = {
		@Result(name = "toList", location = "/admin/process/list.jsp"),
		@Result(name = "toAdd", location = "/admin/process/add.jsp"),
		@Result(name = "toEdit", location = "/admin/process/edit.jsp"),
		@Result(name = "editFlow", location = "/admin/process/editFlow.jsp"),
		@Result(name = "showFlow", location = "/admin/process/showFlow.jsp"),
		@Result(name = "toShow", location = "/admin/process/show.jsp") })
public class ProcessAction extends BaseAction implements ModelDriven<Process> {
	/**
	 * Logger for this class
	 */
	private Process process = new Process();

	@Override
	public Process getModel() {
		return process;
	}

	private static final Logger logger = Logger.getLogger(ProcessAction.class);

	private ProcessServiceI processService;


	public ProcessServiceI getProcessService() {
		return processService;
	}
	@Autowired
	public void setProcessService(ProcessServiceI processService) {
		this.processService = processService;
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
			Process editObject = processService.getById(process.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
			
			return "toEdit";
		}

		// 查看页面
		public String toShow() {
			
			return "toShow";
		}
		
		// 编辑流程
		public String editFlow() {
			Process editObject = processService.getById(process.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "editFlow";
		}
		
		// 编辑流程
		public String showFlow() {
			Process editObject = processService.getById(process.getUuid());//方法需要换成根据流程ID查找
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "showFlow";
		}
	
	//end
	
		// 返回普通列表
		public void dataGrid() {
			super.writeJson(processService.datagrid(process));
		}

	
	
	public void add() {

		Json j = new Json();
		try {
			processService.save(process);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}

	
	public void remove() {
		processService.remove(process.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	
	// 修改
	public void update() {
/*		logger.info("111:"+process.getUuid());
		logger.info(process.getProcessInfo());
		JsonToXML jx = new JsonToXML();
		logger.info(jx.toXml(process.getProcessInfo()));*/
	}
	
	
	// 修改流程
	public void flowInfo() {
/*		logger.info("111:"+process.getUuid());
		logger.info(process.getProcessInfo());
		JsonToXML jx = new JsonToXML();
		logger.info(jx.toXml(process.getProcessInfo()));*/
		
		Json j = new Json();
		try {
			processService.update(process);
			j.setSuccess(true);
			j.setMsg("部署成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	

	
	
}
