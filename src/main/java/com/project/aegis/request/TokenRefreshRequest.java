package com.project.aegis.request;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author cahyaam
 */
public class TokenRefreshRequest {

  @NotBlank
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
