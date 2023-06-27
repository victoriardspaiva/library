package com.victoria.library.service;

import com.victoria.library.controller.BookController;
import com.victoria.library.entity.Book;
import com.victoria.library.entity.Genre;
import com.victoria.library.entity.GenreEnum;
import com.victoria.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.victoria.library.entity.GenreEnum.getDescriptionFromCode;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreService genreService;

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Page<Book> getAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Optional<Book> getByID(UUID id){
        return bookRepository.findById(id);
    }

    public void deleteById(UUID id){
        bookRepository.deleteById(id);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContains(title);
    }

    public Optional<Genre> getGenre(Long code) {
        return genreService.findGenreByCode(code);
    }

    public List<Book> searchByGenre(Long genreId) {
        return bookRepository.findByGenreId(genreId);
    }
}
