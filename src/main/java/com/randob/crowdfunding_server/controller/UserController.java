package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.model.User;
import com.randob.crowdfunding_server.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author randobigor
 **/

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
  }

  @PutMapping
  @Transactional
  @ResponseStatus(HttpStatus.OK)
  public void updateUserPhoto(@RequestBody User user) {
    userRepository.updateUserPhotoById(user.getPicture(), user.getId());
  }
}
