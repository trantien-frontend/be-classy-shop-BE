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
	
	@Query(value = "SELECT p.product_name, p.product_price from product as p "
			+ " JOIN category as c on p.category_id = c.id"
			+ " JOIN product_type as pt on c.id = pt.category_id"
			+ "	WHERE pt.product_type_name= :productTypeName", nativeQuery = true)
	List<Product> findListProductByProductTypeName(@Param("productTypeName") String theProductTypeName); 
}