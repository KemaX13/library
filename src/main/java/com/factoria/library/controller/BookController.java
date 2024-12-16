package com.factoria.library.controller;

import com.factoria.library.model.Book;
import com.factoria.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //CRUD
     @PostMapping("/books")
     public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
         try {
             Book createdBook = bookService.addBook(newBook);
             return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
     }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @PutMapping("/books/id/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(id, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/id/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

    //Filter
    @GetMapping("/books/id/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable long id) {
        Optional<Book> foundBook = bookService.findBookById(id);

        return foundBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/books/title/{title}")
    public List<Book> findBookByTitle(@PathVariable String title) {
        return bookService.findBookByTitle(title);
    }

    @GetMapping("/books/author/{author}")
    public List<Book> findBookByAuthor(@PathVariable String author) {
        return bookService.findBookByAuthor(author);
    }

    @GetMapping("/books/genre/{genre}")
    public List<Book> findBookByGenre(@PathVariable String genre) {
        return bookService.findBookByGenre(genre);
    } 
}
