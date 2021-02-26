package com.vivid.backend.helpers;

import java.util.Map;

import com.vivid.backend.exceptions.AuthenticationFailedException;
import com.vivid.backend.exceptions.UserNotFoundException;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;

public class UserAuthenticationHelper {

  private final UserRepository userRepository;
  private final BasicJsonParser basicJsonParser = new BasicJsonParser();

  public UserAuthenticationHelper(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User authenticate(String body) {

    Map<String, Object> data = basicJsonParser.parseMap(body);

    return userRepository.findByEmail(data.get("email").toString())
        .orElseThrow(() -> new AuthenticationFailedException());
  }

  public User authorize(String auth) {
    String token = auth.split(" ", 2)[1];

    return userRepository.findByToken(token).orElseThrow(() -> new UserNotFoundException());
  }

}
