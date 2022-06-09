package com.randob.crowdfunding_server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.PUBLIC_SCHEMA;
import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.Tables.*;

/**
 * @author randobigor
 **/

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PROJECTS, schema = PUBLIC_SCHEMA)
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "created_by")
  private User user;

  @Column(name = "collected", insertable = false)
  private Float collected;

  @Column(name = "target")
  private Float target;

  @Column(name = "picture")
  private String picture;

  @Column(name = "completed", insertable = false)
  private Boolean completed;

  @Column(name = "created_tm", insertable = false, updatable = false)
  private LocalDateTime createdDateTime;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = PROJECTS_TO_PICTURES, schema = PUBLIC_SCHEMA,
      joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id")
  )
  private List<Picture> descriptionPictures;
}
