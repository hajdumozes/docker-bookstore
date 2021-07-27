package com.demo.docker.service;

import com.demo.docker.entity.Book;
import com.demo.docker.entity.Customer;
import com.demo.docker.entity.CustomerOrder;
import com.demo.docker.repository.CustomerOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerOrderServiceImplTest {
    CustomerOrderService customerOrderService;

    @Mock
    CustomerOrderRepository customerOrderRepository;

    @Mock
    CustomerService customerService;

    @Mock
    BookService bookService;

    @BeforeEach
    void init() {
        customerOrderService = new CustomerOrderServiceImpl(customerOrderRepository, customerService, bookService);
    }

    @Test
    void add_shouldInvokeRepositorySave() {
        // given
        long customerId = 1L;
        Customer customer = new Customer();
        customer.setName("John Doe");
        when(customerService.findById(customerId)).thenReturn(customer);

        List<Long> bookIds = List.of(1L, 2L);
        Book firstBook = new Book();
        firstBook.setTitle("The Little Prince");
        Book secondBook = new Book();
        firstBook.setTitle("Clean Code");
        when(bookService.findAllById(bookIds)).thenReturn(List.of(firstBook, secondBook));

        CustomerOrder assembled = new CustomerOrder();
        assembled.setCustomer(customer);
        assembled.setBooks(Set.of(firstBook, secondBook));

        CustomerOrder expected = new CustomerOrder();
        expected.setId(5L);
        when(customerOrderRepository.save(assembled)).thenReturn(expected);

        // when
        customerOrderService.add(customerId, bookIds);

        // then
        verify(customerOrderRepository).save(assembled);
    }

    @Test
    void add_shouldReturnWithResponseId() {
        // given
        long customerId = 1;
        List<Long> bookIds = List.of(1L);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(2);

        Mockito.when(customerOrderRepository.save(any(CustomerOrder.class))).thenReturn(customerOrder);

        // when
        long output = customerOrderService.add(customerId, bookIds);

        // then
        assertThat(output).isEqualTo(customerOrder.getId());
    }

    @Test
    void findAllBy_shouldReturnWithDbResponse() {
        // given
        long customerId = 1L;
        List<CustomerOrder> expected = List.of();
        when(customerOrderRepository.findByCustomerId(customerId)).thenReturn(expected);

        // when
        List<CustomerOrder> output = customerOrderService.findAllBy(customerId);

        // then
        assertThat(output).isEqualTo(expected);
    }
}