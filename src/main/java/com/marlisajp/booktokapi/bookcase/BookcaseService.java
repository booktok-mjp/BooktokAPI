package com.marlisajp.booktokapi.bookcase;

import com.marlisajp.booktokapi.book.Book;
import com.marlisajp.booktokapi.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookcaseService {
    @Autowired
    private BookcaseRepository bookcaseRepository;

    @Autowired
    private BookRepository bookRepository;

    public Optional<Bookcase> deleteBookFromBookcaseById(Long bookcaseId, Long bookId) {
        Optional<Bookcase> optionalBookcase = bookcaseRepository.findById(bookcaseId);

        if (optionalBookcase.isPresent()) {
            Bookcase bookcase = optionalBookcase.get();
            bookcase.getBooks().removeIf(book -> book.getId().equals(bookId));
            return Optional.of(bookcaseRepository.save(bookcase));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Bookcase> addBookToBookcaseById(Long bookcaseId, Long bookId) {
        Optional<Bookcase> optionalBookcase = bookcaseRepository.findById(bookcaseId);

        if (optionalBookcase.isPresent()) {
            Bookcase bookcase = optionalBookcase.get();
            Optional<Book> optionalBook = bookRepository.findById(bookId);

            if (optionalBook.isPresent() && !bookcase.getBooks().contains(optionalBook.get())) {
                bookcase.getBooks().add(optionalBook.get());
                return Optional.of(bookcaseRepository.save(bookcase));
            }
        }
        return Optional.empty();
    }
}
