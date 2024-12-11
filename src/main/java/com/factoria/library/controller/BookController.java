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

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @PostMapping("/books")
    public void createBook(@RequestBody Book newBook) {
        bookService.addBook(newBook);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable long id) {
        Optional<Book> foundBook = bookService.findBookById(id);

        return foundBook.map(book -> new ResponseEntity<>(book, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/books/{title}")
    public List<Book> findBookByTitle(@PathVariable String title) {
        return bookService.findBookByTitle(title);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updatedBook(id, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
