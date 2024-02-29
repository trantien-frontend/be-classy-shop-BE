package com.project.BeClassyShop.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BeClassyShop.Entity.Banner;
import com.project.BeClassyShop.Service.BannerService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class BannerApi {
	@Autowired
	private BannerService bannerService;

	@GetMapping(path = "/banners")
	public List<Banner> getListBanner() {
		return bannerService.getListBanner();
	}
}
