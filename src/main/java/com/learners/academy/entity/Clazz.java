package com.learners.academy.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Clazz {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String line;
  private String level;

  @OneToMany(mappedBy = "clazz")
  private List<Student> students;
  @OneToMany(mappedBy = "clazz")
  private List<Subject> subjects;

  public Clazz() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String group) {
    this.line = group;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }

  @Override
  public String toString() {
    return "Clazz{" +
            "id=" + id +
            ", line='" + line + '\'' +
            ", level='" + level + '\'' +
            '}';
  }
}
