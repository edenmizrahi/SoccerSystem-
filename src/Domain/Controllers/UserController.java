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

    public boolean haveUnreadNotifications(String userName ) {
        User u=smc.getUserByUserName(userName);
        if(((Fan)(u)).getUnReadNotifications().size()>0){
            return true;
        }
        return false;
    }

}
