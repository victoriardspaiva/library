package com.victoria.library.contrller;

import com.victoria.library.entity.Library;
import com.victoria.library.service.LibraryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Library save(@RequestBody Library library){
        return libraryService.save(library);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Library> getAllLibrary(){
        return libraryService.getAllLibrary();
    }
    // TODO na primeira vez que rodei o getall com o banco vazio ao invez de retonar vazio, deu erro 500 no repository null

    @GetMapping("/{id}")
    public Library getByIdLibrary(@PathVariable("id") Long id){
        return libraryService.getByID(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLibrary(@PathVariable("id") Long id){
        libraryService.getByID(id)
                .map(library -> {
                    libraryService.deleteById(library.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLibrary(@PathVariable("id") Long id, @RequestBody Library library){
        libraryService.getByID(id)
                .map(libraryBase -> {
                    modelMapper.map(library, libraryBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
    }
}
