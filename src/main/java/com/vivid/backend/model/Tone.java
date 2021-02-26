package com.vivid.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tones")
public class Tone {
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "dream_id", nullable = false)
  private Dream dream;

  private String title;

  private int magnitude;

  protected Tone() {}
  
  public Tone(String title, int magnitude, Dream dream) {
    this.title =  title;
    this.magnitude = magnitude;
    this.dream = dream;
  }

  public Long getId() {
    return this.id;
  }

  public String getTone() {
    return this.title;
  }

  public int getMagnitude() {
    return this.magnitude;
  }

  public Dream getDream() {
    return this.dream;
  }

  public void setDream(Dream dream) {
    this.dream = dream;
  }
}
