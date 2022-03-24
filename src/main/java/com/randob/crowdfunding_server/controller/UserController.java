package com.randob.crowdfunding_server.controller;

import com.randob.crowdfunding_server.model.User;
import com.randob.crowdfunding_server.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
