package com.project.BeClassyShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.entity.Banner;
import com.project.BeClassyShop.repository.BannerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BannerService {
	@Autowired
	private BannerRepository bannerRepository; 
	
	public List<Banner> getListBanner () {
		List<Banner> listBanner = bannerRepository.findAll(); 
		return listBanner; 
	}
	
}
