package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.dto.ProjectDto;
import com.randob.crowdfunding_server.model.Project;
import com.randob.crowdfunding_server.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author randobigor
 **/

@CrossOrigin(originPatterns = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
  private final ProjectRepository projectRepository;

  @PreAuthorize("permitAll()")
  @GetMapping
  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  @PreAuthorize("permitAll()")
  @GetMapping("/{id}")
  public Project getProjectById(@PathVariable long id) {
    return projectRepository.findById(id).get();
  }

  @PreAuthorize("permitAll()")
  @GetMapping("/category/{categoryId}")
  public List<Project> getProjectsByCategoryId(@PathVariable long categoryId) {
    return projectRepository.getProjectsByCategoryId(categoryId);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping
  public void createNewProject(@RequestBody ProjectDto projectDto) {
    projectRepository.save(ProjectDto.toEntity(projectDto));
  }
//
//  @PreAuthorize("#projectDto.user == authentication.getPrincipal().getId()")
//  @PutMapping
//  public void updateProject(@RequestBody ProjectDto projectDto, Authentication authentication) {
//    System.out.println("some code");
//  }
}
