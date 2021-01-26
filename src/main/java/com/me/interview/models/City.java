package com.me.interview.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.me.interview.requests.CityRequest;

@Entity
@Table(name = "cities")
public class City {

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private String name;

  private States UF;

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

  public States getUF() {
    return this.UF;
  }

  public void setUF(States UF) {
    this.UF = UF;
  }

  public City(CityRequest request) {
    this.name = request.getName();
    this.UF = request.getUF();
  }

  public City() {
  }

}
