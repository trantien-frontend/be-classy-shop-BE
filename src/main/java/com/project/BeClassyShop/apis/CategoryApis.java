package com.project.BeClassyShop.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BeClassyShop.entity.Category;
import com.project.BeClassyShop.service.CategoryService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1")
public class CategoryApis {
	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "/categories")
	public List<Category> getListCateogry() {
		return categoryService.getListCategory();
	}
}	
