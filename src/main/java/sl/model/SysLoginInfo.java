package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysLoginInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_login_info", catalog = "saxon")
public class SysLoginInfo implements java.io.Serializable {

	// Fields

	private String uuid;
	private String userName;
	private String info;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String isDelete;

	// Constructors

	/** default constructor */
	public SysLoginInfo() {
	}

	/** minimal constructor */
	public SysLoginInfo(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysLoginInfo(String uuid, String userName, String info,
			String createTime, String createUser, String updateTime,
			String updateUser, String isDelete) {
		this.uuid = uuid;
		this.userName = userName;
		this.info = info;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.isDelete = isDelete;
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

	@Column(name = "USER_NAME", length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "INFO", length = 100)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
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

	@Column(name = "IS_DELETE", length = 2)
	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

}