package com.demo.jdbc.repository;

import com.demo.jdbc.JdbcApplicationTests;
import com.demo.jdbc.domain.Customer;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRepositoryTest extends JdbcApplicationTests {

    @Autowired
    private CustomerRepository customerRepo;

    @Test
    public void createSimpleCustomer() {

        Customer customer = new Customer();
        customer.setDob(LocalDate.of(1904, 5, 14));
        customer.setFirstName("Albert");

        Customer saved = customerRepo.save(customer);

        assertThat(saved.getId()).isNotNull();

        saved.setFirstName("Hans Albert");

        customerRepo.save(saved);

        Optional<Customer> reloaded = customerRepo.findById(saved.getId());

        assertThat(reloaded).isNotEmpty();

        assertThat(reloaded.get().getFirstName()).isEqualTo("Hans Albert");
    }

}
