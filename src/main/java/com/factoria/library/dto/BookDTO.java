package com.factoria.library.dto;

import com.factoria.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private List<String> authors;
    private String isbn;
    private List<String> genres;

    public static BookDTO fromBook(Book book) {
        if (book == null) return null;

        return new BookDTO(book.getId(), book.getTitle(), book.getAuthors(), book.getIsbn(), book.getGenres());
    }
}
