package com.demo.docker.service;

import com.demo.docker.entity.Book;
import com.demo.docker.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class BookServiceImpl implements BookService {
    BookRepository bookRepository;

    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }
}
