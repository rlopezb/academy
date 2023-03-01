package com.learners.academy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Class {
  @Id
  @GeneratedValue
  private Long id;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
