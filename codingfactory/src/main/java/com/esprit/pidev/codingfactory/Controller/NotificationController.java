package com.esprit.pidev.codingfactory.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.pidev.codingfactory.Entity.Notification;
import com.esprit.pidev.codingfactory.Entity.Post;
// import com.esprit.pidev.codingfactory.Entity.User;
// import com.esprit.pidev.codingfactory.Service.AuthService;
import com.esprit.pidev.codingfactory.Service.NotificationService;
import com.esprit.pidev.codingfactory.model.CommentNotificationRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;
    // private final AuthService authService;

    @GetMapping("/user/{userId}")
    public List<Notification> getUserNotifications(@PathVariable("userId") String userId) {
        // User user = authService.getUserById(userId);
        return notificationService.getUserNotifications(userId);
    }

    @GetMapping("/user/unread/{userId}")
    public List<Notification> getUnreadNotifications(@PathVariable("userId") String userId) {
        // User user = authService.getUserById(userId);
        return notificationService.getUnreadNotifications(userId);
    }

    @PutMapping("/mark-read/{notificationId}")
    public void markAsRead(@PathVariable("notificationId") Long notificationId) {
        notificationService.markAsRead(notificationId);
    }

    @PutMapping("/mark-all-read/{userId}")
    public ResponseEntity<String> markAllAsRead(@PathVariable("userId") String userId) {
        try {
            // User user = authService.getUserById(userId);
            notificationService.markAllAsRead(userId);
            return ResponseEntity.ok("All notifications marked as read");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to mark notifications as read: " + e.getMessage());
        }
    }

    @PostMapping("/mention")
    public ResponseEntity<String> createMentionNotification(@RequestBody CommentNotificationRequest request) {
        try {
            String userId = request.getUser_id();
            Post post = request.getPost();
            System.out.println("User: " + userId);
            System.out.println("Post: " + post);
            notificationService.createMentionNotification(userId, post, request.getCommenterName());
            return ResponseEntity.ok("Notification created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create notification: " + e.getMessage());
        }
    }

    // @PostMapping("/comment")
    // public void createCommentNotification(@RequestBody CommentNotificationRequest
    // request,
    // @RequestParam String commenterName) {
    // User user = request.getUser();
    // Post post = request.getPost();
    // notificationService.createCommentNotification(user, post, commenterName);
    // }

    @PostMapping("/comment")
    public ResponseEntity<String> createCommentNotification(@RequestBody CommentNotificationRequest request) {
        try {
            // User user = request.getUser();
            String userId = request.getUser_id();
            Post post = request.getPost();
            notificationService.createCommentNotification(userId, post, request.getCommenterName());
            return ResponseEntity.ok("Notification created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create notification: " + e.getMessage());
        }
    }

}