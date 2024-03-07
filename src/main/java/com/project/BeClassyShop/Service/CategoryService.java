package com.project.BeClassyShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.entity.Category;
import com.project.BeClassyShop.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getListCategory() {
		return categoryRepository.findAll();
	}
}
