package com.randob.crowdfunding_server.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.randob.crowdfunding_server.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author randobigor
 **/

public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = -7207374231965178869L;

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Float balance;
  private String picture;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String firstName, String lastName, String email, Float balance, String picture, String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.balance = balance;
    this.picture = picture;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    //TODO: Add roles here
    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

    return new UserDetailsImpl(
        user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getBalance(),
        user.getPicture(),
        user.getPassword(),
        authorities
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public String getEmail() {return email;}

  public Float getBalance() {
    return balance;
  }

  public String getPicture() {
    return picture;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    UserDetailsImpl user = (UserDetailsImpl) obj;
    return Objects.equals(id, user.id);
  }
}
