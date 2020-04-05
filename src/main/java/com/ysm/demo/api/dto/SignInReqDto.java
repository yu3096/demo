package com.ysm.demo.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInReqDto {
  private String userId;
  private String userPw;
}
