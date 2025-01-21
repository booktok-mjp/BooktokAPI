package com.marlisajp.booktokapi.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marlisajp.booktokapi.thread.Thread;
import com.marlisajp.booktokapi.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "thread_id", nullable = false)
    @JsonIgnore
    private Thread thread;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", thread=" + thread +
                '}';
    }
}
