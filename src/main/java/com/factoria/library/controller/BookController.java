package com.factoria.library.controller;

import com.factoria.library.dto.BookDTO;
import com.factoria.library.exception.ObjectNotFoundException;
import com.factoria.library.model.Book;
import com.factoria.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    //CRUD
    //Create
    @PostMapping("/api/books")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
         try {
             Book createdBook = bookService.addBook(newBook);
             return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
     }

    //Read
    @GetMapping("/api/books")
    public List<BookDTO> getAllBooks() {
        return bookService.getAll();
    }

    //Update
    @PutMapping("/api/books/id={id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(id, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete
    @DeleteMapping("/api/books/id={id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

    //Filter
    //Find by id
    @GetMapping("/api/books/id={id}")
    public ResponseEntity<Book> findBookById(@PathVariable long id) {
        Optional<Book> foundBook = bookService.findBookById(id);

        if (foundBook.isPresent()) return new ResponseEntity<>(foundBook.get(), HttpStatus.FOUND);
        throw new ObjectNotFoundException("Book", id);
    }

    //Find by title
    @GetMapping("/api/books/title={title}")
    public List<Book> findBookByTitle(@PathVariable String title) {
        return bookService.findBookByTitle(title);
    }

    @GetMapping("/api/books/author={author}")
    public List<Book> findBookByAuthor(@PathVariable String author) {
        return bookService.findBookByAuthor(author);
    }

    @GetMapping("/api/books/genre={genre}")
    public List<BookDTO> findBookByGenre(@PathVariable String genre) {
        return bookService.findBookByGenre(genre);
    } 
}
