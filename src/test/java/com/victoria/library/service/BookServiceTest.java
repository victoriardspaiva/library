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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    List<Book> bookList;

    private String title = "Livro";

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        book = Book.builder()
                .title("Livro")
                .author("Fulano")
                .genreEnum(Collections.singletonList(GenreEnum.FILOSOFIA))
                .subTitle("etc")
                .translator("Siclano")
                .pages(3L)
                .readStatus(true)
                .registrationDate(LocalDateTime.now())
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
        Book bookAtual = bookService.save(book);
        assertEquals(book, bookAtual);
    }

    @Test
    void deveRetornarTodosComSucesso(){
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);
        Page<Book> bookPageAtual = bookService.getAll(pageable);
        assertEquals(bookPage, bookPageAtual);
    }

    @Test
    void deveDeletarComSucesso(){
        doNothing().when(bookRepository).deleteById(book.getId());
        bookService.deleteById(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
    }

    @Test
    void deveRetornarLivroPorTitulo(){
        when(bookRepository.findByTitleContains(title)).thenReturn((bookList));
        List<Book> bookListAtual = bookService.searchByTitle(title);
        assertEquals(bookPage, bookListAtual);
    }
}
