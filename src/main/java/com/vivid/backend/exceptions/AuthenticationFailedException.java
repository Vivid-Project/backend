package com.vivid.backend.exceptions;

public class AuthenticationFailedException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 6106056216310753235L;

  public AuthenticationFailedException() {
    super("User Authentication information incorrect");
  }

}
