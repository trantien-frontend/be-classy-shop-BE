package com.project.BeClassyShop.apis;

import java.util.Optional;

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

import com.project.BeClassyShop.dto.CustomePage;
import com.project.BeClassyShop.entity.Product;
import com.project.BeClassyShop.service.ProductService;

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
			@RequestParam(name = "limit", required = false, defaultValue = "3") String _limit,
			@RequestParam(name = "sortby", required = false) String _sortBy) {
		int page = Integer.parseInt(_page);
		int limit = Integer.parseInt(_limit);
		Optional<String> sortBy = Optional.ofNullable(_sortBy);
		return productService.getListProduct(page, limit, sortBy.orElse(null));
	}

	@GetMapping(path = "/products/category/{categoryName}")
	public CustomePage<Product> getProductsByCategoryName(@PathVariable(name = "categoryName") String theCategoryName,
			@RequestParam(name = "limit", required = false, defaultValue = "3") String _limit,
			@RequestParam(name = "page", required = false, defaultValue = "0") String _page,
			@RequestParam(name = "sortby", required = false) String _sortBy) {
		int page = Integer.parseInt(_page);
		int limit = Integer.parseInt(_limit);
		Optional<String> sortBy = Optional.ofNullable(_sortBy);
		return productService.getProductsByCategoryName(page, limit, theCategoryName, sortBy.orElse(null));
	}

	@GetMapping(path = "/products/productType/{productTypeName}")
	public CustomePage<Product> getProductsByProductTypeName(
			@PathVariable(name = "productTypeName") String theProductTypeName,
			@RequestParam(name = "page", required = false, defaultValue = "0") String _page,
			@RequestParam(name = "limit", required = false, defaultValue = "3") String _limit, 
			@RequestParam(name = "sortby", required = false) String _sortBy) {
		int page = Integer.parseInt(_page);
		int limit = Integer.parseInt(_limit);
		Optional<String> sortBy = Optional.ofNullable(_sortBy);
		return productService.getProductsByProductTypeName(page, limit, theProductTypeName, sortBy.orElse(null));
	}

	@GetMapping(path = "/product/{productId}")
	public Product getProductById(@PathVariable(name = "productId") Integer productId) {
		Product product = productService.getProductById(productId);
		return product;
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
