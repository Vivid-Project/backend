package com.vivid.backend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "themes")
public class Theme {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private String title;

  @OneToMany(mappedBy = "theme")
  private Set<DreamTheme> dream_themes;

  protected Theme() {
  }

  public Theme(User user, String title) {
    this.user = user;
    this.title = title;
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
}
