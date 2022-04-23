package com.randob.crowdfunding_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author randobigor
 **/

@Getter
@Setter
@AllArgsConstructor
public class DonationDto {
  private Long userId;
  private Long projectId;
  private Float value;
}
