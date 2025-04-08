package com.esprit.pidev.codingfactory.Service;

import com.esprit.pidev.codingfactory.Entity.Notification;
import com.esprit.pidev.codingfactory.Entity.NotificationType;
import com.esprit.pidev.codingfactory.Entity.Post;
import com.esprit.pidev.codingfactory.Entity.User;

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
    public void createCommentNotification(User user, Post post, String commenterName) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setPost(post);
        notification.setMessage(commenterName + " commented on your post");
        notification.setType(NotificationType.COMMENT);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    @Transactional
    public void createMentionNotification(User user, Post post, String mentionedBy) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setPost(post);
        notification.setMessage(mentionedBy + " mentioned you in a post");
        notification.setType(NotificationType.MENTION);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(User user) {
        return notificationRepository.findByUser(user);
    }

    public List<Notification> getUnreadNotifications(User user) {
        return notificationRepository.findByUserAndIsReadFalse(user);
    }

    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }

    @Transactional
    public void markAllAsRead(User user) {
        List<Notification> unreadNotifications = notificationRepository.findByUserAndIsReadFalse(user);
        unreadNotifications.forEach(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }
}