package com.randob.crowdfunding_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.PUBLIC_SCHEMA;
import static com.randob.crowdfunding_server.util.DatabaseConstants.Public.Tables.PAYMENTS;

/**
 * @author randobigor
 **/

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PAYMENTS, schema = PUBLIC_SCHEMA)
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "processed_tm", updatable = false, insertable = false)
  private LocalDateTime dateTime;

  @ManyToOne
  @JoinColumn(name = "donated_by")
  private User user;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Column(name = "value")
  private Float value;

  @Column(name = "anonymous")
  private boolean anonymous;
}
