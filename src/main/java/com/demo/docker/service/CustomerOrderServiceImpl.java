package com.demo.docker.service;

import com.demo.docker.entity.CustomerOrder;
import com.demo.docker.repository.CustomerOrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CustomerOrderServiceImpl implements CustomerOrderService {
    CustomerOrderRepository customerOrderRepository;

    @Override
    public void add(CustomerOrder customerOrder) {
        customerOrderRepository.save(customerOrder);
    }
}
