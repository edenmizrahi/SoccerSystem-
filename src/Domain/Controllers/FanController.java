package Domain.Controllers;

import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Notifications.Notification;
import Domain.Users.Fan;
import Domain.Users.Referee;
import Domain.Users.TeamRole;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class FanController {
    SystemOperationsController systemOperationsController=new SystemOperationsController();


    public String fanIsTeamRole(String userName) {
        Fan fan= (Fan) systemOperationsController.getUserByUserName(userName);
        if( fan instanceof TeamRole){
            return "true";
        }
        return "false";
    }

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
    public List<String> changePrivateDetails(Fan user,String name ,String password, String phoneNumber, String email, Date date) {
        List<String> changed = new LinkedList<>();
        //add name
        changed.add(name);
        user.setName(name);
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

    public String setFanDetails(String userName,String name, String password, String phoneNumber, String email){
        Fan fan= (Fan) systemOperationsController.getUserByUserName(userName);
        Date dateFormat= null;
//        try {
//            dateFormat = new SimpleDateFormat("dd/MM/yyyy").parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        List<String> changes= changePrivateDetails(fan,name,password,phoneNumber,email,dateFormat);
        if(changes.isEmpty()){
            return "noting is changed !! ";
        }
        else return "ok";

    }

    /**
     * return all notifications of fan and mark them as read
     * @param userName
     * @return
     */
    public LinkedList<String> getFanNotifications(String userName){
        LinkedList<String> fanNotificationsString=new LinkedList<>();
        Fan fan= (Fan) systemOperationsController.getUserByUserName(userName);
        HashSet<Notification> fanNotifications= fan.getNotificationsList();
        for (Notification noti:fanNotifications) {
            fanNotificationsString.add(noti.getContent().toString());
            noti.setRead(true);
        }

        //markAsReadNotification
        return fanNotificationsString;
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

    /**
     * return all the maches of fan follw
     * @param userName
     * @return
     */
    public LinkedList<String> getUserMachesFollows(String userName){
        Fan fan= (Fan) systemOperationsController.getUserByUserName(userName);
        LinkedList<Match> fanMachesFollw= fan.getMatchesFollow();
        LinkedList<String> fanMachesFollwsString = new LinkedList<>();
        for (Match match:fanMachesFollw) {
            fanMachesFollwsString.add(match.toString());
        }
        return fanMachesFollwsString;
    }

    /**
     *  add Match to matches follow of fan
     * @param userName
     * @param match
     * @return
     */
    public String addMatchToFanMatchesFollow(String userName, String match) {
        Fan fan= (Fan) systemOperationsController.getUserByUserName(userName);
        HashSet<Match> allMatchesInSystem=systemOperationsController.getAllCurrMatchs();
        String massage="eror- match not added";
        //        String[] arrayOfTeamsAndDate = match.split(",");
//        String date = arrayOfTeamsAndDate[1];
//        String TeamsString = arrayOfTeamsAndDate[0];
//        String[] arrayOfTeams = TeamsString.split("-");
//        Team homeTeam = this.systemOperationsController.getTeambyTeamName(arrayOfTeams[0]);
//        Team awayTeam = this.systemOperationsController.getTeambyTeamName(arrayOfTeams[1]);
//        Date matchDate = MainSystem.simpleDateFormat.parse(date);
        for (Match m:allMatchesInSystem) {
            if(m.toString().equals(match))  {
                fan.addMatchFollow(m);
                massage="ok";
                return massage;
            }
        }
        return massage;
    }


}

