package com.me.interview.requests;

import com.me.interview.models.States;

public class CityRequest {
  private String name;
  private States UF;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public States getUF() {
    return UF;
  }

  public void setUF(States UF) {
    this.UF = UF;
  }
}
