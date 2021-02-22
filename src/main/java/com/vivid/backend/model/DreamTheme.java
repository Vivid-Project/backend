package com.vivid.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="dream_themes")
public class DreamTheme {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "dream_id", nullable = false)
  private Dream dream;

  @ManyToOne
  @JoinColumn(name = "theme_id", nullable = false)
  private Theme theme;

  protected DreamTheme() {}

  public DreamTheme(Dream dream, Theme theme) {
    this.dream = dream;
    this.theme = theme;
  }

  public Long getId() {
    return this.id;
  }

  public Dream getDream() {
    return this.dream;
  }

  public Theme getTheme() {
    return this.theme;
  }
}
