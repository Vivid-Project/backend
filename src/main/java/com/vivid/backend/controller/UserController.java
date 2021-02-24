package com.vivid.backend.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.vivid.backend.exceptions.UserNotFoundException;
import com.vivid.backend.filters.UserFilters;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

  private final UserRepository userRepository;

  UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public MappingJacksonValue getAllUsersFiltered() {

    List<User> users = userRepository.findAll();
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);

    mappingJacksonValue.setFilters(UserFilters.USER_DEFAULT_FILTER);

    return mappingJacksonValue;
  }

  @GetMapping("/users/{id}")
  public MappingJacksonValue getUserFiltered(@PathVariable Long id) {

    User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);

    mappingJacksonValue.setFilters(UserFilters.USER_DEFAULT_FILTER);

    return mappingJacksonValue;
  }
}
