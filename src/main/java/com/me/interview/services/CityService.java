package com.me.interview.services;

import com.me.interview.models.City;
import com.me.interview.models.States;
import com.me.interview.repositories.CityRepository;
import com.me.interview.requests.CityRequest;
import com.me.interview.response.CityResponse;
import com.me.interview.response.DeleteResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CityService {

  private final CityRepository repository;

  @Autowired
  public CityService(CityRepository repository) {
    this.repository = repository;
  }

  public Page<CityResponse> getPages(Pageable pagination, String name, States UF) {
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

  public CityResponse create(CityRequest request) {
    City city = new City(request);
    try {
      repository.save(city);
    } catch (DataIntegrityViolationException exception) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "This city is already registered");
    }
    return new CityResponse(city);
  }

  public CityResponse update(CityRequest request, Long id) {
    City city = this.repository.findById(id).get();
    city.setUF(request.getUF());
    city.setName(request.getName());
    repository.save(city);
    return new CityResponse(city);
  }

  public DeleteResponse delete(Long id) {
    City city = repository.findById(id).get();
    this.repository.deleteById(id);
    DeleteResponse response = new DeleteResponse();
    response.setResource(city.getName());
    return response;
  }
}
