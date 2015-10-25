package sl.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role", catalog = "saxon")
public class SysRole implements java.io.Serializable {

	// Fields

	private String uuid;
	private String createTime;
	private String createUser;
	private String isDelete;
	private String name;
	private String sort;
	private String updateTime;
	private String updateUser;
	private Set<SysRoleAuth> sysRoleAuths = new HashSet<SysRoleAuth>(0);
	private Set<SysUserRole> sysUserRoles = new HashSet<SysUserRole>(0);

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** minimal constructor */
	public SysRole(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysRole(String uuid, String createTime, String createUser,
			String isDelete, String name, String sort, String updateTime,
			String updateUser, Set<SysRoleAuth> sysRoleAuths,
			Set<SysUserRole> sysUserRoles) {
		this.uuid = uuid;
		this.createTime = createTime;
		this.createUser = createUser;
		this.isDelete = isDelete;
		this.name = name;
		this.sort = sort;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.sysRoleAuths = sysRoleAuths;
		this.sysUserRoles = sysUserRoles;
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

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysRoleAuth> getSysRoleAuths() {
		return this.sysRoleAuths;
	}

	public void setSysRoleAuths(Set<SysRoleAuth> sysRoleAuths) {
		this.sysRoleAuths = sysRoleAuths;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysUserRole> getSysUserRoles() {
		return this.sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

}