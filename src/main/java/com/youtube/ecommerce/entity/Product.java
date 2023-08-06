package com.youtube.ecommerce.entity;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import net.bytebuddy.implementation.bind.annotation.Default;

import java.util.Set;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	@Column(length = 2000)
	private String productDescription;
	private String productFullDescription;
	private Double productDiscountedPrice;
	private Double productActualPrice;
	@Value("false")
	private Boolean bestSeller;
	@Value("false")
	private Boolean trendProduct;
	private String type;
	private Integer ml;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "product_images", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "image_id") })
	private Set<ImageModel> productImages;

	public Set<ImageModel> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ImageModel> productImages) {
		this.productImages = productImages;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductFullDescription() {
		return productFullDescription;
	}

	public void setProductFullDescription(String productFullDescription) {
		this.productFullDescription = productFullDescription;
	}

	public Double getProductDiscountedPrice() {
		return productDiscountedPrice;
	}

	public void setProductDiscountedPrice(Double productDiscountedPrice) {
		this.productDiscountedPrice = productDiscountedPrice;
	}

	public Double getProductActualPrice() {
		return productActualPrice;
	}

	public void setProductActualPrice(Double productActualPrice) {
		this.productActualPrice = productActualPrice;
	}


	public Boolean getBestSeller() {
		return bestSeller;
	}

	public void setBestSeller(Boolean bestSeller) {
		this.bestSeller = bestSeller;
	}

	public Boolean getTrendProduct() {
		return trendProduct;
	}

	public void setTrendProduct(Boolean trendProduct) {
		this.trendProduct = trendProduct;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMl() {
		return ml;
	}

	public void setMl(Integer ml) {
		this.ml = ml;
	}

}
