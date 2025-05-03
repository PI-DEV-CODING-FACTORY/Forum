package com.esprit.pidev.codingfactory.model;



import com.esprit.pidev.codingfactory.Entity.Post;


import lombok.Data;


@Data
public class CommentNotificationRequest {
    //  private User user;
    private String user_id;
    private Post post;
    private String commenterName;
}
