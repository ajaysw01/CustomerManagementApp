package com.aj.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aj.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
