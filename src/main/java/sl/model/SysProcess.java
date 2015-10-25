package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysProcess entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_process", catalog = "saxon")
public class SysProcess implements java.io.Serializable {

	// Fields

	private String uuid;
	private String createTime;
	private String createUser;
	private String isDelete;
	private String processId;
	private String processName;
	private String sort;
	private String updateTime;
	private String updateUser;
	private String processInfo;
	

	// Constructors

	/** default constructor */
	public SysProcess() {
	}

	/** minimal constructor */
	public SysProcess(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysProcess(String uuid, String createTime, String createUser,
			String isDelete, String processId, String processName, String sort,
			String updateTime, String updateUser, String processInfo) {
		this.uuid = uuid;
		this.createTime = createTime;
		this.createUser = createUser;
		this.isDelete = isDelete;
		this.processId = processId;
		this.processName = processName;
		this.sort = sort;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.processInfo = processInfo;
	}

	// Property accessors
	@Id
	@Column(name = "UUID", unique = true, nullable = false, length = 50)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "CREATE_TIME", length = 50)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER", length = 50)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "IS_DELETE", length = 2)
	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "PROCESS_ID", length = 50)
	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	@Column(name = "PROCESS_NAME", length = 100)
	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Column(name = "SORT", length = 2)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "UPDATE_TIME", length = 50)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_USER", length = 50)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "PROCESS_INFO")
	public String getProcessInfo() {
		return this.processInfo;
	}

	public void setProcessInfo(String processInfo) {
		this.processInfo = processInfo;
	}

}