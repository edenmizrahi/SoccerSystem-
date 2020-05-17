package Service;

import Domain.Controllers.FanController;
import java.util.LinkedList;

public class FanApplication {
    FanController fanController = new FanController();

    public String fanIsTeamRole(String userName) {

        return fanController.fanIsTeamRole(userName);
    }

    public String setFanDetails(String userName,String name, String password, String phoneNumber, String email){
      return fanController.setFanDetails(userName, name, password, phoneNumber, email);
    }

    /**
     * return all notifications of fan and mark them as read
     * @param userName
     * @return
     */
    public LinkedList<String> getFanNotifications(String userName){
        return fanController.getFanNotifications(userName);
    }

    /**
     * return all the maches of fan follw
     * @param userName
     * @return
     */
    public LinkedList<String> getUserMachesFollows(String userName){
       return fanController.getUserMachesFollows(userName);
    }

    /**
     *  add Match to matches follow of fan
     * @param userName
     * @param match
     * @return
     */
    public String addMatchToFanMatchesFollow(String userName, String match) {
        return fanController.addMatchToFanMatchesFollow(userName, match);
    }

    /**
     * check for new Notification
     * @param username
     * @return
     */
    public String checkForNewNotifications(String username){
        return fanController.checkForNewNotifications(username);
    }

}
