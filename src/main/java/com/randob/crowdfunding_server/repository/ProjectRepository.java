package com.randob.crowdfunding_server.repository;

import com.randob.crowdfunding_server.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author randobigor
 **/

public interface ProjectRepository extends JpaRepository<Project, Long> {
  List<Project> getProjectsByCategoryId(long categoryId);

  @Modifying
  @Query("UPDATE Project p set p.collected = p.collected + ?1 WHERE p.id = ?2")
  void updateCollectedMoney(Float value, Long projectId);
}
