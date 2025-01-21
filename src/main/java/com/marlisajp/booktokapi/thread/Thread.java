package com.marlisajp.booktokapi.thread;

import com.marlisajp.booktokapi.message.Message;
import com.marlisajp.booktokapi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subject;
    // temporary, create relationship with Book model and use that instead
    private String book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Message> messages;

    @Override
    public String toString() {
        return "Thread{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", book='" + book + '\'' +
                ", user=" + user +
                ", messages=" + messages +
                '}';
    }
}
