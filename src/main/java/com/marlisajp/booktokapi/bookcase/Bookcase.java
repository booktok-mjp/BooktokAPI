package com.marlisajp.booktokapi.bookcase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marlisajp.booktokapi.book.Book;
import com.marlisajp.booktokapi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Bookcase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable(
            name = "bookcase_books",
            joinColumns = @JoinColumn(name = "bookcase_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @Override
    public String toString() {
        return "Bookcase{" +
                "id=" + id +
                ", user=" + user +
                ", books=" + books +
                '}';
    }
}


