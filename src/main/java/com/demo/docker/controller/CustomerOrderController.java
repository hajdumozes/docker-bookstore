package com.demo.docker.controller;

import com.demo.docker.entity.Book;
import com.demo.docker.entity.CustomerOrder;
import com.demo.docker.service.CustomerOrderService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/list/{customerId}")
    ResponseEntity<List<CustomerOrderResponse>> list(@PathVariable("customerId") long customerId) {
        List<CustomerOrder> orders = customerOrderService.findAllBy(customerId);
        List<CustomerOrderResponse> response = orders.stream().map(this::of).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") long id) {
        customerOrderService.delete(id);
        return ResponseEntity.ok().build();
    }

    private CustomerOrderResponse of(CustomerOrder customerOrder) {
        CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
        customerOrderResponse.setOrderId(customerOrder.getId());
        List<Long> bookIds = customerOrder.getBooks().stream().map(Book::getId).collect(Collectors.toList());
        customerOrderResponse.setBookIds(bookIds);
        return customerOrderResponse;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CreateOrderRequest {
        @NotNull
        long customerId;

        @NotNull
        List<Long> bookIds;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CustomerOrderResponse {
        long orderId;
        List<Long> bookIds;
    }
}
