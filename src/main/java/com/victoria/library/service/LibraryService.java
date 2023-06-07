package com.victoria.library.service;

import com.victoria.library.entity.Library;
import com.victoria.library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public Library save(Library library){
        return libraryRepository.save((library));
    }

    public List<Library> getAllLibrary(){
        return libraryRepository.findAll();
    }

    public Optional<Library> getByID(Long id){
        return libraryRepository.findById(id);
    }

    public void deleteById(Long id){
        libraryRepository.deleteById(id);
    }
}
