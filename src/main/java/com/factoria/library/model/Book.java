package com.factoria.library.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String title;

    @ElementCollection(targetClass = String.class)
    @Column(nullable=false)
    private List<String> authors;

    @Column(length=200, nullable=false)
    private String description;

    @Column(unique=true, nullable=false)
    private String isbn;

    @ElementCollection(targetClass = String.class)
    @Column(nullable=false)
    private List<String> genres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    public Book(String title, List<String> authors, String description, String isbn, List<String> genres, Member member) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.isbn = isbn;
        this.genres = genres;
        this.member = member;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
