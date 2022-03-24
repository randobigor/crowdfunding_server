package com.randob.crowdfunding_server.repository;

import com.randob.crowdfunding_server.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author randobigor
 **/

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
