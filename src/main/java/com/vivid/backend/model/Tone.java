package com.vivid.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@Table(name="tones")
@JsonFilter("toneFilter")
public class Tone {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "dream_id", nullable = false)
  private Dream dream;

  private String title;

  private long magnitude;

  protected Tone() {}

  public Tone(String title, Long long1, Dream dream) {
    this.title =  title;
    this.magnitude = long1;
    this.dream = dream;
    this.dream.addTone(this);
  }

  public Long getId() {
    return this.id;
  }

  public String getTone() {
    return this.title;
  }

  public long getMagnitude() {
    return this.magnitude;
  }

  public Dream getDream() {
    return this.dream;
  }

  public void setDream(Dream dream) {
    this.dream = dream;
  }
}
