package com.vivid.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = -1371819578401661232L;

  public UserNotFoundException(Long id) {
    super("Could not find user with id " + id);
  }
}
