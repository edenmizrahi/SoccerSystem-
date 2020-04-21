package Service;

import Domain.MainSystem;
import Domain.Notifications.Notification;
import Domain.Users.Fan;
import Domain.Users.User;

import java.util.HashSet;

public class FanController {

    /***
     *must call after login.
     * @param user
     * @return
     *  @codeBy Eden
     */
    public HashSet<Notification> checkNotification(Fan user){
        return user.genUnReadNotifications();
    }
}
