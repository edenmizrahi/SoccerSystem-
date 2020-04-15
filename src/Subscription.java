import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Subscription extends User
{
    private String name;
    private String phoneNumber;
    private String email;
    private String userName;
    private String password;
    private static final Logger LOG = LogManager.getLogger();


    public Subscription(MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.password = password;
        //TODO add permissions
        //this.permissions.add();
    }

    //<editor-fold desc="getters and setters">
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //</editor-fold>

}
