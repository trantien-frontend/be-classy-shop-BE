package com.project.BeClassyShop.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_price")
	private double productPrice;
	@Column(name = "product_image")
	private String productImage;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "product_size_color", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_size"))
	@JsonProperty("listSize")
	private List<Size> listSizeProduct = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE })
	@JoinTable(name = "product_size_color", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_color"))
	private List<Color> listColor = new ArrayList<>();
}
