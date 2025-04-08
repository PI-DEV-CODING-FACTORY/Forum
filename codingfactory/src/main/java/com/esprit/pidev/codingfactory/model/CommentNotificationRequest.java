package com.esprit.pidev.codingfactory.model;



import com.esprit.pidev.codingfactory.Entity.Post;
import com.esprit.pidev.codingfactory.Entity.User;

import lombok.Data;
@Data
public class CommentNotificationRequest {
    private User user;
    private Post post;
    private String commenterName;
}
