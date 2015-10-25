package sl.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sl.pageModel.Dept;
import sl.pageModel.Json;
import sl.service.DeptServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "deptAction", results = {
		@Result(name = "toList", location = "/admin/dept/list.jsp"),
		@Result(name = "toAdd", location = "/admin/dept/add.jsp"),
		@Result(name = "toEdit", location = "/admin/dept/edit.jsp"),
		@Result(name = "toShow", location = "/admin/dept/show.jsp") })
public class DeptAction extends BaseAction implements ModelDriven<Dept> {
	/**
	 * Logger for this class
	 */
	private Dept dept = new Dept();

	@Override
	public Dept getModel() {
		return dept;
	}

	private static final Logger logger = Logger.getLogger(DeptAction.class);
	private DeptServiceI deptService;

	public DeptServiceI getDeptService() {
		return deptService;
	}

	@Autowired
	public void setDeptService(DeptServiceI deptService) {
		this.deptService = deptService;
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
		Dept editObject = deptService.getById(dept.getUuid());
		ActionContext.getContext().getSession().put("editObject", editObject);
		return "toEdit";
	}

	// 查看页面
	public String toShow() {
		Dept editObject = deptService.getById(dept.getUuid());
		ActionContext.getContext().getSession().put("editObject", editObject);
		return "toShow";
	}

	// 返回普通列表
	public void dataGrid() {
		super.writeJson(deptService.datagrid(dept));
	}

	// 返回树列表
	public void treeGrid() {
		super.writeJson(deptService.treeGrid(dept));
	}

	// 增加
	public void add() {

		Json j = new Json();
		try {
			deptService.save(dept);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}

	// 删除

	public void delete() {
		deptService.delete(dept.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	// 修改
	public void update() {
		deptService.update(dept);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		super.writeJson(j);
	}

	// 查看
	public void show() {

	}

	// 移除
	public void remove() {
		deptService.remove(dept.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	// 查询
	public void search() {

	}

	public void datagrid() {
		super.writeJson(deptService.datagrid(dept));
	}

	public void getTreeNode() {
		super.writeJson(deptService.getTreeNode());
	}

}
