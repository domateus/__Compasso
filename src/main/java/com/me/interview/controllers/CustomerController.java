package com.me.interview.controllers;

import com.me.interview.requests.CustomerRequest;
import com.me.interview.response.CustomerResponse;
import com.me.interview.response.DeleteResponse;
import com.me.interview.services.CustomerService;

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
  private CustomerService service;

  @GetMapping
  public Page<CustomerResponse> list(@PageableDefault(sort = "name") Pageable pagination,
      @RequestParam(required = false) String name, @RequestParam(required = false) Long id) {
    return service.getPages(pagination, name, id);
  }

  @PostMapping
  public CustomerResponse create(@RequestBody CustomerRequest request) {
    return service.create(request);
  }

  @PutMapping("/{id}")
  public CustomerResponse update(@RequestBody CustomerRequest request, @PathVariable Long id) {
    return service.update(request, id);
  }

  @DeleteMapping("/{id}")
  public DeleteResponse delete(@PathVariable Long id) {
    return service.delete(id);
  }

}
