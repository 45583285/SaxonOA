package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SxnContents entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sxn_contents", catalog = "saxon")
public class SxnContents implements java.io.Serializable {

	// Fields

	private String uuid;
	private SxnMenu sxnMenu;
	private String sort;
	private String title;
	private String origin;
	private String author;
	private String abstract_;
	private String image;
	private String details;
	private String isDelete;
	private String createUser;
	private String createTime;
	private String updateUser;
	private String updateTime;

	// Constructors

	/** default constructor */
	public SxnContents() {
	}

	/** minimal constructor */
	public SxnContents(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SxnContents(String uuid, SxnMenu sxnMenu, String sort, String title,
			String origin, String author, String abstract_, String image,
			String details, String isDelete, String createUser,
			String createTime, String updateUser, String updateTime) {
		this.uuid = uuid;
		this.sxnMenu = sxnMenu;
		this.sort = sort;
		this.title = title;
		this.origin = origin;
		this.author = author;
		this.abstract_ = abstract_;
		this.image = image;
		this.details = details;
		this.isDelete = isDelete;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
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
	@JoinColumn(name = "TYPE")
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

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ORIGIN", length = 50)
	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "AUTHOR", length = 50)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "ABSTRACT")
	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	@Column(name = "IMAGE", length = 100)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "DETAILS")
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
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

}