package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.model.User;
import com.randob.crowdfunding_server.payload.request.LoginRequest;
import com.randob.crowdfunding_server.payload.request.SignupRequest;
import com.randob.crowdfunding_server.payload.response.JwtResponse;
import com.randob.crowdfunding_server.payload.response.MessageResponse;
import com.randob.crowdfunding_server.repository.UserRepository;
import com.randob.crowdfunding_server.security.jwt.JwtUtils;
import com.randob.crowdfunding_server.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author randobigor
 **/

@AllArgsConstructor
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;

//todo  private final RoleRepository Addd

  private final PasswordEncoder passwordEncoder;

  private final JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//    List<Roles>

      return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), userDetails.getBalance(), userDetails.getPicture()));
    } catch (BadCredentialsException exc) {
      return ResponseEntity.badRequest().body(new MessageResponse("Bad credentials"));
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already taken!"));
    }

//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Email is already in use!"));
//    }

    // Create new user's account
//    User user = new User(
//        signUpRequest.getFirstName(),
//        signUpRequest.getLastName(),
//        signUpRequest.getEmail(),
//        passwordEncoder.encode(signUpRequest.getPassword()));

    User user = User.builder()
        .firstName(signUpRequest.getFirstName())
        .lastName(signUpRequest.getLastName())
        .email(signUpRequest.getEmail())
        .password(passwordEncoder.encode(signUpRequest.getPassword()))
        .build();

//    Set<String> strRoles = signUpRequest.getRole();
//    Set<Role> roles = new HashSet<>();

//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//          case "admin":
//            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(adminRole);
//
//            break;
//          case "mod":
//            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(modRole);
//
//            break;
//          default:
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        }
//      });
//    }

//    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}

