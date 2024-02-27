package com.project.BeClassyShop.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.BeClassyShop.DTO.CustomePage;
import com.project.BeClassyShop.Entity.Product;
import com.project.BeClassyShop.Service.ProductService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class ProductApis {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/products")
	public CustomePage<Product> getListProuct(
			@RequestParam(name = "page", required = false, defaultValue = "1") String _page,
			@RequestParam(name = "limit", required = false, defaultValue = "3") String _limit) {
		int page = Integer.parseInt(_page);
		int limit = Integer.parseInt(_limit);
		return productService.getListProduct(page, limit);
	}

	@GetMapping(path = "/products/{productId}")
	public Product getProductById(@PathVariable(name = "productId") Integer productId) {
		Product product = productService.getProductById(productId);
		return product;
	}

	@GetMapping(path = "/products/category/{productTypeName}")
	public List<Product> getProductsByProductTypeName(@PathVariable(name = "productTypeName") String productTypeName) {
		return this.productService.getProductsByProductTypeName(productTypeName);
	}

	@PostMapping(path = "/products")
	public Product addProduct(@RequestBody Product theProduct) {
		return productService.addProduct(theProduct);
	}

	@PutMapping(path = "/products")
	public Product updateProduct(@RequestBody Product theProduct) {
		return productService.updateProduct(theProduct);
	}

	@DeleteMapping("/products/{productId}")
	public void removeProduct(@PathVariable(name = "productId") Integer productId) {
		productService.deleteProduct(productId);
	}
}
