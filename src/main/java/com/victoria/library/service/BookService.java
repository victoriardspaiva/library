package com.victoria.library.service;

import com.victoria.library.entity.Book;
import com.victoria.library.entity.GenreEnum;
import com.victoria.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.util.Assert.notNull;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

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

    public Page<Book> searchByTitle(String title) {
        return new PageImpl<>(bookRepository.findByTitleContains(title)
                .stream()
                .map(Book::converter)
                .collect(Collectors.toList()));
    }
}
