package com.marlisajp.booktokapi.book;

import com.marlisajp.booktokapi.auth.JwtService;
import com.marlisajp.booktokapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {
           Book book = bookService.findBookById(bookId);

           if(book == null){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
           }

           return ResponseEntity.ok(book);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }
}
