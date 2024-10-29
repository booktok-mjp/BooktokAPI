package com.marlisajp.booktokapi.book;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private Long id;
    private String title;
    private String description;
    private String imgUrl;
    private String author_name;
    private String author_imgUrl;
    private String author_bio;
}