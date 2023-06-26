package com.victoria.library.repository;

import com.victoria.library.entity.Book;
import com.victoria.library.entity.GenreEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByTitleContains(String title);
    List<Book> findAllByGenreEnum(GenreEnum genre);
}
