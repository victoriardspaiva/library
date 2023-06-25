package com.victoria.library.service;

import com.victoria.library.entity.Book;
import com.victoria.library.entity.GenreEnum;
import com.victoria.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static java.lang.String.format;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    Book book;

    Page<Book> bookPage;
    Pageable pageable;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        book = Book.builder()
                .title("Livro")
                .author("Fulano")
                .genreEnum(Collections.singletonList(GenreEnum.FILOSOFIA))
                .build();
    }

    @Test
    void deveBuscarLivrosPorIdComSucesso(){
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Optional<Book> books = bookService.getByID(book.getId());

        assertEquals(Optional.of(book), books);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void deveSalvarLivrosComSucesso(){
        when(bookRepository.save(book)).thenReturn(book);
        book = bookService.save(book);
        assertEquals(book, book);
    }

    @Test
    void deveRetornarTodosComSucesso(){
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);
        bookPage = bookService.getAll(pageable);
        assertEquals(book, book);
    }

    @Test
    void deveDeletarComSucesso(){
        doNothing().when(bookRepository).deleteById(book.getId());
        bookService.deleteById(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
    }
}
