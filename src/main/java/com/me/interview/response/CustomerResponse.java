package com.me.interview.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.me.interview.models.Customer;

import org.springframework.data.domain.Page;

public class CustomerResponse {

  private String name;
  private Integer age;
  private String gender;
  private String birthdate;
  private String city;

  private final static String pattern = "dd/MM/yyyy";
  private final static DateFormat formater = new SimpleDateFormat(pattern);

  public CustomerResponse(Customer customer) {
    this.name = customer.getName();
    this.age = customer.getAge();
    this.gender = customer.getGender();
    this.birthdate = formater.format(customer.getBirthdate());
    this.city = customer.getCity().getName();
  }

  public static Page<CustomerResponse> convert(Page<Customer> customers) {
    return customers.map(CustomerResponse::new);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

}
