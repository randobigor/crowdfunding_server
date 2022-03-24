package com.randob.crowdfunding_server.repository;

import com.randob.crowdfunding_server.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author randobigor
 **/

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
