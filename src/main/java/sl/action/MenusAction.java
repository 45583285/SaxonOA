package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Json;
import sl.pageModel.Menus;
import sl.service.MenusServiceI;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "menusAction", results = {
		@Result(name = "toList", location = "/menu/list.jsp"),
		@Result(name = "toAdd", location = "/menu/add.jsp"),
		@Result(name = "toEdit", location = "/menu/edit.jsp"),
		@Result(name = "toShow", location = "/menu/show.jsp") })
public class MenusAction extends BaseAction implements ModelDriven<Menus> {
	/**
	 * Logger for this class
	 */
	private Menus menus = new Menus();

	@Override
	public Menus getModel() {
		return menus;
	}

	private static final Logger logger = Logger.getLogger(MenusAction.class);

	private MenusServiceI menusService;


	public MenusServiceI getMenusService() {
		return menusService;
	}
	@Autowired
	public void setMenusService(MenusServiceI menusService) {
		this.menusService = menusService;
	}
	// 页面跳转---
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

	// 页面调整---end
	public void add() {

		Json j = new Json();
		try {
			menusService.save(menus);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}

	public void treeGrid() {
		// super.writeJson(authService.datagrid(auth));
		super.writeJson(menusService.treeGrid(menus));
	}
	public void getAllAuth() {
		super.writeJson(menusService.getAll());
	}

	public void remove() {
		menusService.remove(menus.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	// 修改
	public void update() {
		menusService.update(menus);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		super.writeJson(j);
	}
	
	
	
	
	
}
