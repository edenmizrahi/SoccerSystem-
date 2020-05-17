package Service;

import Domain.Controllers.SystemOperationsController;
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
     * return list with all private details of the fan
     * list : name, Password, PhoneNumber, Email, DateOfBirth
     * @param userName
     * @return list of details of fan
     */
    public List<String> getPrivateDetails(String userName) {
        return systemOperationsController.getPrivateDetails(userName);
    }


    public String  signUp(String role, String name, String phoneNumber, String email, String userName, String password, Date dateOfBirth) {
       return systemOperationsController.signUp(role, name, phoneNumber, email, userName, password, dateOfBirth);
    }
}
