package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.dto.AddFundsDto;
import com.randob.crowdfunding_server.model.User;
import com.randob.crowdfunding_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author randobigor
 **/

@CrossOrigin(originPatterns = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepository;

  @GetMapping
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping("/update-photo")
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  public void updateUserPhoto(@RequestBody User user, Authentication authentication) {
    userRepository.updateUserPhotoById(user.getPicture(), user.getId());
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping("/add-funds")
  @Transactional
  public void addFundsToAccountByUserId(@RequestBody AddFundsDto dto) {
    userRepository.addFundsToAccountById(dto.getValue(), dto.getUserId());
  }
}
