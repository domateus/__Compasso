package com.me.interview.response;

import com.me.interview.models.City;

import org.springframework.data.domain.Page;

public class CityResponse {

  private String name;
  private String uf;

  public CityResponse(City city) {
    this.name = city.getName();
    this.uf = city.getUF().name();
  }

  public static Page<CityResponse> convert(Page<City> cities) {
    return cities.map(CityResponse::new);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

}
