package com.me.interview.controllers;

import com.me.interview.models.City;
import com.me.interview.models.States;
import com.me.interview.repositories.CityRepository;
import com.me.interview.requests.CityRequest;
import com.me.interview.response.CityResponse;
import com.me.interview.response.DeleteResponse;

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
  private CityRepository repository;

  @GetMapping
  public Page<CityResponse> list(@PageableDefault(sort = "name") Pageable pagination,
      @RequestParam(required = false) String name, @RequestParam(required = false) States UF) {
    if (name == null && UF == null) {
      Page<City> cities = repository.findAll(pagination);
      return CityResponse.convert(cities);
    } else if (name != null) {
      Page<City> cities = repository.findAllByName(name, pagination);
      return CityResponse.convert(cities);
    } else {
      Page<City> cities = repository.findAllByUF(UF, pagination);
      return CityResponse.convert(cities);
    }

  }

  @PostMapping
  public CityResponse create(@RequestBody CityRequest request) {
    City city = new City(request);
    this.repository.save(city);
    return new CityResponse(city);
  }

  @PutMapping("/{id}")
  public CityResponse update(@PathVariable Long id, @RequestBody CityRequest request) {
    City city = this.repository.findById(id).get();
    city.setUF(request.getUF());
    city.setName(request.getName());

    this.repository.save(city);

    return new CityResponse(city);
  }

  @DeleteMapping("/{id}")
  public DeleteResponse delete(@PathVariable Long id) {
    City city = repository.findById(id).get();
    this.repository.deleteById(id);
    DeleteResponse response = new DeleteResponse();
    response.setResource(city.getName());
    return response;
  }

}
