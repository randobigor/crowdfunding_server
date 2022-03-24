package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.model.Project;
import com.randob.crowdfunding_server.repository.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author randobigor
 **/

@RestController
@RequestMapping("/projects")
public class ProjectController {
  private final ProjectRepository projectRepository;

  public ProjectController(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @GetMapping
  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }
}
