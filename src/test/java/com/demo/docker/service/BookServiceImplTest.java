package com.demo.docker.service;

import com.demo.docker.entity.Book;
import com.demo.docker.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void init() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void add_shouldInvokeRepositorySave() {
        // given
        Book book = new Book();

        // when
        bookService.add(book);

        // then
        verify(bookRepository).save(book);
    }

    @Test
    void findAllById_shouldReturnDbResult() {
        // given
        List<Long> input = List.of(1L, 2L);
        Book book = new Book();
        Book otherBook = new Book();
        List<Book> expected = List.of(book, otherBook);
        Mockito.when(bookRepository.findAllById(input)).thenReturn(expected);

        // when
        List<Book> output = bookService.findAllById(input);

        // then
        assertThat(output).isEqualTo(expected);
    }
}