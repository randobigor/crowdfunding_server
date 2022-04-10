package com.randob.crowdfunding_server.dto;

import com.randob.crowdfunding_server.model.Category;
import com.randob.crowdfunding_server.model.Project;
import com.randob.crowdfunding_server.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author randobigor
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDto {
  private Long category;
  private String description;
  private String name;
  private String picture;
  private Float target;
  private Long user;

  public static Project toEntity(ProjectDto dto) {
    Project project = new Project();
    project.setCategory(new Category(dto.getCategory(), null));
    project.setDescription(dto.getDescription());
    project.setName(dto.getName());
    project.setPicture(dto.getPicture());
    project.setTarget(dto.getTarget());

    User user = new User();
    user.setId(dto.getUser());

    project.setUser(user);

    return project;
  }
}