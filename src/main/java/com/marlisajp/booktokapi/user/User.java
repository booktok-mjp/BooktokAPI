package com.marlisajp.booktokapi.user;

import com.marlisajp.booktokapi.bookcase.Bookcase;
import com.marlisajp.booktokapi.message.Message;
import com.marlisajp.booktokapi.thread.Thread;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "_user")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String auth0UserId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Bookcase bookcase;

    @OneToMany
    private List<Thread> threads;

    @OneToMany
    private List<Message> messages;
}
