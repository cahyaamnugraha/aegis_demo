package com.project.aegis.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequest {

  @NotBlank
  @Size(min = 3, max = 20)
  @ApiModelProperty(example = "username", required = true)
  private String username;

  @NotBlank
  @Size(min = 6, max = 40)
  @ApiModelProperty(example = "password", required = true)
  private String password;

}
