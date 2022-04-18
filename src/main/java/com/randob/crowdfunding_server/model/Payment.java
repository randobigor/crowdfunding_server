package com.randob.crowdfunding_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.PUBLIC_SCHEMA;
import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.Tables.PAYMENTS;

/**
 * @author randobigor
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PAYMENTS, schema = PUBLIC_SCHEMA)
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "datetime", updatable = false, insertable = false)
  private LocalDateTime dateTime;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Column(name = "value")
  private Float value;
}
