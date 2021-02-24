package com.vivid.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DreamNotFoundException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = -324589763425876L;

  public DreamNotFoundException(Long id) {
    super("Could not find dream with id " + id);
  }
}
