package com.sisinfo.Service;

import com.sisinfo.Entity.Notification;
import com.sisinfo.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Long id, Notification updatedNotification) {
        var notification = notificationRepository.findById(id);

        if (notification.isEmpty()) return null;

        var existingNotification = notification.get();
        existingNotification.setMessage(updatedNotification.getMessage());
        existingNotification.setDate(updatedNotification.getDate());
        existingNotification.setRead(updatedNotification.isRead());

        return notificationRepository.save(existingNotification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
