package com.demo.docker.service;

import com.demo.docker.entity.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {
    long add(long customerId, List<Long> bookIds);

    List<CustomerOrder> findAllBy(long customerId);
}
