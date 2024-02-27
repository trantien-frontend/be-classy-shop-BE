package com.project.BeClassyShop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.DTO.CustomePage;
import com.project.BeClassyShop.Entity.Product;
import com.project.BeClassyShop.Repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public CustomePage<Product> getListProduct(Integer thePage, Integer theLimit) {
		Pageable pageable = PageRequest.of(thePage, theLimit);
		return new CustomePage<Product>(productRepository.findAll(pageable));
	}

	public Product getProductById(Integer theId) {
		Optional<Product> product = productRepository.findById(theId);
		return product.get();
	}

	public List<Product> getProductsByProductTypeName(String theProductTypeName) {
		return productRepository.findListProductByProductTypeName(theProductTypeName);
	}

	public List<Product> getProductByCategoryName(String theCategoryName) {
		return productRepository.findListProductByCategoryName(theCategoryName);
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
}