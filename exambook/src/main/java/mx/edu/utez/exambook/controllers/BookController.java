package mx.edu.utez.exambook.controllers;

import jakarta.validation.Valid;
import mx.edu.utez.exambook.dtos.BookDto;
import mx.edu.utez.exambook.models.Book;
import mx.edu.utez.exambook.services.BookService;
import mx.edu.utez.exambook.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/book")
@CrossOrigin(origins = {"*"})
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Book>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getActive")
    public ResponseEntity<CustomResponse<List<Book>>>
    getAllActive(){
        return new ResponseEntity<>(
                this.service.getAllActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getAllInactive")
    public ResponseEntity<CustomResponse<List<Book>>>
    getAllInactive(){
        return new ResponseEntity<>(
                this.service.getAllInactive(),
                HttpStatus.OK
        );
    }

    ///// -----------------------------------------

    @GetMapping("/ordered-by-publication-date")
    public CustomResponse<List<Book>> getAllBooksOrderedByPublicationDate() {
        return service.getAllOrderedByPublicationDate();
    }

    @GetMapping("/ordered-by-author")
    public CustomResponse<List<Book>> getAllBooksOrderedByAuthor() {
        return service.getAllOrderedByAuthor();
    }

    @GetMapping("/with-image")
    public CustomResponse<List<Book>> getAllBooksWithImage() {
        return service.getAllWithImage();
    }

    /////
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Book>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    //Insertar un horario
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Book>> insert(
            @RequestBody BookDto dto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(dto.getBooks()),
                HttpStatus.CREATED
        );
    }

    //Modificar
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Book>> update(
            @RequestBody BookDto dto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(dto.getBooks()),
                HttpStatus.CREATED
        );
    }

    //Modificarstatus
    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse<Book>> enableOrDisable(
            @RequestBody Book dto) {
        return new ResponseEntity<>(
                this.service.changeStatus(dto.getId(), dto.getStatus()),
                HttpStatus.OK
        );
    }
}
