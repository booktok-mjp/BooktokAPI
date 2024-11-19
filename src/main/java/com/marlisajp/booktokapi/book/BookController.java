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
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@RequestHeader("Authorization") String token, @PathVariable("bookId") Long bookId) {
        Long authenticatedUserId = getAuthenticatedUserId(token);

        if(authenticatedUserId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Book book = bookService.findBookById(bookId);
        if(book == null){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(book);
    }

    private Long getAuthenticatedUserId(String token) {
        String jwt = token.substring(7);
        String auth0UserId = jwtService.getAuth0UserId(jwt);
        System.out.println(auth0UserId);
        return userService.findUserByAuth0UserId(auth0UserId);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }
}
