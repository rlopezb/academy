package com.learners.academy.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Clazz {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer number;
  private Integer room;
  private Date date;
  @OneToMany(mappedBy = "clazz")
  private List<Student> students;
  @ManyToOne
  @JoinColumn(name = "subjectId", nullable = true)
  private Subject subject;
  @ManyToOne
  @JoinColumn(name = "teacherId", nullable = true)
  private Teacher teacher;

  public Clazz() {

  }

  public Clazz(Integer number, Integer room, Date date, List<Student> students, Subject subject) {
    this.number = number;
    this.room = room;
    this.date = date;
    this.students = students;
    this.subject = subject;
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

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }
}
