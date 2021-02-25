package com.vivid.backend.helpers;

import java.util.Map;
import java.util.Optional;

import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.springframework.boot.json.JacksonJsonParser;

public class UserAuthenticationHelper {

  private final UserRepository userRepository;
  private JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();

  public UserAuthenticationHelper(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> authenticate(String body) {

    Map<String, Object> data = jacksonJsonParser.parseMap(body);

    return userRepository.findByEmail(data.get("email").toString());
  }

}
