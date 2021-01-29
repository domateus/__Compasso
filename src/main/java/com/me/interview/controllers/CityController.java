package com.me.interview.controllers;

import com.me.interview.models.States;
import com.me.interview.requests.CityRequest;
import com.me.interview.response.CityResponse;
import com.me.interview.response.DeleteResponse;
import com.me.interview.services.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

  @Autowired
  private CityService service;

  @GetMapping
  public Page<CityResponse> list(@PageableDefault(sort = "name") Pageable pagination,
      @RequestParam(required = false) String name, @RequestParam(required = false) States UF) {
    return service.getPages(pagination, name, UF);
  }

  @PostMapping
  public CityResponse create(@RequestBody CityRequest request) {
    return service.create(request);
  }

  @PutMapping("/{id}")
  public CityResponse update(@PathVariable Long id, @RequestBody CityRequest request) {
    return service.update(request, id);
  }

  @DeleteMapping("/{id}")
  public DeleteResponse delete(@PathVariable Long id) {
    return service.delete(id);
  }

}
