package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.model.Category;
import com.randob.crowdfunding_server.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author randobigor
 **/

@CrossOrigin(originPatterns = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
  private final CategoryRepository categoryRepository;

  @PreAuthorize("permitAll()")
  @GetMapping
  public List<Category> getAllCategories() {
    return categoryRepository.findByOrderByPosition();
  }
}
