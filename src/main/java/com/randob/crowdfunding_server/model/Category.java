package com.randob.crowdfunding_server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.PUBLIC_SCHEMA;
import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.Tables.CATEGORIES;

/**
 * @author randobigor
 **/

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = CATEGORIES, schema = PUBLIC_SCHEMA)
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @Column(name = "position")
  private Integer position;
}
