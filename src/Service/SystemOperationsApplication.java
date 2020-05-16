package Service;

import Domain.Controllers.SystemOperationsController;
import java.text.ParseException;
import java.util.*;

public class SystemOperationsApplication {
    SystemOperationsController systemOperationsController = new SystemOperationsController();


    /**
     * return all matches in system that have not yet happened - String format
     */
    public HashSet<String> getAllMatchsInSytem(){
        return systemOperationsController.getAllMatchsInSytem();
    }

    /**
     *
     * @return LinkedList<String> off all the season in the system
     */
    public LinkedList<String> getAllStringSeasons(){
        return systemOperationsController.getAllStringSeasons();
    }


    /**
     * start system - if its first start
     * return the default user name and password
     * otherwise return null.
     * @return List of default user name and password or null
     * @throws ParseException
     * @codeBy Eden
     */
     public List<String> startSystem() throws ParseException {
       return systemOperationsController.startSystem();

     }

    /**
     * return list with all private details of the fan
     * list : name, Password, PhoneNumber, Email, DateOfBirth
     * @param userName
     * @return list of details of fan
     */
    public List<String> getPrivateDetails(String userName) { //##
        return systemOperationsController.getPrivateDetails(userName);
    }


    public String  signUp(String role, String name, String phoneNumber, String email, String userName, String password, Date dateOfBirth) {
       return systemOperationsController.signUp(role, name, phoneNumber, email, userName, password, dateOfBirth);
    }
}
