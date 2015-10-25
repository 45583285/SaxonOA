package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysRoleAuth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_auth", catalog = "saxon")
public class SysRoleAuth implements java.io.Serializable {

	// Fields

	private String uuid;
	private SysRole sysRole;
	private SysAuth sysAuth;

	// Constructors

	/** default constructor */
	public SysRoleAuth() {
	}

	/** minimal constructor */
	public SysRoleAuth(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysRoleAuth(String uuid, SysRole sysRole, SysAuth sysAuth) {
		this.uuid = uuid;
		this.sysRole = sysRole;
		this.sysAuth = sysAuth;
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
	@JoinColumn(name = "AUTH_UUID")
	public SysAuth getSysAuth() {
		return this.sysAuth;
	}

	public void setSysAuth(SysAuth sysAuth) {
		this.sysAuth = sysAuth;
	}



}