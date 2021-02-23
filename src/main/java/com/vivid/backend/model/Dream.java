package com.vivid.backend.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="dreams")
public class Dream {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private String date;

  private String title;

  @Column(columnDefinition="TEXT")
  private String description;

  private String emotion;

  @OneToMany(mappedBy = "dream")
  private Set<DreamTheme> dream_themes;

  protected Dream() {}

  public Dream(String date, String title, String description, String emotion, User user) {
    this.date = date;
    this.title = title;
    this.description = description;
    this.emotion = emotion;
    this.user = user;
  }

  public Long getId() {
    return this.id;
  }

  public String getDate() {
    return this.date;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public String getEmotion() {
    return this.emotion;
  }

  public User getUser() {
    return this.user;
  }
}
