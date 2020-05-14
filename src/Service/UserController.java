package Service;

import Domain.MainSystem;
import Domain.Users.Fan;
import Domain.Users.Referee;
import Domain.Users.Rfa;
import Domain.Users.User;

import javax.activation.MailcapCommandMap;
import javax.naming.ldap.Rdn;

public class UserController {

    /**
     * login function
     * @param u
     * @param userName
     * @param password
     * @return
     * @throws Exception
     *  @codeBy Eden
     */
    public String login(User u, String userName , String password ) throws Exception {
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
}
