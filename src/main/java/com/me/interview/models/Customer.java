package com.me.interview.models;

import java.util.Date;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.me.interview.requests.CustomerRequest;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String gender;

  private Integer age;

  private Date birthdate;

  public Customer(CustomerRequest request) {
    TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
    this.name = request.getName();
    this.gender = request.getGender();
    this.age = request.getAge();
    this.birthdate = request.getBirthdate();
  }

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)

  @JoinColumn(name = "city")
  private City city;

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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City lives_at) {
    this.city = lives_at;
  }

  public Customer() {
  }

}
