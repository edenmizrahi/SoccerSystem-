package Service;

import Domain.Controllers.UserController;

public class UserApplication {
    UserController userController = new UserController();

    /**
     * login function
     * @param userName
     * @param password
     * @return
     * @throws Exception
     *  @codeBy Eden
     */
    public String login(String userName , String password ) throws Exception {
        return userController.login(userName, password);
    }

    public String logout(String userName )  {
        return userController.logOut(userName);
    }

    public boolean haveUnreadNotifications(String userName ) {
        return userController.haveUnreadNotifications(userName);
    }

}
