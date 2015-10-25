package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user_role", catalog = "saxon")
public class SysUserRole implements java.io.Serializable {

	// Fields

	private String uuid;
	private SysRole sysRole;
	private SysUser sysUser;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}

	/** minimal constructor */
	public SysUserRole(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysUserRole(String uuid, SysRole sysRole, SysUser sysUser) {
		this.uuid = uuid;
		this.sysRole = sysRole;
		this.sysUser = sysUser;
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
	@JoinColumn(name = "ROLE_UUID")
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_UUID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}