package com.project.aegis.service;

import com.project.aegis.model.Result;
import com.project.aegis.request.LoginRequest;
import com.project.aegis.request.SignupRequest;
import com.project.aegis.request.TokenRefreshRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<?> createUser(SignupRequest signUpRequest);

  ResponseEntity<?> refreshToken(TokenRefreshRequest tokenRefreshRequest);

  ResponseEntity<?> signIn(LoginRequest loginRequest, String uri);

//  Result signOut(long id, String uri);

  Result active(long id, boolean isActive, String uri);

}
