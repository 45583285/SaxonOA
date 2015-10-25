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
 * ShopProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_product", catalog = "saxon")
public class ShopProduct implements java.io.Serializable {

	// Fields

	private String uuid;
	private ShopCategory shopCategory;
	private String name;
	private String marketPrice;
	private String shopPrice;
	private String image;
	private String desc;
	private String isHot;
	private String update;
	private String isDelete;
	private String createTime;
	private String updateTime;
	private String createUser;
	private String updateUser;
	private Set<ShopOrderitem> shopOrderitems = new HashSet<ShopOrderitem>(0);

	// Constructors

	/** default constructor */
	public ShopProduct() {
	}

	/** minimal constructor */
	public ShopProduct(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public ShopProduct(String uuid, ShopCategory shopCategory, String name,
			String marketPrice, String shopPrice, String image, String desc,
			String isHot, String update, String isDelete, String createTime,
			String updateTime, String createUser, String updateUser,
			Set<ShopOrderitem> shopOrderitems) {
		this.uuid = uuid;
		this.shopCategory = shopCategory;
		this.name = name;
		this.marketPrice = marketPrice;
		this.shopPrice = shopPrice;
		this.image = image;
		this.desc = desc;
		this.isHot = isHot;
		this.update = update;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUser = createUser;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUUID")
	public ShopCategory getShopCategory() {
		return this.shopCategory;
	}

	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MARKET_PRICE", length = 50)
	public String getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	@Column(name = "SHOP_PRICE", length = 50)
	public String getShopPrice() {
		return this.shopPrice;
	}

	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}

	@Column(name = "IMAGE", length = 225)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "DESC", length = 225)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "IS_HOT", length = 2)
	public String getIsHot() {
		return this.isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	@Column(name = "UPDATE", length = 50)
	public String getUpdate() {
		return this.update;
	}

	public void setUpdate(String update) {
		this.update = update;
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

	@Column(name = "UPDATE_TIME", length = 50)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "CREATE_USER", length = 50)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "UPDATE_USER", length = 50)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shopProduct")
	public Set<ShopOrderitem> getShopOrderitems() {
		return this.shopOrderitems;
	}

	public void setShopOrderitems(Set<ShopOrderitem> shopOrderitems) {
		this.shopOrderitems = shopOrderitems;
	}

}