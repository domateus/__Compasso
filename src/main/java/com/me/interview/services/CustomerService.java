package com.me.interview.services;

import com.me.interview.models.City;
import com.me.interview.models.Customer;
import com.me.interview.repositories.CityRepository;
import com.me.interview.repositories.CustomerRepository;
import com.me.interview.requests.CustomerRequest;
import com.me.interview.response.CustomerResponse;
import com.me.interview.response.DeleteResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private CustomerRepository customerRepository;

  private CityRepository cityRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository, CityRepository cityRepository) {
    this.customerRepository = customerRepository;
    this.cityRepository = cityRepository;
  }

  public Page<CustomerResponse> getPages(Pageable pagination, String name, Long id) {
    if (name == null && id == null) {
      return CustomerResponse.convert(customerRepository.findAndFetchAll(pagination));
    } else if (id != null) {
      return CustomerResponse.convert(customerRepository.findAndFetchAllById(id, pagination));
    } else {
      return CustomerResponse.convert(customerRepository.findAndFetchAllByName(name, pagination));
    }
  }

  public CustomerResponse create(CustomerRequest request) {
    Customer customer = new Customer(request);
    City city = cityRepository.findById(request.getCity()).get();

    customer.setCity(city);
    customerRepository.save(customer);

    return new CustomerResponse(customer);
  }

  public CustomerResponse update(CustomerRequest request, Long id) {
    Customer customer = customerRepository.findAndFetchById(id);
    City city = cityRepository.findById(request.getCity()).get();
    customer.setCity(city);
    customer = request.update(customer);
    customerRepository.save(customer);

    return new CustomerResponse(customer);
  }

  public DeleteResponse delete(Long id) {
    Customer customer = customerRepository.findAndFetchById(id);
    DeleteResponse response = new DeleteResponse();
    response.setResource(customer.getName());
    customerRepository.deleteById(id);

    return response;
  }

}
