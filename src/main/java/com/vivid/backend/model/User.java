package com.vivid.backend.model;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@Table(name="users")
@JsonFilter("userFilter")
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
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

  public Set<Dream> getDreams() {
    return this.dreams;
  }

  public Set<Theme> getThemes() {
    return this.themes;
  }

  public void addDream(Dream dream) {
    dreams.add(dream);
    dream.setUser(this);
  }

  private static final SecureRandom secureRandom = new SecureRandom();
  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

  private static String generateNewToken() {
      byte[] randomBytes = new byte[24];
      secureRandom.nextBytes(randomBytes);
      return base64Encoder.encodeToString(randomBytes);
  }
}
