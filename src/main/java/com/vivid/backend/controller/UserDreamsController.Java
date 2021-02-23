package com.vivid.backend.controller;

import java.util.List;

import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users/{id}")
class UserDreamsController {

  private final UserRepository userRepository;
  private final DreamRepository dreamRepository;

  UserDreamsController(UserRepository userRepository, DreamRepository dreamRepository) {
    this.userRepository = userRepository;
    this.dreamRepository = dreamRepository;
  }

  User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

  @GetMapping("/dreams")
  public ResponseEntity<> getMethodName(@RequestParam String param) {
      return new SomeData();
  }
}