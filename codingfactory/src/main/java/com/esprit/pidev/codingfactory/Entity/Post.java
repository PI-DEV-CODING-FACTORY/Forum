package com.esprit.pidev.codingfactory.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

enum TypePost {
    question,
    response
}

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;
    @Column
    String title;
    @Column
    String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    String createdAt;
    @Enumerated(EnumType.STRING)
    @Column
    TypePost type;
    @Column
    int parent_post_id;
    @Column
    String tags;
    @Column
    String image;

    @Column
    int reportCount;
    @Column
    Integer bestAnswerId;
    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Notification> notifications;
}