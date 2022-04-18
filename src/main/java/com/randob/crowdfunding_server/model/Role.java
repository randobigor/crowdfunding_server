package com.randob.crowdfunding_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.PUBLIC_SCHEMA;
import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.Tables.ROLES;

/**
 * @author randobigor
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ROLES, schema = PUBLIC_SCHEMA)
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;
}
