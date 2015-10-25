package sl.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sl.service.RepairServiceI;

@Namespace("/")
@Action(value = "repairAction")
public class RepairAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private RepairServiceI repairService;

	public RepairServiceI getRepairService() {
		return repairService;
	}
@Autowired
	public void setRepairService(RepairServiceI repairService) {
		this.repairService = repairService;
	}

	public void init(){
		repairService.repair();
	}
}
