package com.demo.docker.service;

import com.demo.docker.entity.Book;

import java.util.List;

public interface BookService {
    void add(Book book);

    List<Book> findAllById(List<Long> ids);
}
