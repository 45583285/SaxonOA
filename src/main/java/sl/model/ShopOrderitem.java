package sl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ShopOrderitem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_orderitem", catalog = "saxon")
public class ShopOrderitem implements java.io.Serializable {

	// Fields

	private String uuid;
	private ShopProduct shopProduct;
	private ShopOrders shopOrders;
	private String count;
	private String subTotle;
	private String isDelete;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;

	// Constructors

	/** default constructor */
	public ShopOrderitem() {
	}

	/** minimal constructor */
	public ShopOrderitem(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public ShopOrderitem(String uuid, ShopProduct shopProduct,
			ShopOrders shopOrders, String count, String subTotle,
			String isDelete, String createTime, String createUser,
			String updateTime, String updateUser) {
		this.uuid = uuid;
		this.shopProduct = shopProduct;
		this.shopOrders = shopOrders;
		this.count = count;
		this.subTotle = subTotle;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
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
	public ShopProduct getShopProduct() {
		return this.shopProduct;
	}

	public void setShopProduct(ShopProduct shopProduct) {
		this.shopProduct = shopProduct;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OUUID")
	public ShopOrders getShopOrders() {
		return this.shopOrders;
	}

	public void setShopOrders(ShopOrders shopOrders) {
		this.shopOrders = shopOrders;
	}

	@Column(name = "COUNT", length = 50)
	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Column(name = "SUB_TOTLE", length = 50)
	public String getSubTotle() {
		return this.subTotle;
	}

	public void setSubTotle(String subTotle) {
		this.subTotle = subTotle;
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

}