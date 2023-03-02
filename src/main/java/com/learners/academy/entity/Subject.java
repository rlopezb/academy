package com.learners.academy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Subject {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String level;
  @OneToMany
  private List<Class> classes;

  public Subject() {

  }

  public Subject(String name, String level, List<Class> classes) {
    this.name = name;
    this.level = level;
    this.classes = classes;
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

  public List<Class> getClasses() {
    return classes;
  }

  public void setClasses(List<Class> classes) {
    this.classes = classes;
  }

  @Override
  public String toString() {
    return "Subject{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", level='" + level + '\'' +
            ", classes=" + classes +
            '}';
  }
}
