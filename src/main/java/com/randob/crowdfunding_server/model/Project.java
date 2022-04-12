package com.randob.crowdfunding_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author randobigor
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "projects", schema = "public")
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

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "projects_to_pictures",
      joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id")
  )
  private List<Picture> descriptionPictures;
}
