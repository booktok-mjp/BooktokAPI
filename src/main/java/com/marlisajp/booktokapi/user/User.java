package com.marlisajp.booktokapi.user;

import com.marlisajp.booktokapi.bookcase.Bookcase;
import jakarta.persistence.*;
import lombok.*;

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
}
