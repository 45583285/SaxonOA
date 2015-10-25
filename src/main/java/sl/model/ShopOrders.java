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
 * ShopOrders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_orders", catalog = "saxon")
public class ShopOrders implements java.io.Serializable {

	// Fields

	private String uuid;
	private String total;
	private String orderTime;
	private String state;
	private String name;
	private String phone;
	private String addr;
	private String uuuid;
	private String isDelete;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private Set<ShopOrderitem> shopOrderitems = new HashSet<ShopOrderitem>(0);

	// Constructors

	/** default constructor */
	public ShopOrders() {
	}

	/** minimal constructor */
	public ShopOrders(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public ShopOrders(String uuid, String total, String orderTime,
			String state, String name, String phone, String addr, String uuuid,
			String isDelete, String createTime, String createUser,
			String updateTime, String updateUser,
			Set<ShopOrderitem> shopOrderitems) {
		this.uuid = uuid;
		this.total = total;
		this.orderTime = orderTime;
		this.state = state;
		this.name = name;
		this.phone = phone;
		this.addr = addr;
		this.uuuid = uuuid;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.shopOrderitems = shopOrderitems;
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

	@Column(name = "TOTAL", length = 100)
	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Column(name = "ORDER_TIME", length = 50)
	public String getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	@Column(name = "STATE", length = 50)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PHONE", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ADDR", length = 200)
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "UUUID", length = 50)
	public String getUuuid() {
		return this.uuuid;
	}

	public void setUuuid(String uuuid) {
		this.uuuid = uuuid;
	}

	@Column(name = "IS_DELETE", length = 2)
	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shopOrders")
	public Set<ShopOrderitem> getShopOrderitems() {
		return this.shopOrderitems;
	}

	public void setShopOrderitems(Set<ShopOrderitem> shopOrderitems) {
		this.shopOrderitems = shopOrderitems;
	}

}