package com.vivid.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthenticationFailedException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 6106056216310753235L;

  public AuthenticationFailedException() {
    super("User authentication information incorrect");
  }

  public AuthenticationFailedException(String reason) {
    super(reason);
  }

}
