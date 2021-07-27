package com.demo.docker.controller;

import com.demo.docker.service.CustomerOrderService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CustomerOrderController {
    CustomerOrderService customerOrderService;

    @PostMapping(value = "/create")
    ResponseEntity<Long> startExport(@Valid @RequestBody CreateOrderRequest request) {
        Long orderId = customerOrderService.add(request.customerId, request.bookIds);
        return ResponseEntity.ok().body(orderId);
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CreateOrderRequest {
        @NotNull
        long customerId;

        @NotNull
        List<Long> bookIds;
    }
}
