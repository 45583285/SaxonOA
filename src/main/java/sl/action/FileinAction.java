package sl.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Filein;
import sl.pageModel.Json;
import sl.service.FileinServiceI;
import sl.util.FileUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "fileinAction", results = {
		@Result(name = "toList", location = "/filein/list.jsp"),
		@Result(name = "toAdd", location = "/filein/add.jsp"),
		@Result(name = "toEdit", location = "/filein/edit.jsp"),
		@Result(name = "toShow", location = "/filein/show.jsp") })
public class FileinAction extends BaseAction implements ModelDriven<Filein> {
	/**
	 * Logger for this class
	 */
	private Filein filein = new Filein();

	@Override
	public Filein getModel() {
		return filein;
	}

	private static final Logger logger = Logger.getLogger(FileinAction.class);

	private FileinServiceI fileinService;

	public FileinServiceI getFileinService() {
		return fileinService;
	}

	@Autowired
	public void setFileinService(FileinServiceI fileinService) {
		this.fileinService = fileinService;
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
			Filein editObject = fileinService.getById(filein.getUuid());
			editObject.setProcessid(filein.getProcessid());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "toEdit";
		}
		
		public String getByTaskId(){
			Filein editObject = fileinService.getByTaskId(filein.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "toEdit";
		}
		

		// 查看页面
		public String toShow() {

			return "toShow";
		}
	
	//end
	
		// 返回普通列表
		public void dataGrid() {
			super.writeJson(fileinService.datagrid(filein));
		}

	
		public void fileUpload(){
			FileUtil fu = new FileUtil();
			//fu.fileUpload();
			
			super.writeJson(fu.fileUpload());
		}
		
	
	public void add() {
		Json j = new Json();
		try {
			fileinService.save(filein);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	public void submit() {

		Json j = new Json();
		try {
			fileinService.submit(filein);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	public void remove() {
		fileinService.remove(filein.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	
	// 修改
	public void update() {

	}
	
	public void queryHistoricActivityInstance(){
		super.writeJson(fileinService.queryHistoricActivityInstance(filein.getUuid()));
	}
	

}
