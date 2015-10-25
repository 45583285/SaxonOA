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
 * SxnMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sxn_menu", catalog = "saxon")
public class SxnMenu implements java.io.Serializable {

	// Fields

	private String uuid;
	private SxnMenu sxnMenu;
	private String sort;
	private String menuName;
	private String menuUrl;
	private String isDelete;
	private String createUser;
	private String createTime;
	private String updateUser;
	private String updateTime;
	private Set<SxnMenu> sxnMenus = new HashSet<SxnMenu>(0);
	private Set<SxnContents> sxnContentses = new HashSet<SxnContents>(0);

	// Constructors

	/** default constructor */
	public SxnMenu() {
	}

	/** minimal constructor */
	public SxnMenu(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SxnMenu(String uuid, SxnMenu sxnMenu, String sort, String menuName,
			String menuUrl, String isDelete, String createUser,
			String createTime, String updateUser, String updateTime,
			Set<SxnMenu> sxnMenus, Set<SxnContents> sxnContentses) {
		this.uuid = uuid;
		this.sxnMenu = sxnMenu;
		this.sort = sort;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.isDelete = isDelete;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.sxnMenus = sxnMenus;
		this.sxnContentses = sxnContentses;
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
	public SxnMenu getSxnMenu() {
		return this.sxnMenu;
	}

	public void setSxnMenu(SxnMenu sxnMenu) {
		this.sxnMenu = sxnMenu;
	}

	@Column(name = "SORT", length = 50)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "MENU_NAME", length = 100)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_URL", length = 100)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "IS_DELETE", length = 2)
	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "CREATE_USER", length = 50)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "CREATE_TIME", length = 50)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_USER", length = 50)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "UPDATE_TIME", length = 50)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sxnMenu")
	public Set<SxnMenu> getSxnMenus() {
		return this.sxnMenus;
	}

	public void setSxnMenus(Set<SxnMenu> sxnMenus) {
		this.sxnMenus = sxnMenus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sxnMenu")
	public Set<SxnContents> getSxnContentses() {
		return this.sxnContentses;
	}

	public void setSxnContentses(Set<SxnContents> sxnContentses) {
		this.sxnContentses = sxnContentses;
	}

}