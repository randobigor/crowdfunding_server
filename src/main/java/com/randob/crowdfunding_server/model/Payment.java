package com.randob.crowdfunding_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
  public static final String ANONYMOUS = "Анонимно";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonFormat(pattern="yyyy-MM-dd")
  @Column(name = "processed_tm", updatable = false, insertable = false)
  private LocalDateTime dateTime;

  @ManyToOne
  @Getter(AccessLevel.NONE)
  @JoinColumn(name = "donated_by")
  private User user;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Column(name = "value")
  private Float value;

  @JsonIgnore
  @Column(name = "anonymous")
  private boolean anonymous;

  @Transient
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private String fullName;

  public String getFullName() {
    return isAnonymous() ? ANONYMOUS : user.getFullName();
  }
}
