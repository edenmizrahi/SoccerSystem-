package Domain.Controllers;

import DataAccess.DaoFans;
import DataAccess.DbAdapter.FanAdapter;
import Domain.Main;
import Domain.MainSystem;
import Domain.Users.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserController{
    SystemOperationsController smc=new SystemOperationsController();
    FanAdapter fanAdapter=new FanAdapter();
    DaoFans fansTable=new DaoFans();
    /**
     * login function
     * @param userName
     * @param password
     * @return
     * @throws Exception
     *  @codeBy Eden
     */
    public String login(String userName , String password ) {
        MainSystem ms = MainSystem.getInstance();
        try {
        //Todo this function returns exceptions, need to catch them here and return the message
            if(userName==null){
               ms.writeToLogError("userName null");
                throw new Exception("userName null");
            }
            if(userName.length()==0){
                ms.writeToLogError("userName empty");
                throw new Exception("userName empty");
            }
            if(password==null){
                ms.writeToLogError("password null");
                throw new Exception("password null");
            }
            if(password.length()<6){
                ms.writeToLogError("password not valid");
                throw new Exception("password not valid");
            }
            List<String> key=new LinkedList<>();
            key.add(userName);
            List<String> fanRecord =fansTable.get(key);
            /**userName dont exist at table
             * or password dont match*/
            if(fanRecord==null||!fanRecord.get(2).equals(password)){
                ms.writeToLogError("details not correct, no fan in system");
                throw new Exception("details not correct, no fan in system");
            }
            Fan f =fanAdapter.ToObj(fanRecord);
            if (f == null) {
                 //Todo change to return string message and not exception
                throw new Exception("Incorrect user name or password");
            }
            MainSystem.getInstance().logIn(userName,password,f);
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
            return e.getMessage();
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
