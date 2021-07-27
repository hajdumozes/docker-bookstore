package com.demo.docker.service;

import java.util.List;

public interface CustomerOrderService {
    long add(long customerId, List<Long> bookIds);
}
