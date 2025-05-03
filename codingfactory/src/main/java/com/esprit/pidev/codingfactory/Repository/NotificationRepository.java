package com.esprit.pidev.codingfactory.Repository;

import com.esprit.pidev.codingfactory.Entity.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // List<Notification> findByUserAndIsReadFalse(User user);
    // List<Notification> findByUser(User user);

    @Query("SELECT n FROM Notification n WHERE n.user_id = :userId AND n.isRead = false")
    List<Notification> findUnreadByUserId(@Param("userId") String userId);

    @Query("SELECT n FROM Notification n WHERE n.user_id = :userId")
    List<Notification> findByUserId(@Param("userId") String userId);
}