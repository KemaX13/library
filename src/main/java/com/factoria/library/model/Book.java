package com.factoria.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnoreProperties("members")
    private Member member;
}
