package com.project.BeClassyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BeClassyShop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
