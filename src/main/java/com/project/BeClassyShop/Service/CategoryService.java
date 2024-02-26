package com.project.BeClassyShop.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BeClassyShop.Entity.Category;
import com.project.BeClassyShop.Repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	public List<Category> getListCategory() {
		return categoryRepository.findAll(); 
	}
	
	
//	public List<CategoryDTO> getCategories () {
//		List<CategoryDTO> categoriesDTO = new ArrayList<>(); 
//		List<Category> categories = this.categoryRepository.findAll(); 
//		categories.forEach(category -> categoriesDTO.add(mapper.map(category, CategoryDTO.class)));
//		return categoriesDTO; 
//	}
}
