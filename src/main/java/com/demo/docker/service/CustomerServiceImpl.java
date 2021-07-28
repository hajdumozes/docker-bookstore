package com.demo.docker.service;

import com.demo.docker.entity.Customer;
import com.demo.docker.exception.RecordNotFoundException;
import com.demo.docker.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    @Override
    public void add(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findById(long id) {
        return customerRepository.findById(id).orElseThrow(() ->
            new RecordNotFoundException(String.format("Customer with id '%d' not found", id)));
    }
}
