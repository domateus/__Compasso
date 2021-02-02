package com.me.interview.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import com.me.interview.models.City;
import com.me.interview.models.Customer;
import com.me.interview.models.States;
import com.me.interview.repositories.CityRepository;
import com.me.interview.repositories.CustomerRepository;
import com.me.interview.requests.CustomerRequest;
import com.me.interview.response.CustomerResponse;
import com.me.interview.response.DeleteResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceTest {

  private CustomerService service;
  private CustomerRequest request;
  private City city;

  @Mock
  private CustomerRepository mockCustomerRepository;

  @Mock
  private CityRepository mockCityRepository;

  @BeforeEach
  void initEntities() {
    city = new City();
    city.setId(Long.valueOf(1));
    city.setName("Chapecó");
    city.setUF(States.SC);

    when(mockCityRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(city));
    service = new CustomerService(mockCustomerRepository, mockCityRepository);

    request = new CustomerRequest();
    request.setName("Mateus");
    request.setGender("Male");
    request.setBirthdate(Date.from(LocalDate.of(2002, 9, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    request.setCity(Long.valueOf(1));
  }

  @Test
  void shouldCreateCustomer() {
    CustomerResponse customer = this.service.create(this.request);

    assertNotNull(customer.getCity());
    assertEquals(customer.getName(), "Mateus");
  }

  @Test
  void shouldBeAbletoUpdateCustomer() {
    Customer customer = new Customer(request);
    customer.setId(Long.valueOf(2));
    customer.setCity(city);
    when(mockCustomerRepository.findAndFetchById(Long.valueOf(2))).thenReturn(customer);

    CustomerResponse customer2 = service.create(request);

    request.setName("Mateus Antonio");

    service.update(request, Long.valueOf(2));

    assertEquals(customer2.getGender(), customer.getGender());
    assertNotEquals(customer2.getName(), customer.getName());
  }

  @Test
  void shouldBeAbleToDeleteCustomer() {
    Customer customer = new Customer(request);
    customer.setId(Long.valueOf(2));
    customer.setCity(city);
    when(mockCustomerRepository.findAndFetchById(Long.valueOf(2))).thenReturn(customer);

    DeleteResponse response = service.delete(Long.valueOf(2));

    assertEquals(response.getResource(), customer.getName() + " was successfully deleted");

  }

}
