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
    public String login(String userName , String password ) throws Exception {
        Fan f= MainSystem.getInstance().logIn(userName,password);
        if(f==null){
            throw new Exception("Incorrect user name or password");
        }
        if(f instanceof Rfa){
            return "RFA";
        }
        if(f instanceof Referee){
            return "Referee";
        }
        return "Fan";
    }

    /**OR
     * log out from system
     * @param username
     * @return
     */
    public String logOut(String username){
        User u=smc.getUserByUserName(username);
        if(u instanceof Fan){
            try {
                MainSystem.getInstance().logOut((Fan)u);
                return "succes";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "user is not a fan";
    }

    public boolean haveUnreadNotifications(String userName ) {
        User u=smc.getUserByUserName(userName);
        if( u instanceof  Fan &&((Fan)(u)).getUnReadNotifications().size()>0){
            return true;
        }
        return false;
    }

}
