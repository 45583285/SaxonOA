package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Bulletin;
import sl.pageModel.Dept;
import sl.pageModel.Json;
import sl.service.BulletinServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "bulletinAction", results = {
		@Result(name = "toList", location = "/bulletin/list.jsp"),
		@Result(name = "toShow", location = "/bulletin/show.jsp"),
		@Result(name = "toEdit", location = "/bulletin/edit.jsp") ,
		@Result(name = "toAdd", location = "/bulletin/add.jsp")})
public class BulletinAction extends BaseAction implements ModelDriven<Bulletin> {
	/**
	 * Logger for this class
	 */
	private Bulletin bulletin = new Bulletin();

	@Override
	public Bulletin getModel() {
		return bulletin;
	}

	private static final Logger logger = Logger.getLogger(BulletinAction.class);
	private BulletinServiceI bulletinService;


	public BulletinServiceI getBulletinService() {
		return bulletinService;
	}


@Autowired
	public void setBulletinService(BulletinServiceI bulletinService) {
		this.bulletinService = bulletinService;
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
			Bulletin editObject = bulletinService.getById(bulletin.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "toEdit";
		}
		// 查看页面
		public String toShow() {
			Bulletin editObject = bulletinService.getById(bulletin.getUuid());
			ActionContext.getContext().getSession().put("editObject", editObject);
			return "toShow";
		}
		// 返回普通列表
		public void dataGrid() {
			super.writeJson(bulletinService.datagrid(bulletin));
		}

		// 新增页面
		public void add() {
			Json j = new Json();
			try {
				bulletinService.save(bulletin);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			super.writeJson(j);
			
		}
		
		//删除
		public void remove() {
			Json j = new Json();
			try {
			bulletinService.remove(bulletin.getIds());
			j.setSuccess(true);
			j.setMsg("删除成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			super.writeJson(j);
		}
		
		
		
		// 修改
	public void update() {
		Json j = new Json();
		try {
			bulletinService.update(bulletin);
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
}
