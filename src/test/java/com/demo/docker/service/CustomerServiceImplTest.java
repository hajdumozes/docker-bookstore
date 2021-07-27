package com.demo.docker.service;

import com.demo.docker.entity.Customer;
import com.demo.docker.exception.RecordNotFoundException;
import com.demo.docker.repository.CustomerRepository;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void givenRecordExists_findById_shouldReturnDbResult() {
        // given
        long id = 1;
        Customer customer = new Customer();
        customer.setId(id);
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        // when
        Customer output = customerService.findById(id);

        // then
        assertThat(output).isEqualTo(customer);
    }

    @Test
    void givenRecordIsMissing_findById_shouldThrowException() {
        // given
        long id = 2;
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        // when
        ThrowableAssert.ThrowingCallable findById = () -> customerService.findById(id);

        // then
        assertThatThrownBy(findById).isInstanceOf(RecordNotFoundException.class);
    }
}