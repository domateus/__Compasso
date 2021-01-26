package com.me.interview.repositories;

import com.me.interview.models.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Page<Customer> findAllByName(String name, Pageable pagination);

  Page<Customer> findAllById(Long id, Pageable pagination);

  @Query(value = "SELECT c FROM Customer c LEFT JOIN FETCH c.city WHERE c.id = :id")
  Customer findAndFetchById(@Param("id") Long id);

  @Query(value = "SELECT c FROM Customer c LEFT JOIN FETCH c.city", countQuery = "SELECT count(c) FROM Customer c")
  Page<Customer> findAndFetchAll(Pageable pagination);

  @Query(value = "SELECT c FROM Customer c LEFT JOIN FETCH c.city WHERE c.name = :name", countQuery = "SELECT count(c) FROM Customer c")
  Page<Customer> findAndFetchAllByName(@Param("name") String name, Pageable pagination);

  @Query(value = "SELECT c FROM Customer c LEFT JOIN FETCH c.city WHERE c.id = :id", countQuery = "SELECT count(c) FROM Customer c")
  Page<Customer> findAndFetchAllById(@Param("id") Long id, Pageable pagination);
}
