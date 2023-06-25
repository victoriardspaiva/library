package com.victoria.library.controller;

import com.victoria.library.entity.Book;
import com.victoria.library.service.exception.ObjectNotFoudException;
import com.victoria.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
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
        book.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return bookService.save(book);
    }

    @GetMapping
    @Operation(summary = "Lists all the books in your library.", method = "GET")
    public ResponseEntity<Page<Book>> getAllBook(@PageableDefault(
            size = 2,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "List a book by id code.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    public Book getByIdBook(@PathVariable("id") UUID id){
        Optional<Book> book = bookService.getByID(id);
        return book.orElseThrow(()-> new ObjectNotFoudException("Livro não encontrado!"));
    }

    @GetMapping("/search")
    @Operation(summary = "Pesquisa por titilo da obra.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    public ResponseEntity<Page<Book>> searchByTitle(@PageableDefault(
            size = 2,
            sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable,
                                                    @RequestParam("title") String title) {
        Page<Book> pageBook = new PageImpl<>(bookService.searchByTitle(title));
        return ResponseEntity.status(HttpStatus.OK).body(pageBook);
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
}
