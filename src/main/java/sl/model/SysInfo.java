package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_info", catalog = "saxon")
public class SysInfo implements java.io.Serializable {

	// Fields

	private String uuid;
	private String sort;
	private String name;
	private String logo;
	private String contactPerson;
	private String serviceLine;
	private String telephone;
	private String fax;
	private String qq;
	private String zipCode;
	private String email;
	private String website;
	private String address;
	private String keyWord;
	private String archivalInfo;
	private String accessQuantity;
	private String copyrightInfo;
	private String remarks;
	private String isDelete;
	private String createUser;
	private String createTime;
	private String updateUser;
	private String updateTime;

	// Constructors

	/** default constructor */
	public SysInfo() {
	}

	/** minimal constructor */
	public SysInfo(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SysInfo(String uuid, String sort, String name, String logo,
			String contactPerson, String serviceLine, String telephone,
			String fax, String qq, String zipCode, String email,
			String website, String address, String keyWord,
			String archivalInfo, String accessQuantity, String copyrightInfo,
			String remarks, String isDelete, String createUser,
			String createTime, String updateUser, String updateTime) {
		this.uuid = uuid;
		this.sort = sort;
		this.name = name;
		this.logo = logo;
		this.contactPerson = contactPerson;
		this.serviceLine = serviceLine;
		this.telephone = telephone;
		this.fax = fax;
		this.qq = qq;
		this.zipCode = zipCode;
		this.email = email;
		this.website = website;
		this.address = address;
		this.keyWord = keyWord;
		this.archivalInfo = archivalInfo;
		this.accessQuantity = accessQuantity;
		this.copyrightInfo = copyrightInfo;
		this.remarks = remarks;
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

	@Column(name = "SORT", length = 50)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LOGO", length = 100)
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "CONTACT_PERSON", length = 50)
	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Column(name = "SERVICE_LINE", length = 50)
	public String getServiceLine() {
		return this.serviceLine;
	}

	public void setServiceLine(String serviceLine) {
		this.serviceLine = serviceLine;
	}

	@Column(name = "TELEPHONE", length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "FAX", length = 50)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "QQ", length = 50)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "ZIP_CODE", length = 20)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "WEBSITE", length = 100)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "KEY_WORD", length = 200)
	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Column(name = "ARCHIVAL_INFO", length = 50)
	public String getArchivalInfo() {
		return this.archivalInfo;
	}

	public void setArchivalInfo(String archivalInfo) {
		this.archivalInfo = archivalInfo;
	}

	@Column(name = "ACCESS_QUANTITY", length = 50)
	public String getAccessQuantity() {
		return this.accessQuantity;
	}

	public void setAccessQuantity(String accessQuantity) {
		this.accessQuantity = accessQuantity;
	}

	@Column(name = "COPYRIGHT_INFO", length = 50)
	public String getCopyrightInfo() {
		return this.copyrightInfo;
	}

	public void setCopyrightInfo(String copyrightInfo) {
		this.copyrightInfo = copyrightInfo;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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