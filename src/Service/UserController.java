package Service;

import Domain.Users.Fan;
import Domain.Users.User;

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
    public Fan login(User u, String userName , String password ) throws Exception {
        Fan f=u.logIn(userName,password);
        if(f==null){
            throw new Exception("Incorrect user name or password");
        }
        return f;
    }
}
