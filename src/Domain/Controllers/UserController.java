package Domain.Controllers;

import Domain.MainSystem;
import Domain.Users.Fan;
import Domain.Users.Referee;
import Domain.Users.Rfa;
import Domain.Users.User;

import javax.activation.MailcapCommandMap;
import javax.naming.ldap.Rdn;
import java.util.LinkedList;
import java.util.List;

public class UserController {
    SystemOperationsController smc=new SystemOperationsController();
    /**
     * login function
     * @param userName
     * @param password
     * @return
     * @throws Exception
     *  @codeBy Eden
     */
    public String login(String userName , String password ) {
        try {
            //Todo this function returns exceptions, need to catch them here and return the message
            Fan f = MainSystem.getInstance().logIn(userName, password);
            if (f == null) {
                //Todo change to return string message and not exception
                throw new Exception("Incorrect user name or password");
            }
            if (f instanceof Rfa) {
                return "RFA";
            }
            if (f instanceof Referee) {
                return "Referee";
            }
            return "Fan";
        } catch(Exception e){
            //Todo change the return
            return "";
        }
    }

    public boolean haveUnreadNotifications(String userName ) {
        User u = smc.getUserByUserName(userName);
        if(((Fan)(u)).getUnReadNotifications().size()>0){
            return true;
        }
        return false;
    }

}
