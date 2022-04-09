package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.ProjectDto;
import com.randob.crowdfunding_server.model.Project;
import com.randob.crowdfunding_server.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/{id}")
  public Project getProjectById(@PathVariable long id) {
    return projectRepository.findById(id).get();
  }

  @GetMapping("/category/{categoryId}")
  public List<Project> getProjectsByCategoryId(@PathVariable long categoryId) {
    return projectRepository.getProjectsByCategoryId(categoryId);
  }

  @PostMapping
  public void createNewProject(@RequestBody ProjectDto projectDto) {
    Project project = ProjectDto.toEntity(projectDto);
    project.setAnonymous(false);
    projectRepository.save(project);
  }
}
