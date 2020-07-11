package com.demo.jdbc.repository;

import com.demo.jdbc.domain.Customer;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select * from customer ")
    @Override
    List<Customer> findAll();

}
