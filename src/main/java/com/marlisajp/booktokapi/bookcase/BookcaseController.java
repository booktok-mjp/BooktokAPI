package com.marlisajp.booktokapi.bookcase;

import com.marlisajp.booktokapi.auth.AuthUtil;
import com.marlisajp.booktokapi.auth.JwtService;
import com.marlisajp.booktokapi.user.User;
import com.marlisajp.booktokapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/bookcase")
public class BookcaseController {

    private final UserService userService;
    private final JwtService jwtService;
    private final BookcaseService bookcaseService;
    private final AuthUtil authUtil;

    public BookcaseController(UserService userService, JwtService jwtService, BookcaseService bookcaseService, AuthUtil authUtil) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.bookcaseService = bookcaseService;
        this.authUtil = authUtil;
    }

    @GetMapping("/me")
    public ResponseEntity<Bookcase> getMyBookcase(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String auth0UserId = jwtService.getAuth0UserId(jwt);
        User user = userService.findOrCreateUser(auth0UserId);
        return ResponseEntity.ok(user.getBookcase());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getBookcaseById(@RequestHeader("Authorization") String token, @PathVariable("userId") Long userId){
        Long authenticatedUserId = authUtil.getAuthenticatedUserId(token);

        if(authenticatedUserId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Bookcase bookcase = userService.findUserBookcaseByUserId(userId);
        if (bookcase != null) {
            return ResponseEntity.ok(bookcase);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Object> deleteBookFromBookcase(@RequestHeader("Authorization") String token, @PathVariable("bookId") Long bookId) {
        Long authenticatedUserId = authUtil.getAuthenticatedUserId(token);

        if(authenticatedUserId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Bookcase bookcase = userService.findUserBookcaseByUserId(authenticatedUserId);
        if (bookcase != null) {
           return ResponseEntity.ok(bookcaseService.deleteBookFromBookcaseById(bookcase.getId(), bookId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/book/{bookId}")
    public ResponseEntity<Bookcase> addBookToBookcase(@RequestHeader("Authorization") String token, @PathVariable("bookId") Long bookId) {
        Long authenticatedUserId = authUtil.getAuthenticatedUserId(token);

        if (authenticatedUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Bookcase bookcase = userService.findUserBookcaseByUserId(authenticatedUserId);
        if (bookcase != null) {
            Optional<Bookcase> updatedBookcase = bookcaseService.addBookToBookcaseById(bookcase.getId(), bookId);
            return updatedBookcase.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

