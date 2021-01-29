package com.me.interview.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.me.interview.models.City;
import com.me.interview.models.States;
import com.me.interview.repositories.CityRepository;
import com.me.interview.requests.CityRequest;
import com.me.interview.response.CityResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

  CityService service;

  @Mock
  CityRepository mockRepository;

  @BeforeEach
  void initService() {
    service = new CityService(mockRepository);
  }

  @Test
  void shouldCreateNewCity() {
    when(mockRepository.save(any())).thenReturn(new City());
    CityRequest request = new CityRequest();
    request.setUF(States.SC);
    request.setName("Chapecó");

    CityResponse response = service.create(request);

    assertEquals(response.getName(), response.getName());
    assertEquals(response.getUf(), response.getUf());
  }

  @Test
  void shouldNotCreateSameCity() {
    when(mockRepository.save(any(City.class))).thenAnswer(new Answer<City>() {
      private int count = 0;

      public City answer(InvocationOnMock invocation) {
        if (count++ == 0)
          return new City();

        throw new DataIntegrityViolationException("This city is already registered");
      }

    });
    CityRequest request = new CityRequest();
    request.setUF(States.SC);
    request.setName("Chapecó");

    service.create(request);

    assertThrows(ResponseStatusException.class, () -> {
      service.create(request);
    });

  }

}
