package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.dto.DonationDto;
import com.randob.crowdfunding_server.dto.ProjectDto;
import com.randob.crowdfunding_server.model.Project;
import com.randob.crowdfunding_server.payload.response.MessageResponse;
import com.randob.crowdfunding_server.repository.ProjectRepository;
import com.randob.crowdfunding_server.repository.UserRepository;
import com.randob.crowdfunding_server.security.services.UserDetailsImpl;
import com.randob.crowdfunding_server.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
  private final PaymentService paymentService;

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

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/donate")
  @Transactional
  public ResponseEntity<?> donateProject(@RequestBody DonationDto dto, Authentication authentication) {
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    if(userDetails.getBalance() >= dto.getValue()) {
      paymentService.makeDonation(dto, authentication);
      return ResponseEntity.ok(new MessageResponse("Success"));
    } else {
      return ResponseEntity.badRequest().body(new MessageResponse("Not enough funds on the account"));
    }
  }
}
