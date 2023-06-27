package com.victoria.library.repository;

import com.victoria.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByTitleContains(String title);
    List<Book> findByGenreId(Long genreId);
    boolean existsByTitle(String book);
}
