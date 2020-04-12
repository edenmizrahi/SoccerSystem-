import java.util.LinkedList;
import java.util.List;

public class Fan extends Subscription{

    private List<PrivatePage> myPages;
    private List<Notification> myNotifications;
    private List<Complaint> myComplaints;


    Fan(Subscription sub, MainSystem ms){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        myPages = new LinkedList<>();
        myNotifications = new LinkedList<>();
        myComplaints = new LinkedList<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public void subToPage(int pageID){

    }
    public void addComplaint(){

    }

}
