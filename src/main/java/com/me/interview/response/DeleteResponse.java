package com.me.interview.response;

public class DeleteResponse {
  private String resource;

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource + " was successfully deleted";
  }

}
