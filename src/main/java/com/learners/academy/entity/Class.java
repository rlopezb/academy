package com.learners.academy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Class {
  @Id
  @GeneratedValue
  private Long id;
  private Integer number;
  private Integer room;
  private Date date;
  @OneToMany
  private List<Student> students;

  public Class() {

  }
  public Class(Integer number, Integer room, Date date, List<Student> students) {
    this.number = number;
    this.room = room;
    this.date = date;
    this.students = students;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Integer getRoom() {
    return room;
  }

  public void setRoom(Integer room) {
    this.room = room;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  @Override
  public String toString() {
    return "Class{" +
            "id=" + id +
            ", number=" + number +
            ", room=" + room +
            ", date=" + date +
            ", students=" + students +
            '}';
  }
}
