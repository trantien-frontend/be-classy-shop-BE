package com.project.BeClassyShop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.BeClassyShop.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product as p"
			+ "	JOIN p.category as c"
			+ "	WHERE c.categoryName = :categoryName")
	List<Product> findListProductByCategoryName (@Param("categoryName") String categoryName); 
	
	@Query(value = "SELECT product.id, product.product_name FROM product", nativeQuery = true)
	List<Product> findListProductByProductTypeName(); 
}
