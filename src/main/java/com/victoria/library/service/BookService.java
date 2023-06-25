package com.victoria.library.service;

import com.victoria.library.entity.Book;
import com.victoria.library.exception.BookException;
import com.victoria.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
        try {
            return bookRepository.findById(id);
        } catch (final Exception e){
            throw new BookException(format("Erro ao buscar livros por id = %s", id), e);
        }
    }

    public void deleteById(UUID id){
        bookRepository.deleteById(id);
    }

//    public Book changeStatus(Optional<Book> book, Boolean status) {
//        book.get().setReadStatus(status);
//        return bookRepository.save(book);
//    }
}
