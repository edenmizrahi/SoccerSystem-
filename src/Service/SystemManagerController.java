package Service;

import Domain.LeagueManagment.Team;
import Domain.Notifications.Notification;
import Domain.Users.SystemManager;
import Domain.Users.TeamOwner;
import Domain.Users.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SystemManagerController {

    /**
     * close team  forever
     *
     * @param teamToRemove
     * @param user
     * @throws Exception
     */
    public void closeTeamForever(Team teamToRemove, SystemManager user) throws Exception {
        user.removeTeamFromSystem(teamToRemove);
    }

    /**
     * get user by name is not an unique field so return a list of
     * matches users.
     *
     * @param name
     * @return list of match users.
     */
    public LinkedList<User> getUserByName(String name, SystemManager user) {
        List<User> users = user.getSystem().getUsers();
        LinkedList<User> matches = new LinkedList<User>();
        for (User cur : users) {
            if (user.getName().equals(name)) ;
            matches.add(cur);
        }
        return matches;
    }

    /**
     * Domain.Users.User name is an unique field so this function return one user if there is user with
     *
     * @param userName
     * @param user
     * @return
     */
    public User getUserByUserName(String userName, SystemManager user) {
        List<User> users = user.getSystem().getUsers();
        LinkedList<User> matches = new LinkedList<User>();
        for (User cur : users) {
            if (user.getUserName().equals(userName)) {
                return cur;
            }
        }
        return null;
    }

    /**
     * Delete user from System
     * @param u user to delete
     * @param systemManager
     * @throws Exception if delete is not valid.
     */
    public void deleteUserForever(User u, SystemManager systemManager) throws Exception {
        systemManager.removeUser(u);
    }


    /**
     * replace team's founder .
     * @param user
     * @param toAdd
     * @param toRemove
     * @param fromTeam
     * @throws Exception if team is not in the team owner's teams list.
     */
    public void replaceTeamFounder(SystemManager user, TeamOwner toAdd, TeamOwner toRemove , Team fromTeam) throws Exception {
        user.replaceTeamOwnerFounder(toAdd,toRemove,fromTeam);
    }

    /**
     * @return FileReader pointer to Log file
     */
    public FileReader showSystemDetails (SystemManager user) throws FileNotFoundException {
        return user.showSystemInfo();
    }

    /**
     * show unread notification -> run while user connect .
     * @param user
     * @return
     */
    public HashSet<Notification> showUnreadNotification(SystemManager user){
        return user.genUnReadNotifications();
    }

    public void markAsReadNotification (HashSet<Notification> read){

    }


    // TODO: 19/04/2020  watching and answers complaints - next iteration



}


