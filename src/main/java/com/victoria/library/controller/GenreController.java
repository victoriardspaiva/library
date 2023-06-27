package com.victoria.library.controller;

import com.victoria.library.entity.Genre;
import com.victoria.library.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/genre", produces = {"application/json"})
@Tag(name = "Genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a genre record.", method = "POST")
    public Genre save(@RequestBody Genre genre){
        return genreService.save(genre);
    }
}
