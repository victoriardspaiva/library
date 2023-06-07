package com.victoria.library.contrller;

import com.victoria.library.entity.Book;
import com.victoria.library.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@RequestBody Book library){
        return bookService.save(library);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBook(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getByIdBook(@PathVariable("id") Long id){
        return bookService.getByID(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Long id){
        bookService.getByID(id)
                .map(book -> {
                    bookService.deleteById(book.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("id") Long id, @RequestBody Book book){
        bookService.getByID(id)
                .map(bookBase -> {
                    modelMapper.map(book, bookBase);
                    bookService.save(bookBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }
}
