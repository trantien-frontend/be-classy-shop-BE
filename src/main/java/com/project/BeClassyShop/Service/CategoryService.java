package com.project.BeClassyShop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.Entity.Category;
import com.project.BeClassyShop.Repository.CategoryRepository;

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
