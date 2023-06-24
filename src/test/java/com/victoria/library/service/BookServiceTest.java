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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService service;

    @Mock
    BookRepository repository;

    Book book;

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
        when(repository.findById(book.getId())).thenReturn(Optional.of(book));

        Optional<Book> books = service.getByID(book.getId());

        assertEquals(Optional.of(book), books);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void naoDeveChamarORepositoruCasoIdNulo(){
        final
    }
}
