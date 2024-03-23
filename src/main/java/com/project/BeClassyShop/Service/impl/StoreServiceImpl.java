package com.project.BeClassyShop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.BeClassyShop.entity.StoreEntity;
import com.project.BeClassyShop.repository.StoreRepository;
import com.project.BeClassyShop.service.StoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

	private final StoreRepository storeRepository;

	@Override
	public List<StoreEntity> getStoresByProductColorAndProductSize(Integer id, String theColorName, Integer theSize) {
		List<StoreEntity> stores = storeRepository.findListStoreByProductColorAndProductSize(id, theColorName, theSize);
		return stores; 
	}

}
