package com.security.server.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {

  private String name;
  private String email;
}
