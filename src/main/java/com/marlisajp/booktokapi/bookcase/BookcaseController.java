package com.marlisajp.booktokapi.bookcase;

import com.marlisajp.booktokapi.auth.JwtService;
import com.marlisajp.booktokapi.user.User;
import com.marlisajp.booktokapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bookcase")
public class BookcaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<Bookcase> getMyBookcase(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String auth0UserId = jwtService.getAuth0UserId(jwt);
        User user = userService.findOrCreateUser(auth0UserId);
        return ResponseEntity.ok(user.getBookcase());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getBookcaseById(@RequestHeader("Authorization") String token, @PathVariable("userId") Long userId){
        String jwt = token.substring(7);
        String auth0UserId = jwtService.getAuth0UserId(jwt);
        Long authenticatedUserId = userService.findUserByAuth0UserId(auth0UserId);

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
}

