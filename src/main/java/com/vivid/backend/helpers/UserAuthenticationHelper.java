package com.vivid.backend.helpers;

import java.util.List;
import java.util.Map;

import com.vivid.backend.exceptions.AuthenticationFailedException;
import com.vivid.backend.exceptions.UserNotFoundException;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.springframework.boot.json.BasicJsonParser;

public class UserAuthenticationHelper {

  private final UserRepository userRepository;
  private final BasicJsonParser basicJsonParser = new BasicJsonParser();

  public UserAuthenticationHelper(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User authenticate(String body) {

    Map<String, Object> data = basicJsonParser.parseMap(body);

    return userRepository.findByEmail(data.get("email").toString())
        .orElseThrow(() -> new UserNotFoundException("email: " + data.get("email").toString()));
  }

  public User authorize(String auth) {

    String[] authParts = auth.split(" ", 2);
    if (authParts.length < 2) {
      throw new AuthenticationFailedException();
    }
    String token = authParts[1];

    return userRepository.findByToken(token).orElseThrow(() -> new UserNotFoundException());
  }

}
