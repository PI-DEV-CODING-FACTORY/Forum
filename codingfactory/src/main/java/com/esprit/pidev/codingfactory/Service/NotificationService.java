package com.esprit.pidev.codingfactory.Service;

import com.esprit.pidev.codingfactory.Entity.Notification;
import com.esprit.pidev.codingfactory.Entity.NotificationType;
import com.esprit.pidev.codingfactory.Entity.Post;


import com.esprit.pidev.codingfactory.Repository.NotificationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional
    public void createCommentNotification(String user_id, Post post, String commenterName) {
        Notification notification = new Notification();
        notification.setUser_id(user_id);
        notification.setPost(post);
        notification.setMessage(commenterName + " commented on your post");
        notification.setType(NotificationType.COMMENT);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    @Transactional
    public void createMentionNotification(String user_id, Post post, String mentionedBy) {
        Notification notification = new Notification();
        notification.setUser_id(user_id);
        notification.setPost(post);
        notification.setMessage(mentionedBy + " mentioned you in a post");
        notification.setType(NotificationType.MENTION);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(String user_id) {
        return notificationRepository.findByUserId(user_id);
    }

    public List<Notification> getUnreadNotifications(String user_id) {
        return notificationRepository.findUnreadByUserId(user_id);
    }

    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }

    @Transactional
    public void markAllAsRead(String user_id) {
        List<Notification> unreadNotifications = notificationRepository.findUnreadByUserId(user_id);
        unreadNotifications.forEach(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }
}