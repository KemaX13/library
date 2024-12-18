package com.factoria.library.service;

import com.factoria.library.dto.BookDTO;
import com.factoria.library.model.Book;
import com.factoria.library.model.Member;
import com.factoria.library.repository.BookRepository;
import com.factoria.library.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
        private final MemberRepository memberRepository;

    //CRUD
    public Book addBook(Book newBook) {
        long memberId = newBook.getMember().getId();
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if ((optionalMember.isPresent())) {
            Member member = optionalMember.get();
            newBook.setMember(member);

            return bookRepository.save(newBook);
        }

        throw new RuntimeException("Member not found");
    }

    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookDTO::fromBook).collect(Collectors.toList());
    }

    public Book updateBook(long id, Book updatedBook) {
        Optional<Book> foundProduct = bookRepository.findById(id);

        if (foundProduct.isPresent()) {
            Book existingBook = foundProduct.get();

            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthors(updatedBook.getAuthors());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setGenres(updatedBook.getGenres());

            return bookRepository.save(existingBook);
        }

        throw new RuntimeException("Book not found with id: " + id);
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    //Filter
    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthors(author);
    }

    public List<BookDTO> findBookByGenre(String genre) {
        List<Book> books = bookRepository.findByGenres(genre);
        return books.stream().map(BookDTO::fromBook).collect(Collectors.toList());
    }
}
