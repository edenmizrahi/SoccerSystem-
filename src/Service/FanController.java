package Service;

import Domain.Notifications.Notification;
import Domain.Users.Fan;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class FanController {
    /***
     *must call after login.
     * @param user
     * @return
     *  @codeBy Eden
     */
    public HashSet<Notification> showNotifications(Fan user){
        return user.getUnReadNotifications();
    }


    /**
     * logOutFromSystem
     *
     * @param curUser
     * @codeBy Eden
     */
    public void logOut(Fan curUser) {
        curUser.logOut();
    }

    /**
     * change user details , return list of changed fields
     * send parameter you want to change or null if you dont want to change
     *
     * @param user
     * @param password
     * @param phoneNumber
     * @param email
     * @param date
     * @return list of fields that changed.
     * @codeBy Eden
     */
    public List<String> changePrivateDetails(Fan user, String password, String phoneNumber, String email, Date date) {
        List<String> changed = new LinkedList<>();

        //password length is 6 or more
        if (password != null && password.length() >= 6) {
            changed.add(password);
            user.setPassword(password);
        }
        // phone number is 10 digits
        if (phoneNumber != null && (phoneNumber.matches("^[0-9]*$") && phoneNumber.length() == 10)) {
            changed.add(phoneNumber);
            user.setPhoneNumber(phoneNumber);
        }
        //email contains @
        if (email != null && email.contains("@") && ((email.contains(".com") || email.contains("il")))) {
            changed.add(email);
            user.setEmail(email);
        }
        if (date != null) {
            changed.add("birthday");
            user.setDateOfBirth(date);

        }
        return changed;

    }

    /***
     * mark list of notifications as read.
     * @param f
     * @param read
     * @codeBy Eden
     */
    public void markAsReadNotification(Fan f, HashSet<Notification> read) {
        for (Notification n : read) {
            if (f.getNotificationsList().contains(n)) {
                f.MarkAsReadNotification(n);
            }
        }
    }


}
