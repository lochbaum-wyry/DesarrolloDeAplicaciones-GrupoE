package domain.services;

import domain.Notification;
import domain.User;
import domain.repositories.NotificationRepository;

import java.util.List;

/**
 * Created by prospero on 7/8/16.
 */
public class NotificationService
{
    private NotificationRepository notificationRepository;

    public NotificationService()
    {
    }

    public NotificationService(NotificationRepository notificationRepository)
    {
        this();
        this.notificationRepository = notificationRepository;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public void setNotificationRepository(NotificationRepository notificationRepository)
    {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> notificationsFor(User user)
    {
        return notificationRepository.notificationsFor(user);
    }

    public void create(Notification notification)
    {
        notificationRepository.save(notification);
    }

    public void markSeen(Notification notification)
    {
        notification.setSeen(true);
        notificationRepository.update(notification);
    }

    public Notification getNotification(Integer id) {
        return notificationRepository.findById(id);
    }
}
