package com.vivid.backend.exceptions;

public class MicroserviceRequestFailedException extends RuntimeException{

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  
  public MicroserviceRequestFailedException() {
    super("Failed to retrieve data from microservice");
  }
}
