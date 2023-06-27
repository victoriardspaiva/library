package com.victoria.library.controller;

import com.victoria.library.entity.Genre;
import com.victoria.library.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/genre", produces = {"application/json"})
@Tag(name = "Genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a genre record.", method = "POST")
    public ResponseEntity<Object> save(@RequestBody Genre genre) {
        if(genreService.existsByGenre(genre.getDescription()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Genre already exists");
        return ResponseEntity.status(HttpStatus.OK).body(genreService.save(genre));
    }

    @GetMapping
    @Operation(summary = "List of all registered genres.", method = "GET")
    public ResponseEntity<Page<Genre>> getAllGenre(@PageableDefault
                                                           (size = 10,
                                                                   sort = "code",
                                                                   direction = Sort.Direction.DESC) Pageable pageable){
        Page<Genre> genresPage = genreService.getAllGenre(pageable);
        if (!genresPage.isEmpty()){
            for(Genre genre: genresPage){
                Long code = genre.getCode();
                genre.add(linkTo(methodOn(GenreController.class).getByCode(code)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(genresPage);
    }

    @GetMapping("/{code}")
    @Operation(summary = "Search genre by code.", method = "GET")
    public Genre getByCode(@PathVariable("code") Long code){
        Optional<Genre> genre = genreService.getById(code);
        Pageable pageable = PageRequest.of(0,10);
        genre.get().add(linkTo(methodOn(GenreController.class).getAllGenre(pageable)).withRel("Genre List"));
        return genre.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found!"));
    }
}
