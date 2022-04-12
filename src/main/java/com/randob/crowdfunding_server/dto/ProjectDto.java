package com.randob.crowdfunding_server.dto;

import com.randob.crowdfunding_server.model.Category;
import com.randob.crowdfunding_server.model.Picture;
import com.randob.crowdfunding_server.model.Project;
import com.randob.crowdfunding_server.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
  private List<Picture> descriptionPictures;

  public static Project toEntity(ProjectDto dto) {
    Project project = new Project();
    project.setCategory(Category.builder().id(dto.getCategory()).build());
    project.setDescription(dto.getDescription());
    project.setName(dto.getName());
    project.setPicture(dto.getPicture());
    project.setTarget(dto.getTarget());
    project.setUser(User.builder().id(dto.getUser()).build());
    project.setDescriptionPictures(dto.getDescriptionPictures());

    return project;
  }
}
