package com.project.BeClassyShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.dto.CustomePage;
import com.project.BeClassyShop.entity.Product;
import com.project.BeClassyShop.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProducts() {
		return productRepository.findAll(); 
	}

	public CustomePage<Product> getListProduct(Integer thePage, Integer theLimit, String sortBy) {
		Pageable pageable = PageRequest.of(thePage,theLimit);
	
		if (sortBy != null && !sortBy.isEmpty()) {
			Sort sort = handleSortBy(sortBy);
			pageable = PageRequest.of(thePage, theLimit, sort);
		}
		return new CustomePage<Product>(productRepository.findAll(pageable));
	}

	public CustomePage<Product> getProductsByCategoryName(Integer thePage, Integer theLimit, String theCategoryName,
			String sortBy) {
		Pageable pageable = PageRequest.of(thePage, theLimit);  
		System.out.println("sortBy: " + sortBy);

		if (sortBy != null && !sortBy.isEmpty()) {
			Sort sort = handleSortBy(sortBy);
			pageable = PageRequest.of(thePage, theLimit, sort);
		}
		return new CustomePage<Product>(productRepository.findByCategoryName(theCategoryName, pageable));
	}

	public CustomePage<Product> getProductsByProductTypeName(Integer thePage, Integer theLimit,
			String theProductTypeName, String sortBy) {
		Pageable pageable = PageRequest.of(thePage, theLimit);
		if (sortBy != null && !sortBy.isEmpty()) {
			Sort sort = handleSortBy(sortBy);
			pageable = PageRequest.of(thePage, theLimit, sort);
		}
		return new CustomePage<Product>(productRepository.findByProductTypeName(theProductTypeName, pageable));
	}
	
	public List<Product> getProductsByProductTypeName(String theProductTypeName){
		return productRepository.getListProductByProductType(theProductTypeName); 
	}

	public Product getProductById(Integer theId) {
		Optional<Product> product = productRepository.findById(theId);
		return product.get();
	}

	public Product updateProduct(Product theProduct) {
		theProduct.setId(0);
		return productRepository.save(theProduct);
	}

	public Product addProduct(Product theProduct) {
		return productRepository.save(theProduct);
	}

	public void deleteProduct(Integer theId) {
		Product product = productRepository.findById(theId).get();
		if (product == null) {
			throw new RuntimeException("Not found Id" + theId);
		}
		productRepository.delete(product);
	}
	
	private Sort handleSortBy(String sortBy) {
		String sortField = sortBy;
		Sort.Direction sortDirection = Sort.Direction.ASC;

		if (sortBy.contains(":")) {
			String[] parts = sortBy.split(":");
			sortField = parts[0];
			sortDirection = parts.length > 1 && parts[1].equalsIgnoreCase("dsc") ? Sort.Direction.DESC
					: Sort.Direction.ASC;
		}
		return Sort.by(sortDirection, sortField);
	}
}