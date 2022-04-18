package com.randob.crowdfunding_server.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author randobigor
 **/

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
  private String token;
  private final String type = "Bearer";
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Float balance;
  private String picture;
}
