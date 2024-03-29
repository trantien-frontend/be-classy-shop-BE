package com.project.BeClassyShop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.BeClassyShop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p from Product as p" + "	WHERE p.category.categoryName = :categoryName")
	Page<Product> findByCategoryName(@Param("categoryName") String theCategoryName, Pageable pageable);

	@Query("SELECT p from Product as p" + "	WHERE p.productType.productTypeName = :productTypeName")
	Page<Product> findByProductTypeName(@Param("productTypeName") String theProductTypeName, Pageable pageable);
	
	@Query("SELECT p from Product as p" + " WHERE p.productType.productTypeName = :productTypeName")
	List<Product> getListProductByProductType (@Param("productTypeName") String theProductTypeName); 

}