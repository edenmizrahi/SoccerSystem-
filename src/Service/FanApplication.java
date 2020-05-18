package Service;

import Domain.Controllers.FanController;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/Fan")
public class FanApplication {
    FanController fanController = new FanController();

    @Path("/fanIsTeamRole/{username}")
    @GET
    @Produces("text/plain")
    public String fanIsTeamRole(@PathParam("username") String userName) {
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
    public String getFanNotifications(String userName){
        return fanController.getFanNotifications(userName);
    }

    /**
     * return all the maches of fan follw
     * @param userName
     * @return
     */
    public String getUserMachesFollows(String userName){
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
