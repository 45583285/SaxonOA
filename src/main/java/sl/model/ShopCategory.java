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
 * ShopCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_category", catalog = "saxon")
public class ShopCategory implements java.io.Serializable {

	// Fields

	private String uuid;
	private ShopCategory shopCategory;
	private String sort;
	private String name;
	private String isDelete;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String description;
	private Set<ShopProduct> shopProducts = new HashSet<ShopProduct>(0);
	private Set<ShopCategory> shopCategories = new HashSet<ShopCategory>(0);

	// Constructors

	/** default constructor */
	public ShopCategory() {
	}

	/** minimal constructor */
	public ShopCategory(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public ShopCategory(String uuid, ShopCategory shopCategory, String sort,
			String name, String isDelete, String createTime, String createUser,
			String updateTime, String updateUser, String description,
			Set<ShopProduct> shopProducts, Set<ShopCategory> shopCategories) {
		this.uuid = uuid;
		this.shopCategory = shopCategory;
		this.sort = sort;
		this.name = name;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.createUser = createUser;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.description = description;
		this.shopProducts = shopProducts;
		this.shopCategories = shopCategories;
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

	@Column(name = "SORT", length = 10)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "DESCRIPTION", length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shopCategory")
	public Set<ShopProduct> getShopProducts() {
		return this.shopProducts;
	}

	public void setShopProducts(Set<ShopProduct> shopProducts) {
		this.shopProducts = shopProducts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shopCategory")
	public Set<ShopCategory> getShopCategories() {
		return this.shopCategories;
	}

	public void setShopCategories(Set<ShopCategory> shopCategories) {
		this.shopCategories = shopCategories;
	}

}