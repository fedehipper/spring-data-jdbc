package com.demo.jdbc.repository;

import com.demo.jdbc.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {


}
