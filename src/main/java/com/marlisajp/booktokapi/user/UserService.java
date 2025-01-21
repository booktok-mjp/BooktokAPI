package com.marlisajp.booktokapi.user;

import com.marlisajp.booktokapi.bookcase.Bookcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOrCreateUser(String auth0UserId) {
        Optional<User> optionalUser = userRepository.findByAuth0UserId(auth0UserId);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            User newUser = User.builder()
                    .auth0UserId(auth0UserId)
                    .bookcase(new Bookcase())
                    .messages(new ArrayList<>())
                    .threads(new ArrayList<>())
                    .build();

            newUser.getBookcase().setUser(newUser);
            return userRepository.save(newUser);
        }
    }

    public Bookcase findUserBookcaseByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getBookcase)
                .orElse(null);
    }

    public Long findUserByAuth0UserId(String auth0UserId) {
        return userRepository.findByAuth0UserId(
                auth0UserId
        ).map(User::getId).orElse(null);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
