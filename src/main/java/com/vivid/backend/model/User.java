package com.vivid.backend.model;

import java.util.Base64;
import java.util.Set;
import java.security.SecureRandom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique=true)
  private String email;

  private String token;

  @OneToMany(mappedBy = "user")
  private Set<Dream> dreams;

  @OneToMany(mappedBy = "user")
  private Set<Theme> themes;

  protected User() {}

  public User(String name, String email) {
    this.name = name;
    this.email = email;
    this.token = generateNewToken();
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getToken() {
    return this.token;
  }

  private static final SecureRandom secureRandom = new SecureRandom();
  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

  private static String generateNewToken() {
      byte[] randomBytes = new byte[24];
      secureRandom.nextBytes(randomBytes);
      return base64Encoder.encodeToString(randomBytes);
  }
}
