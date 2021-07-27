package com.demo.docker.service;

import com.demo.docker.entity.Book;
import com.demo.docker.entity.Customer;
import com.demo.docker.entity.CustomerOrder;
import com.demo.docker.repository.CustomerOrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CustomerOrderServiceImpl implements CustomerOrderService {
    CustomerOrderRepository customerOrderRepository;
    CustomerService customerService;
    BookService bookService;

    @Override
    public long add(long customerId, List<Long> bookIds) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(getCustomer(customerId));
        customerOrder.setBooks(findBooks(bookIds));
        CustomerOrder saved = customerOrderRepository.save(customerOrder);
        return saved.getId();
    }

    @Override
    public List<CustomerOrder> findAllBy(long customerId) {
        return customerOrderRepository.findByCustomerId(customerId);
    }

    private Customer getCustomer(long customerId) {
        return customerService.findById(customerId);
    }

    private Set<Book> findBooks(List<Long> bookIds) {
        return Set.copyOf(bookService.findAllById(bookIds));
    }
}
