package Domain.Controllers;

import DataAccess.DbAdapter.FanAdapter;
import Domain.MainSystem;
import Domain.Users.*;

public class UserController{
    SystemOperationsController smc=new SystemOperationsController();
    FanAdapter fanAdapter=new FanAdapter();
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
                //write the new fan to db
            fanAdapter.ToDB(f);
            if (f instanceof Rfa) {
                // TODO:Eden 18/05/2020- add RFA to RFAs table
                return "RFA";

            }
            if (f instanceof Referee) {
                // TODO:Eden 18/05/2020- add Referee to Referees table
                return "Referee";
            }
            if (f instanceof TeamRole){
                // TODO:Eden 18/05/2020 add to teamRole table and to the subTables (players, coaches and more - id requiered)
            }
            return "Fan";
        } catch(Exception e){
            //Todo change the return
            return "";
        }
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
        User u = smc.getUserByUserName(userName);
        if(((Fan)(u)).getUnReadNotifications().size()>0){
            return true;
        }
        return false;
    }


}
