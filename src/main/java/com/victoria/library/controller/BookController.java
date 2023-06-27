package com.victoria.library.controller;

import com.victoria.library.entity.Book;
import com.victoria.library.entity.Genre;
import com.victoria.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<Object> save(@RequestBody @Valid Book book){
        if(bookService.existsByBook(book.getTitle()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Book already exists");
        Optional<Genre> genre = bookService.getGenre(book.getGenreId());
        book.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        book.setGenre(genre.get());
        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(book));
    }

    @GetMapping
    @Operation(summary = "Lists all the books in your library.", method = "GET")
    public ResponseEntity<Page<Book>> getAllBook(@PageableDefault(
            size = 2,
            sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable){
        Page<Book> bookPage = bookService.getAll(pageable);
        if(!bookPage.isEmpty()){
            for(Book book: bookPage){
                UUID id = book.getId();
                book.add(linkTo(methodOn(BookController.class).getByIdBook(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "List a book by id code.", method = "GET")
    public Book getByIdBook(@PathVariable("id") UUID id){
        Optional<Book> book = bookService.getByID(id);
        Pageable pageable = PageRequest.of(0, 10);
        book.get().add(linkTo(methodOn(BookController.class).getAllBook(pageable)).withRel("Book list"));
        return book.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!"));
    }

    @GetMapping("/search")
    @Operation(summary = "Search by work title.", method = "GET")
    public ResponseEntity<Page<Book>> searchByTitle(@PageableDefault(
            size = 2,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable,
                                                    @RequestParam("title") String title) {
        List<Book> bookList = bookService.searchByTitle(title)
                .stream()
                .map(Book::converter)
                .collect(Collectors.toList());

        Page<Book> bookPage = new PageImpl<>(bookList);

        if(!bookPage.isEmpty()){
            for(Book book: bookPage){
                UUID id = book.getId();
                book.add(linkTo(methodOn(BookController.class).getByIdBook(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(bookPage);
    }

    @GetMapping("/genre")
    @Operation(summary = "Search by book genre.", method = "GET")
    public ResponseEntity<Page<Book>> findByGenre(@PageableDefault(
            size = 2,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable, @RequestParam("genreId")Long genreId){
        List<Book> bookList = bookService.searchByGenre(genreId)
                .stream()
                .map(Book::converter)
                .collect(Collectors.toList());

        Page<Book> bookPage = new PageImpl<>(bookList);

        if(!bookPage.isEmpty()){
            for(Book book: bookPage){
                UUID id = book.getId();
                book.add(linkTo(methodOn(BookController.class).getByIdBook(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookPage);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by id code.", method = "DELETE")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(value="id") UUID id){
        bookService.getByID(id)
                .map(book -> {
                    bookService.deleteById(book.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!"));
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
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!"));
    }
}
