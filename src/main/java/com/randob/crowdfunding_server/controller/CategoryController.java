package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.model.Category;
import com.randob.crowdfunding_server.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author randobigor
 **/

@RestController
@RequestMapping("/categories")
public class CategoryController {
  private final CategoryRepository categoryRepository;

  public CategoryController(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @GetMapping
  public List<Category> getAllCategories() {
    return categoryRepository.findByOrderByPosition();
  }
}
