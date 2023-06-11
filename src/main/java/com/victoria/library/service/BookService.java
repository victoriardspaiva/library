package com.victoria.library.service;

import com.victoria.library.entity.Book;
import com.victoria.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> getByID(UUID id){
        return bookRepository.findById(id);
    }

    public void deleteById(UUID id){
        bookRepository.deleteById(id);
    }

//    public Book changeStatus(Optional<Book> book, Boolean status) {
//        book.get().setReadStatus(status);
//        return bookRepository.save(book);
//    }
}
