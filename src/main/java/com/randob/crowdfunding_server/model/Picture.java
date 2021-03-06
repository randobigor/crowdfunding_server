package com.randob.crowdfunding_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.PUBLIC_SCHEMA;
import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.Tables.PICTURES;

/**
 * @author randobigor
 **/

@Entity
@Table(name = PICTURES, schema = PUBLIC_SCHEMA)
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "content")
  private String content;
}
