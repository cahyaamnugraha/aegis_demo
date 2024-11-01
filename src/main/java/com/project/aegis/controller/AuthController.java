package com.project.aegis.controller;

import com.project.aegis.request.LoginRequest;
import com.project.aegis.request.SignupRequest;
import com.project.aegis.request.TokenRefreshRequest;
import com.project.aegis.service.UserService;
import com.project.aegis.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  UserService service;

  @Autowired
  StringUtil stringUtil;

  @Autowired
  HttpServletRequest request;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    String uri = stringUtil.getLogParam(request);
    logger.info(uri);

    return service.signIn(loginRequest, uri);
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    return service.createUser(signUpRequest);
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    return service.refreshToken(request);
  }
}
