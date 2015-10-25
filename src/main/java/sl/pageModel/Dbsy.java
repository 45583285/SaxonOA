package sl.pageModel;

public class Dbsy implements java.io.Serializable  {
	private String id;
	private String name;
	private String createTime;
	private String assignee;
	private String moduleName;
	private String dealingPeople;
	private String title;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getDealingPeople() {
		return dealingPeople;
	}
	public void setDealingPeople(String dealingPeople) {
		this.dealingPeople = dealingPeople;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
