package com.victoria.library.service;

import com.victoria.library.entity.Book;
import com.victoria.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book library){
        return bookRepository.save(library);
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> getByID(Long id){
        return bookRepository.findById(id);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
}
