package sl.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysAuth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_auth", catalog = "saxon")
public class SysAuth implements java.io.Serializable {

	// Fields

	private String uuid;
	private SysAuth sysAuth;
	private Date createTime;
	private String createUser;
	private String description;
	private String iconcls;
	private String isDelete;
	private String name;
	private String sort;
	private String type;
	private Date updataTime;
	private String updataUser;
	private String url;
	private Set<SysAuth> sysAuths = new HashSet<SysAuth>(0);
	private Set<SysRoleAuth> sysRoleAuths = new HashSet<SysRoleAuth>(0);

	// Constructors

	/** default constructor */
	public SysAuth() {
	}

	/** minimal constructor */
	public SysAuth(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysAuth(String uuid, SysAuth sysAuth, Date createTime,
			String createUser, String description, String iconcls,
			String isDelete, String name, String sort, String type,
			Date updataTime, String updataUser, String url,
			Set<SysAuth> sysAuths, Set<SysRoleAuth> sysRoleAuths) {
		this.uuid = uuid;
		this.sysAuth = sysAuth;
		this.createTime = createTime;
		this.createUser = createUser;
		this.description = description;
		this.iconcls = iconcls;
		this.isDelete = isDelete;
		this.name = name;
		this.sort = sort;
		this.type = type;
		this.updataTime = updataTime;
		this.updataUser = updataUser;
		this.url = url;
		this.sysAuths = sysAuths;
		this.sysRoleAuths = sysRoleAuths;
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
	public SysAuth getSysAuth() {
		return this.sysAuth;
	}

	public void setSysAuth(SysAuth sysAuth) {
		this.sysAuth = sysAuth;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER", length = 100)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ICONCLS", length = 100)
	public String getIconcls() {
		return this.iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
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

	@Column(name = "SORT", length = 10)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATA_TIME", length = 0)
	public Date getUpdataTime() {
		return this.updataTime;
	}

	public void setUpdataTime(Date updataTime) {
		this.updataTime = updataTime;
	}

	@Column(name = "UPDATA_USER", length = 100)
	public String getUpdataUser() {
		return this.updataUser;
	}

	public void setUpdataUser(String updataUser) {
		this.updataUser = updataUser;
	}

	@Column(name = "URL", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysAuth")
	public Set<SysAuth> getSysAuths() {
		return this.sysAuths;
	}

	public void setSysAuths(Set<SysAuth> sysAuths) {
		this.sysAuths = sysAuths;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysAuth")
	public Set<SysRoleAuth> getSysRoleAuths() {
		return this.sysRoleAuths;
	}

	public void setSysRoleAuths(Set<SysRoleAuth> sysRoleAuths) {
		this.sysRoleAuths = sysRoleAuths;
	}

}