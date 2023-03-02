package com.learners.academy.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String lastName;
  private String email;
  private String phone;
  @OneToMany(mappedBy = "teacher")
  private List<Clazz> clazzes;

  public Teacher() {

  }

  public Teacher(String name, String lastName, String email, String phone, List<Clazz> clazzes) {
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
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

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Clazz> getClazzes() {
    return clazzes;
  }

  public void setClazzes(List<Clazz> clazzes) {
    this.clazzes = clazzes;
  }

  @Override
  public String toString() {
    return "Teacher{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", clazzes=" + clazzes +
            '}';
  }
}
