package com.me.interview.requests;

import java.util.Date;

import com.me.interview.models.Customer;

public class CustomerRequest {

  private String name;
  private String gender;
  private Date birthdate;
  private Long city;

  public Customer update(Customer customer) {
    customer.setName(name);
    customer.setGender(gender);
    customer.setBirthdate(birthdate);

    return customer;
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

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public Long getCity() {
    return city;
  }

  public void setCity(Long city) {
    this.city = city;
  }

}
