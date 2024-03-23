package com.project.BeClassyShop.apis;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.BeClassyShop.entity.StoreEntity;
import com.project.BeClassyShop.service.StoreService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class StoreApis {
	private final StoreService storeService;

	@GetMapping("/stores")
	public List<StoreEntity> getListStore(@RequestParam(name = "id", required = true) Integer theId,
			@RequestParam(name = "color", required = false) String theColorName,
			@RequestParam(name = "size", required = false) Integer theSize) {
		List<StoreEntity> stores = storeService.getStoresByProductColorAndProductSize(theId, theColorName, theSize);
		return stores;
	}
}
