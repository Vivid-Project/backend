package com.vivid.backend.controller;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class UserControllerTest {
  
  private String body = "{ \"email\": \"person@example.com\"}";
  private String token = "";

  @Before
  public void setup() {
    // Register
    given()
    .contentType(ContentType.JSON)
    .body(body)
    .when()
    .post("https://vivid-project-backend.herokuapp.com/users/authenticate")
    .then()
    .assertThat()
    .statusCode(HttpStatus.SC_OK);

    // Generate Token 
    token = given()
    .contentType(ContentType.JSON)
    .body(body)
    .when()
    .post("https://vivid-project-backend.herokuapp.com/users/authenticate")
    .jsonPath()
    .get("token");
  }

  public void testGetWithToken() {
    given()
  }
}


// Uri: POST /users/authenticate

// {
//     "email": "adrew@example.com"
// }