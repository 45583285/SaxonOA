package sl.pageModel;

public class BaseModel {
	private int page;
	private int rows;
	private String order;
	private String ids;
	private String sort;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	//流程信息
	private String next_task_user_sysName;
	public String getNext_task_user_sysName() {
		return next_task_user_sysName;
	}
	public void setNext_task_user_sysName(String next_task_user_sysName) {
		this.next_task_user_sysName = next_task_user_sysName;
	}
	
	

}
