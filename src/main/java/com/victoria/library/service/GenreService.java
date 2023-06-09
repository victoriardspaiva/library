package com.victoria.library.service;

import com.victoria.library.entity.Genre;
import com.victoria.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre save(Genre genre){
        return genreRepository.save(genre);
    }

    public Optional<Genre> findGenreByCode(Long code) {
        return genreRepository.findById(code);
    }

    public Page<Genre> getAllGenre(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    public Optional<Genre> getById(Long code) {
        return genreRepository.findById(code);
    }

    public boolean existsByGenre(String description) {
        return genreRepository.existsByDescription(description);
    }
}
