package com.project.BeClassyShop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.Entity.Banner;
import com.project.BeClassyShop.Repository.BannerRepository;

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
