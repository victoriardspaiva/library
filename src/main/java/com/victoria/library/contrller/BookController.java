package com.victoria.library.contrller;

import com.victoria.library.entity.Book;
import com.victoria.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/library", produces = {"application/json"})
@Tag(name = "Project Library")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a book record.", method = "POST")
    public Book save(@RequestBody @Valid Book book){
        return bookService.save(book);
    }

    @GetMapping
    @Operation(summary = "Lists all the books in your library.", method = "GET")
    public ResponseEntity<Page<Book>> getAllBook(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "List a book by id code.", method = "GET")
    public Book getByIdBook(@PathVariable("id") UUID id){
        return bookService.getByID(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by id code.", method = "DELETE")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(value="id") UUID id){
        bookService.getByID(id)
                .map(book -> {
                    bookService.deleteById(book.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update a book by id code.", method = "GET")
    public void updateBook(@PathVariable("id") UUID id, @RequestBody Book book){
        bookService.getByID(id)
                .map(bookBase -> {
                    modelMapper.map(book, bookBase);
                    bookService.save(bookBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }

//    @PutMapping("/")
//    public ResponseEntity<Object> changeStatus(@RequestParam UUID id, @RequestParam Boolean status){
//        Optional<Book> bookO = bookService.getByID(id);
//        if(bookO.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
//        }
////        Book book = new Book();
////        BeanUtils.copyProperties(bookO, book);
//        return ResponseEntity.status(HttpStatus.OK).body(bookService.changeStatus(bookO, status));
//    }
}
