package com.demo.docker.repository;

import com.demo.docker.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}
