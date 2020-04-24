package Domain.Notifications;

import Domain.Notifications.Notification;

import java.util.HashSet;
import java.util.Observer;

public interface NotificationsUser extends Observer{

    HashSet<Notification> genUnReadNotifications();
    HashSet<Notification> getNotificationsList();
    void MarkAsReadNotification(Notification not);
}
