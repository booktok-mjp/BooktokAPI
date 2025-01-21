package com.marlisajp.booktokapi.auth;

import com.marlisajp.booktokapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    /**
     * Extracts the authenticated user's ID based on the JWT token.
     *
     * @param token The JWT token.
     * @return The authenticated user's ID, or null if the user is not found.
     */
    public Long getAuthenticatedUserId(String token) {
        try {
            // Remove Bearer prefix
            String jwt = token.substring(7);
            String auth0UserId = jwtService.getAuth0UserId(jwt);
            return userService.findUserByAuth0UserId(auth0UserId);
        } catch (Exception e) {
            System.err.println("Error getting authenticated user id" + e.getMessage());
            return null;
        }
    }
}
