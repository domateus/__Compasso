package com.me.interview.controllers;

import com.me.interview.models.City;
import com.me.interview.models.Customer;
import com.me.interview.repositories.CityRepository;
import com.me.interview.repositories.CustomerRepository;
import com.me.interview.requests.CustomerRequest;
import com.me.interview.response.CustomerResponse;
import com.me.interview.response.DeleteResponse;
import com.me.interview.requests.CustomerRequest;

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
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CityRepository cityRepository;

  @GetMapping
  public Page<CustomerResponse> list(@PageableDefault(sort = "name") Pageable pagination,
      @RequestParam(required = false) String name, @RequestParam(required = false) Long id) {
    if (name == null && id == null) {
      return CustomerResponse.convert(customerRepository.findAndFetchAll(pagination));
    } else if (name != null) {
      return CustomerResponse.convert(customerRepository.findAndFetchAllByName(name, pagination));
    } else {
      return CustomerResponse.convert(customerRepository.findAndFetchAllById(id, pagination));
    }
  }

  @PostMapping
  public CustomerResponse create(@RequestBody CustomerRequest request) {
    Customer customer = new Customer(request);
    City city = cityRepository.findById(request.getCity()).get();
    customer.setCity(city);
    customerRepository.save(customer);

    return new CustomerResponse(customer);
  }

  @PutMapping("/{id}")
  public CustomerResponse update(@RequestBody CustomerRequest request, @PathVariable Long id) {
    Customer customer = customerRepository.findAndFetchById(id);
    City city = cityRepository.findById(request.getCity()).get();
    customer.setCity(city);
    customer = request.update(customer);
    customerRepository.save(customer);

    return new CustomerResponse(customer);
  }

  @DeleteMapping("/{id}")
  public DeleteResponse delete(@PathVariable Long id) {
    Customer customer = customerRepository.findAndFetchById(id);
    DeleteResponse response = new DeleteResponse();
    response.setResource(customer.getName());
    customerRepository.deleteById(id);

    return response;
  }

}
