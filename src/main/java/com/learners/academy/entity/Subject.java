package com.learners.academy.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Subject {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String level;
  @OneToMany(mappedBy = "subject")
  private List<Clazz> clazzes;

  public Subject() {

  }

  public Subject(String name, String level, List<Clazz> clazzes) {
    this.name = name;
    this.level = level;
    this.clazzes = clazzes;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public List<Clazz> getClazzes() {
    return clazzes;
  }

  public void setClazzes(List<Clazz> clazzes) {
    this.clazzes = clazzes;
  }

  @Override
  public String toString() {
    return "Subject{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", level='" + level + '\'' +
            ", clazzes=" + clazzes +
            '}';
  }
}
