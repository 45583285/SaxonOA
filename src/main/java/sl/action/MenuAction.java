package sl.action;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Json;
import sl.pageModel.Menu;
import sl.service.MenuServiceI;

import com.opensymphony.xwork2.ModelDriven;
@Namespace("/")
@Action(value = "menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu> {
	/**
	 * Logger for this class
	 */
	private Menu menu = new Menu();
	@Override
	public Menu getModel() {
		return menu;
	}
	private static final Logger logger = Logger.getLogger(MenuAction.class);
	private MenuServiceI menuService;
	public MenuServiceI getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}
	public void add() {
		
			Json j = new Json();
			try {
				menuService.save(menu);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			super.writeJson(j);
	}
	
	/**
	 * 异步获取树节点
	 */
	public void getTreeNode() {
		super.writeJson(menuService.getTreeNode(menu.getId()));
	}

	public void getAllTreeNode() {
		super.writeJson(menuService.getAllTreeNode());
	}

	
}
