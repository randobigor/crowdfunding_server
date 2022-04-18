package com.randob.crowdfunding_server.dto;

import lombok.*;

/**
 * @author randobigor
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFundsDto {
  private Long userId;
  private Float value;
}
