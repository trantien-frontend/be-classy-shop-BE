package com.project.BeClassyShop.service;

import java.util.List;

import com.project.BeClassyShop.entity.StoreEntity;

public interface StoreService {
	List<StoreEntity> getStoresByProductColorAndProductSize(Integer id, String theColorName, Integer theSize);
}
