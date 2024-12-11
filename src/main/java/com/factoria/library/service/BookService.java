package com.factoria.library.service;

import com.factoria.library.model.Book;
import com.factoria.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book addBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    public Book updatedBook(long id, Book updatedBook) {
        Optional<Book> foundProduct = bookRepository.findById(id);

        if (foundProduct.isPresent()) {
            Book existingBook = foundProduct.get();

            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setGenre(updatedBook.getGenre());

            return bookRepository.save(existingBook);
        }

        throw  new RuntimeException("Product not with id: " + id);
    }

    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
