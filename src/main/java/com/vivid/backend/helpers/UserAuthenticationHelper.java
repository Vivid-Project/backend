package com.vivid.backend.helpers;

import java.util.Map;

import com.vivid.backend.exceptions.AuthenticationFailedException;
import com.vivid.backend.exceptions.UserNotFoundException;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserAuthenticationHelper {

  private final UserRepository userRepository;
  private final BasicJsonParser basicJsonParser = new BasicJsonParser();
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserAuthenticationHelper(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User authenticate(String body) {

    Map<String, Object> data = basicJsonParser.parseMap(body);

    User user = userRepository.findByEmail(data.get("email").toString()).orElseThrow(() -> new UserNotFoundException());

    if (data.containsKey("password")
        && passwordEncoder.matches(data.get("password").toString(), user.getPasswordDigest())) {

      return user;

    } else if (data.containsKey("password")) {

      throw (new UserNotFoundException());

    } else {

      throw (new AuthenticationFailedException("Information Data Structure Invalid"));

    }

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
