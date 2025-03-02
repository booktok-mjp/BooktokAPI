package com.marlisajp.booktokapi.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveAndRetrieveBook() {
        Book book = new Book(457543047L,
                "Book Title",
                "Book description",
                "/img/url",
                "author name",
                "author/img/url",
                "author bio");
        Book savedBook = bookRepository.save(book);
        System.out.println("Saved Book: ");
        System.out.println(savedBook);

        assertThat(savedBook.getId()).isNotNull();

        Book retrievedBook = bookRepository.findById(savedBook.getId()).orElse(null);
        System.out.println("Retrieved Book: ");
        System.out.println(retrievedBook);

        assertThat(retrievedBook).isNotNull();
        assertThat(retrievedBook.getTitle()).isEqualTo("Book Title");
    }
}
