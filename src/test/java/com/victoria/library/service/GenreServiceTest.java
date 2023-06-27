package com.victoria.library.service;

import com.victoria.library.entity.Genre;
import com.victoria.library.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @InjectMocks
    GenreService genreService;

    @Mock
    GenreRepository genreRepository;

    Genre genre;
    Optional<Genre> genreOptional;
    Long code = 1L;
    Pageable pageable;
    Page<Genre> genrePage;

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.initMocks(this);
        genre = Genre.builder()
                .code(1L)
                .description("genero")
                .build();
    }

    @Test
    void deveSalvarComSucesso(){
        when(genreRepository.save(genre)).thenReturn(genre);
        Genre genreAtual = genreService.save(genre);
        assertEquals(genre, genreAtual);
    }

    @Test
    void deveRetornarGenreByCode(){
        when(genreRepository.findById(code)).thenReturn(genreOptional);
        Optional<Genre> genreAtual = genreService.findGenreByCode(code);
        assertEquals(genreOptional, genreAtual);
    }

    @Test
    void deveRetornarTodosOsGeneros(){
        when(genreRepository.findAll(pageable)).thenReturn(genrePage);
        Page<Genre> genrePageAtual = genreService.getAllGenre(pageable);
        assertEquals(genrePage, genrePageAtual);
    }

    @Test
    void deveRetornarGeneroPorCode(){
        when(genreRepository.findById(code)).thenReturn(genreOptional);
        Optional<Genre> genreAtual = genreService.getById(genre.getCode());
        assertEquals(genreOptional, genreAtual);
        verifyNoMoreInteractions(genreRepository);
    }
}
