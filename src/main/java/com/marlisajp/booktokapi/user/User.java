package com.marlisajp.booktokapi.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marlisajp.booktokapi.bookcase.Bookcase;
import com.marlisajp.booktokapi.message.Message;
import com.marlisajp.booktokapi.thread.Thread;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String auth0UserId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Bookcase bookcase;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Thread> threads;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messages;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", auth0UserId='" + auth0UserId + '\'' +
                ", bookcase=" + bookcase +
                ", threads=" + threads +
                ", messages=" + messages +
                '}';
    }
}
