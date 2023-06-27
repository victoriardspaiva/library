package com.victoria.library.controller;

import com.victoria.library.entity.Genre;
import com.victoria.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/genre", produces = {"application/json"})
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre save(@RequestBody Genre genre){
        return genreService.save(genre);
    }
}
