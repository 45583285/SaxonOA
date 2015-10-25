package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SxnFilein entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sxn_filein", catalog = "saxon")
public class SxnFilein implements java.io.Serializable {

	// Fields

	private String uuid;
	private String createTime;
	private String createUser;
	private String isDelete;
	private String processid;
	private String sort;
	private String title;
	private String updateTime;
	private String updateUser;

	// Constructors

	/** default constructor */
	public SxnFilein() {
	}

	/** minimal constructor */
	public SxnFilein(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SxnFilein(String uuid, String createTime, String createUser,
			String isDelete, String processid, String sort, String title,
			String updateTime, String updateUser) {
		this.uuid = uuid;
		this.createTime = createTime;
		this.createUser = createUser;
		this.isDelete = isDelete;
		this.processid = processid;
		this.sort = sort;
		this.title = title;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
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

	@Column(name = "PROCESSID", length = 50)
	public String getProcessid() {
		return this.processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	@Column(name = "SORT", length = 10)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}