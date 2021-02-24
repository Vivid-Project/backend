package com.vivid.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@Table(name = "themes")
@JsonFilter("themeFilter")
public class Theme {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private String title;

  @ManyToMany
  private Set<Dream> dreams;

  protected Theme() {
  }

  public Theme(User user, String title) {
    this.user = user;
    this.title = title;
    this.dreams = new HashSet<>();

  }

  public long getId() {
    return this.id;
  }

  public User getUser() {
    return this.user;
  }

  public String getTitle() {
    return this.title;
  }

  public Set<Dream> getDreams() {
    return this.dreams;
  }

  public void addDream(Dream dream) {
    dreams.add(dream);
    dream.getThemes().add(this);
  }

}
