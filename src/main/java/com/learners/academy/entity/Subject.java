package com.learners.academy.entity;

import javax.persistence.*;

@Entity
public class Subject {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String level;
  @ManyToOne
  @JoinColumn(name = "clazzId", nullable = true)
  private Clazz clazz;
  @ManyToOne
  @JoinColumn(name = "teacherId", nullable = true)
  private Teacher teacher;

  public Subject() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Clazz getClazz() {
    return clazz;
  }

  public void setClazz(Clazz clazz) {
    this.clazz = clazz;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  @Override
  public String toString() {
    return "Subject{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", level='" + level + '\'' +
        ", clazz=" + clazz +
        ", teacher=" + teacher +
        '}';
  }
}
