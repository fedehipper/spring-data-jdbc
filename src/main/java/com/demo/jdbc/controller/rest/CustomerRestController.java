package com.demo.jdbc.controller.rest;

import com.demo.jdbc.domain.Customer;
import com.demo.jdbc.repository.CustomerRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

    private final CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/api/customer")
    public List<Customer> buscarTodos() {
        return customerRepository.findAll();
    }

    @PostMapping("/api/customer")
    public Customer guardar(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

}
