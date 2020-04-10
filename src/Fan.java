import java.util.LinkedList;
import java.util.List;

public class Fan extends Subscription{

    List<PrivatePage> myPages;
    List<Notification> myNotifications;
    List<Complaint> myComplaints;


    Fan(){
        myPages = new LinkedList<>();
        myNotifications = new LinkedList<>();
        myComplaints = new LinkedList<>();
    }

    public void subToPage(int pageID){

    }
    public void addComplaint(){

    }

}
