package sl.util;

public class WorkflowPath {
	private String pathName;
	private String id;
	private String from;
	private String to;
	private String ids;
	private String name;
	private String g;
	
	
	private String assignee;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFrom() {
  	return from;
  }
	public void setFrom(String from) {
  	this.from = from;
  }
	public String getPathName() {
  	return pathName;
  }
	public void setPathName(String pathName) {
  	this.pathName = pathName;
  }
	public String getTo() {
  	return to;
  }
	public void setTo(String to) {
  	this.to = to;
  }
	public String getName() {
  	return name;
  }
	public void setName(String name) {
  	this.name = name;
  }
	public String getG() {
  	return g;
  }
	public void setG(String g) {
  	this.g = g;
  }
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	
}
