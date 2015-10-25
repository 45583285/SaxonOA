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
 * SysMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_menu", catalog = "saxon")
public class SysMenu implements java.io.Serializable {

	// Fields

	private String uuid;
	private SysMenu sysMenu;
	private String iconcls;
	private String text;
	private String url;
	private Set<SysMenu> sysMenus = new HashSet<SysMenu>(0);

	// Constructors

	/** default constructor */
	public SysMenu() {
	}

	/** minimal constructor */
	public SysMenu(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysMenu(String uuid, SysMenu sysMenu, String iconcls, String text,
			String url, Set<SysMenu> sysMenus) {
		this.uuid = uuid;
		this.sysMenu = sysMenu;
		this.iconcls = iconcls;
		this.text = text;
		this.url = url;
		this.sysMenus = sysMenus;
	}

	// Property accessors
	@Id
	@Column(name = "UUID", unique = true, nullable = false, length = 36)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public SysMenu getSysMenu() {
		return this.sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	@Column(name = "ICONCLS", length = 50)
	public String getIconcls() {
		return this.iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	@Column(name = "TEXT", length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "URL", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysMenu")
	public Set<SysMenu> getSysMenus() {
		return this.sysMenus;
	}

	public void setSysMenus(Set<SysMenu> sysMenus) {
		this.sysMenus = sysMenus;
	}

}