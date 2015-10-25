package sl.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_dept", catalog = "saxon")
public class SysDept implements java.io.Serializable {

	// Fields

	private String uuid;
	private SysDept sysDept;
	private String createTime;
	private String createUser;
	private String description;
	private String isDelete;
	private String name;
	private String sort;
	private String updateTime;
	private String updateUser;
	private Set<SysDept> sysDepts = new HashSet<SysDept>(0);
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	// Constructors

	/** default constructor */
	public SysDept() {
	}

	/** minimal constructor */
	public SysDept(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysDept(String uuid, SysDept sysDept, String createTime,
			String createUser, String description, String isDelete,
			String name, String sort, String updateTime, String updateUser,
			Set<SysDept> sysDepts, Set<SysUser> sysUsers) {
		this.uuid = uuid;
		this.sysDept = sysDept;
		this.createTime = createTime;
		this.createUser = createUser;
		this.description = description;
		this.isDelete = isDelete;
		this.name = name;
		this.sort = sort;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.sysDepts = sysDepts;
		this.sysUsers = sysUsers;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUUID")
	public SysDept getSysDept() {
		return this.sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
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

	@Column(name = "DESCRIPTION", length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "IS_DELETE", length = 2)
	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SORT", length = 10)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysDept")
	public Set<SysDept> getSysDepts() {
		return this.sysDepts;
	}

	public void setSysDepts(Set<SysDept> sysDepts) {
		this.sysDepts = sysDepts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysDept")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

}