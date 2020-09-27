package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Customer;

public interface ICustomerRepo extends CrudRepository<Customer, Integer> {

	boolean existsByName(String name);

	boolean existsByNameAndAge(String name, int age);

}
