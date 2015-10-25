package sl.pageModel;

import java.util.Date;

import sl.model.SysDept;

public class User  extends BaseModel implements java.io.Serializable {
	
	
	
	private String pid;
	private String deptName;
	private String authIds;
	private String AuthNames;
	private String RoleIds;
	private String RoleNames;
	private String AuthUrls;
	
	public String getAuthIds() {
		return authIds;
	}
	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}
	public String getAuthNames() {
		return AuthNames;
	}
	public void setAuthNames(String authNames) {
		AuthNames = authNames;
	}
	public String getRoleIds() {
		return RoleIds;
	}
	public void setRoleIds(String roleIds) {
		RoleIds = roleIds;
	}
	public String getRoleNames() {
		return RoleNames;
	}
	public void setRoleNames(String roleNames) {
		RoleNames = roleNames;
	}
	public String getAuthUrls() {
		return AuthUrls;
	}
	public void setAuthUrls(String authUrls) {
		AuthUrls = authUrls;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	private String uuid;
	private String sysName;
	private String name;
	private String pwd;
	private String sort;
	private String birthdate;
	private String mobilephone;
	private String officephone;
	private String state;
	private String homeAddress;
	private String email;
	private String isAdmin;
	private String isDelete;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;


	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
}
