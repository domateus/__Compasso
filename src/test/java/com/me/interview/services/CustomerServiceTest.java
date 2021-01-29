package com.me.interview.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import com.me.interview.models.City;
import com.me.interview.models.States;
import com.me.interview.repositories.CityRepository;
import com.me.interview.repositories.CustomerRepository;
import com.me.interview.requests.CustomerRequest;
import com.me.interview.response.CustomerResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  CustomerService service;

  @Mock
  CustomerRepository mockCustomerRepository;

  @Mock
  CityRepository mockCityRepository;

  @Test
  void shouldCreateCustomer() {
    City city = new City();
    city.setId(Long.valueOf(1));
    city.setName("Chapecó");
    city.setUF(States.SC);

    when(mockCityRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(city));
    service = new CustomerService(mockCustomerRepository, mockCityRepository);

    CustomerRequest request = new CustomerRequest();
    request.setName("Mateus");
    request.setGender("Male");
    request.setBirthdate(Date.from(LocalDate.of(2002, 9, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    request.setCity(Long.valueOf(1));

    CustomerResponse customer = service.create(request);

    assertNotNull(customer.getCity());
    assertEquals(customer.getName(), "Mateus");

  }

}
