package sl.test;

import java.util.ArrayList;
import java.util.List;

public class WorkflowProcess implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7562490950926698731L;
	private String type;
	private String name;
	private String g;
	private String assignee;//assignType为0时，审批人
	private String expr;
	private String processType;
	private String processName;	
	private String assignValue;//assignType为0时，角色id
	
	private String assignType;//0:角色，1:指定审批人,2:发起人

	private String form;
	private List<WorkflowPath> paths=new ArrayList<WorkflowPath>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getProcessName() {
  	return processName;
  }

	public void setProcessName(String processName) {
  	this.processName = processName;
  }

	public List<WorkflowPath> getPaths() {
  	return paths;
  }

	public void setPaths(List<WorkflowPath> paths) {
  	this.paths = paths;
  }

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getAssignType() {
  	return assignType;
  }

	public void setAssignType(String assignType) {
  	this.assignType = assignType;
  }

	public String getAssignValue() {
  	return assignValue;
  }

	public void setAssignValue(String assignValue) {
  	this.assignValue = assignValue;
  }
	
	
}
