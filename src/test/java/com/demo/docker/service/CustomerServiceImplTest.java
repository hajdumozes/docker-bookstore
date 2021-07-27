package com.demo.docker.service;

import com.demo.docker.entity.Customer;
import com.demo.docker.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void init() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void add_shouldInvokeRepositorySave() {
        // given
        Customer customer = new Customer();

        // when
        customerService.add(customer);

        // then
        verify(customerRepository).save(customer);
    }
}