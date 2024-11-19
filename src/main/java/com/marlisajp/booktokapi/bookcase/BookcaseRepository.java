package com.marlisajp.booktokapi.bookcase;

import com.marlisajp.booktokapi.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookcaseRepository extends JpaRepository<Bookcase, Long> {

    @Query("SELECT b FROM Bookcase bc JOIN bc.books b WHERE bc.id = :bookcaseId AND b.id = :bookId")
    Optional<Book> findBookById(@Param("bookcaseId") Long bookcaseId, @Param("bookId") Long bookId);
}

