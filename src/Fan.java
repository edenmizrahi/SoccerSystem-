import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Fan extends Subscription implements Observer {

    private List<PrivatePage> myPages;
    private List<Notification> myNotifications;
    private List<Complaint> myComplaints;
    private LinkedList <Match> matchesFollow;
    private String name;
    private String id;
    private String email;
    private String password;



    LinkedList<String> searchHisroty;


    Fan(Subscription sub, MainSystem ms){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        myPages = new LinkedList<>();
        myNotifications = new LinkedList<>();
        myComplaints = new LinkedList<>();
        matchesFollow=new LinkedList<>();
        //TODO add permissions
        //this.permissions.add();
    }

    /**Eden*/
    public void  addMatchFollow(Match m){
        matchesFollow.add(m);
        m.addObserver(this);
    }

    /**Eden*/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  Match){
            /**change place or time event */
            /**Game result*/
            if(arg instanceof String){
                /**result event**/
                if(((String)arg).contains("Result") ){

                }
                else{
                    /**change place event */
                    if(((String)arg).contains("Place") ){

                    }
                    else{
                        /**change time event */
                        if(((String)arg).contains("Time") ){

                        }
                    }
                }
            }
            else{
                if(arg instanceof Goal){
                    return;
                }
                if(arg instanceof RedCard){
                    return;
                }
                if(arg instanceof YellowCard){
                    return;
                }
                if(arg instanceof OffSide){
                    return;
                }
                if(arg instanceof Offense){
                    return;
                }
                if(arg instanceof Injury){
                    return;
                }
                if(arg instanceof Replacement){
                    return;
                }
            }
        }
        if(o instanceof Complaint){
            Complaint comp=((Complaint)o);
            String ans=comp.getAnswer();
            // TODO: 11/04/2020 do something with ans..
        }
    }

    /**Eden*/
    public void addNewComplaint(String comp){
        Complaint complaint=new Complaint(this,system);
        myComplaints.add(complaint);
        complaint.setContent(comp);
        complaint.send();

        complaint.addObserver(this);

    }

    /**Eden*/
    public void addToSearchHistory(String search){
        searchHisroty.add(search);
    }

    /**Eden*/
    public LinkedList<String> getSearchHisroty() {
        return searchHisroty;
    }

    public void subToPage(int pageID){

    }
    public void addComplaint(){

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
