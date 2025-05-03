package com.esprit.pidev.codingfactory.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;
    @Column
    private String user_id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Column
    private String message;
    @Column
    private boolean isRead;
    @Column
    private LocalDateTime createdAt;
    @Column
    @Enumerated(EnumType.STRING)
    private NotificationType type;

}