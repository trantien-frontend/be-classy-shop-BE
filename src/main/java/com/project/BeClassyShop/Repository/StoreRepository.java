package com.project.BeClassyShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.BeClassyShop.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {
	@Query(value="select s.id, s.store_name, s.store_address ,sp.quantity from store as s"
			+ " join store_product as sp on s.id = sp.store_id"
			+ " join product_size_color as spc on sp.product_size_color_id = spc.id"
			+ " join color as c on spc.color_id = c.id"
			+ " join size as si on spc.size_id = si.id"
			+ " where spc.product_id =:id and c.color_name =:colorName and si.size_name =:sizeName", nativeQuery = true)
	List<StoreEntity> findListStoreByProductColorAndProductSize(@Param("id") Integer Id, @Param("colorName") String colorName, @Param("sizeName") int sizeName); 
}
