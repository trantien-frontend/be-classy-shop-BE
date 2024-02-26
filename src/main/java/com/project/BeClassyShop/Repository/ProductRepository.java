package com.project.BeClassyShop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.BeClassyShop.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//	@Query("Select p from Product as p" + " where p.category.id = :categoryId")
//	List<Product> findListProductByProductId(@Param("categoryId") Integer categoryId);
	@Query("Select p from Product as p join p.category")
	public List<Product> getListProduct(); 
	
	@Query("Select p from Product as p"
			+ " Join p.category as c"
			+ " where c.categoryName = :categoryName")
	List<Product> findListProductByCategoryName(@Param("categoryName") String categoryName);
	
//	@Query("Select p from Product as p"
//			+ " Join p.productType as pt"
//			+ " where pt.productTypeName = :productTypeName")
//	List<Product> findListProductByProductType(@Param("productTypeName") String productTypeName); 
}
