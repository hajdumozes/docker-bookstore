package com.demo.docker.service;

import com.demo.docker.entity.CustomerOrder;
import com.demo.docker.repository.CustomerOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerOrderServiceImplTest {
    CustomerOrderService customerOrderService;

    @Mock
    CustomerOrderRepository customerOrderRepository;

    @BeforeEach
    void init() {
        customerOrderService = new CustomerOrderServiceImpl(customerOrderRepository);
    }

    @Test
    void add_shouldInvokeRepositorySave() {
        // given
        CustomerOrder customerOrder = new CustomerOrder();

        // when
        customerOrderService.add(customerOrder);

        // then
        verify(customerOrderRepository).save(customerOrder);
    }
}