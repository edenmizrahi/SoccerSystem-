import java.util.HashSet;

public interface NotificationsUser {

    HashSet<Notification> genUnReadNotifications();
    HashSet<Notification> getNotificationsList();
    void MarkAsReadNotification(Notification not);
}
