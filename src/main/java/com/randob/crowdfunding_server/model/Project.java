package com.randob.crowdfunding_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author randobigor
 **/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "collected", insertable = false)
  private Float collected;

  @Column(name = "target")
  private Float target;

  @Column(name = "anonymous")
  private Boolean anonymous;

  @Column(name = "picture")
  private String picture;
}
