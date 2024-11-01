package com.project.aegis.serviceImpl;

import com.project.aegis.enums.EnumRole;
import com.project.aegis.exception.TokenRefreshException;
import com.project.aegis.model.RefreshToken;
import com.project.aegis.model.Result;
import com.project.aegis.model.Role;
import com.project.aegis.model.User;
import com.project.aegis.repository.RoleRepository;
import com.project.aegis.repository.UserRepository;
import com.project.aegis.request.LoginRequest;
import com.project.aegis.request.SignupRequest;
import com.project.aegis.request.TokenRefreshRequest;
import com.project.aegis.response.JwtResponse;
import com.project.aegis.response.TokenRefreshResponse;
import com.project.aegis.service.UserService;
import com.project.aegis.util.JwtUtils;
import com.project.aegis.util.StringUtil;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  UserRepository userRepository;
  
  @Autowired
  RoleRepository roleRepository;
  
  @Autowired
  StringUtil stringUtil;
  
  @Autowired
  PasswordEncoder encoder;
  
  @Autowired
  AuthenticationManager authenticationManager;
  
  @Autowired
  JwtUtils jwtUtils;
  
  @Autowired
  RefreshTokenService refreshTokenService;

  @Value("${app.jwtExpirationMs}")
  private int jwtExpirationMs;
  
  private Result result;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Override
  public ResponseEntity<?> createUser(SignupRequest signUpRequest) {
    result = new Result();

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      result.setMessage("Error: Usename is already taken!");
      result.setCode(HttpStatus.BAD_REQUEST.value());
      return ResponseEntity
          .badRequest()
          .body(result);
    }
    
    Optional<Role> roleOptional = roleRepository.findByName(EnumRole.ROLE_KASIR);
    if (roleOptional.isPresent()) {
      userRepository.save(new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), roleOptional.get(), false, signUpRequest.getUsername()));

      result.setMessage("User registered successfully!");
      result.setCode(HttpStatus.OK.value());
    }

    return ResponseEntity.ok(result);
  }
  
  @Override
  public ResponseEntity<?> signIn(LoginRequest loginRequest, String uri) {
    result = new Result();
    User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(new User());
    if (user.getUsername() != null) {
      user = userRepository.findByUsernameAndIsActive(loginRequest.getUsername(), true).orElse(new User());
      if (user.getUsername() != null) {
        Date dateNow = new Date();
        Date dateExpired = new Date((dateNow).getTime() + jwtExpirationMs);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication, dateNow, dateExpired);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Role role = roleRepository.findByName(EnumRole.valueOf(userDetails.getAuthorities().stream().findAny().get().getAuthority())).orElse(new Role());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        result.setSuccess(true);
        result.setMessage("success");
        result.setData(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), role, dateExpired.getTime()));
      } else {
        result.setSuccess(true);
        result.setCode(HttpStatus.BAD_REQUEST.value());
        result.setMessage("user is not active yet");
      }
    } else {
      result.setSuccess(true);
      result.setCode(HttpStatus.BAD_REQUEST.value());
      result.setMessage("cannot found user");
    }
    return ResponseEntity.ok(result);
  }
  
  @Override
  public Result active(long id, boolean isActive, String uri) {
    result = new Result();
    try {
      int status = userRepository.setIsActive(isActive, id);
      if (status == 1) {
        result.setMessage("user is actived");
      } else {
        result.setMessage("failed");
        result.setSuccess(false);
        result.setCode(HttpStatus.BAD_REQUEST.value());
      }
      
    } catch (Exception e) {
      logger.error(stringUtil.getError(e));
    }
    
    return result;
  }
  
  @Override
  public ResponseEntity<?> refreshToken(TokenRefreshRequest tokenRefreshRequest) {
    String requestRefreshToken = tokenRefreshRequest.getRefreshToken();
    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
        "Refresh token is not in database!"));
  }
  
  
}
