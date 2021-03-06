package com.vivid.backend.controller;

import java.util.Map;

import com.vivid.backend.exceptions.BadRequestException;
import com.vivid.backend.exceptions.UserAlreadyExistsException;
import com.vivid.backend.filters.UserFilters;
import com.vivid.backend.helpers.UserAuthenticationHelper;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
class UserController {

  private final UserRepository userRepository;
  private final UserAuthenticationHelper userAuthenticationHelper;
  private final BasicJsonParser basicJsonParser = new BasicJsonParser();

  UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.userAuthenticationHelper = new UserAuthenticationHelper(this.userRepository);
  }

  @GetMapping("/users")
  public MappingJacksonValue getAllUsersFiltered() {

    // List<User> users = userRepository.findAll();
    // MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);

    // mappingJacksonValue.setFilters(UserFilters.USER_DEFAULT_FILTER);

    // return mappingJacksonValue;

    throw new BadRequestException("Ask the backend why you don't need all the users at once");

    // return null;
  }

  @GetMapping("/user")
  public MappingJacksonValue getUserFiltered(@RequestHeader Map<String, String> headers) {

    User user = userAuthenticationHelper.authorize(headers.get("authorization"));
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);

    mappingJacksonValue.setFilters(UserFilters.USER_DEFAULT_FILTER);

    return mappingJacksonValue;
  }

  @PostMapping("/users/authenticate")
  public MappingJacksonValue authenticateUser(@RequestBody String body) {

    User user = userAuthenticationHelper.authenticate(body);

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);

    mappingJacksonValue.setFilters(UserFilters.USER_AUTHORIZATION_FILTER);

    return mappingJacksonValue;
  }

  @PostMapping("/users")
  public MappingJacksonValue createUser(@RequestBody String body) {

    Map<String, Object> json = basicJsonParser.parseMap(body);

    if (userRepository.findByEmail((String) json.get("email")).isPresent()) {
      throw new UserAlreadyExistsException();
    }

    User user = new User((String) json.get("name"), (String) json.get("email"), (String) json.get("password"));

    userRepository.save(user);

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);

    mappingJacksonValue.setFilters(UserFilters.USER_AUTHORIZATION_FILTER);

    return mappingJacksonValue;
  }
}
