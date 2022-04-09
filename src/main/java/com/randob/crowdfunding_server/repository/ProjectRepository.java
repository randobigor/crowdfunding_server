package com.randob.crowdfunding_server.repository;

import com.randob.crowdfunding_server.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author randobigor
 **/

public interface ProjectRepository extends JpaRepository<Project, Long> {
  List<Project> getProjectsByCategoryId(long categoryId);
}
