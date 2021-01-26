package com.me.interview.repositories;

import com.me.interview.models.City;
import com.me.interview.models.States;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

  Page<City> findAllByName(String name, Pageable pagination);

  Page<City> findAllByUF(States UF, Pageable pagination);

}
