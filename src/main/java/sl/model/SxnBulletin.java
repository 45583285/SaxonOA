package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SxnBulletin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sxn_bulletin", catalog = "saxon")
public class SxnBulletin implements java.io.Serializable {

	// Fields

	private String uuid;
	private String createTime;
	private String createUser;
	private String createUserDept;
	private String isDelete;
	private String main;
	private String sort;
	private String title;
	private String updateTime;
	private String updateUser;

	// Constructors

	/** default constructor */
	public SxnBulletin() {
	}

	/** minimal constructor */
	public SxnBulletin(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SxnBulletin(String uuid, String createTime, String createUser,
			String createUserDept, String isDelete, String main, String sort,
			String title, String updateTime, String updateUser) {
		this.uuid = uuid;
		this.createTime = createTime;
		this.createUser = createUser;
		this.createUserDept = createUserDept;
		this.isDelete = isDelete;
		this.main = main;
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

	@Column(name = "CREATE_USER_DEPT", length = 50)
	public String getCreateUserDept() {
		return this.createUserDept;
	}

	public void setCreateUserDept(String createUserDept) {
		this.createUserDept = createUserDept;
	}

	@Column(name = "IS_DELETE", length = 2)
	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "MAIN")
	public String getMain() {
		return this.main;
	}

	public void setMain(String main) {
		this.main = main;
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