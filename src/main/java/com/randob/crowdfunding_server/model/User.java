package com.randob.crowdfunding_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.PUBLIC_SCHEMA;
import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.Tables.USERS;
import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.Tables.USERS_TO_ROLES;

/**
 * @author randobigor
 **/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Table(name = USERS, schema = PUBLIC_SCHEMA)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "balance", insertable = false)
  private Float balance;

  @Column(name = "picture")
  private String picture;

  @ManyToMany
  @JoinTable(
      name = USERS_TO_ROLES, schema = PUBLIC_SCHEMA,
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private List<Role> roles;
}
