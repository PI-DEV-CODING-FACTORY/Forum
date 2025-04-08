package com.esprit.pidev.codingfactory.Repository;

import com.esprit.pidev.codingfactory.Entity.Notification;
import com.esprit.pidev.codingfactory.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndIsReadFalse(User user);
    List<Notification> findByUser(User user);
}