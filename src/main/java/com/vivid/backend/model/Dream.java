package com.vivid.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.vivid.backend.serializers.ToneSerializer;

import org.springframework.http.converter.json.MappingJacksonValue;

@Entity
@Table(name="dreams")
@JsonFilter("dreamFilter")
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

  @OneToMany(mappedBy="dream")
  private Set<Tone> tones = new HashSet<>();

  @ManyToMany
  private Set<Theme> themes = new HashSet<>();

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

  public MappingJacksonValue getToneAnalysis() {
    return ToneSerializer.serializeToneList(tones);
  }

  public Set<Theme> getThemes() {
    return themes;
  }

  public Set<Tone> getTones() {
    return tones;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void addTheme(Theme theme) {
    themes.add(theme);
    theme.getDreams().add(this);
  }
}
