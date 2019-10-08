package com.security.server.controller;

import com.security.server.entity.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @CrossOrigin
  @GetMapping("/api/userInfo")
  public ResponseEntity<UserInfo> getUserInfo() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    String email = user.getUsername() + "@spring.com";
    UserInfo userInfo = UserInfo.builder().name(user.getUsername()).email(email).build();

    return ResponseEntity.ok(userInfo);
  }
}
