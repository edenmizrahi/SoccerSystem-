import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Fan extends User implements Observer, NotificationsUser {

    private List<PrivatePage> myPages;
    private List<Complaint> myComplaints;
    private LinkedList <Match> matchesFollow;
    private LinkedList<String> searchHisroty;
    private static final Logger LOG = LogManager.getLogger();
    HashSet<Notification> notificationHashSet;

    //from subscription:
    private String name;
    private String phoneNumber;
    private String email;
    private String userName;
    private String password;
    private Date dateOfBirth;

    public Fan(MainSystem ms, String name, String phoneNumber, String email, String userName, String password, Date date) {
        super(ms);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth=date;
        myPages = new LinkedList<>();
        myComplaints = new LinkedList<>();
        matchesFollow=new LinkedList<>();
        this.searchHisroty= new LinkedList<>();

        //add userName to the hashset
        ms.addUserName(userName);
    }

    //<editor-fold desc="getters and setters">

    public List<PrivatePage> getMyPages() {
        return myPages;
    }

    public void setMyPages(List<PrivatePage> myPages) {
        this.myPages = myPages;
    }

    public List<Complaint> getMyComplaints() {
        return myComplaints;
    }

    public void setMyComplaints(List<Complaint> myComplaints) {
        this.myComplaints = myComplaints;
    }

    public LinkedList<Match> getMatchesFollow() {
        return matchesFollow;
    }

    public void setMatchesFollow(LinkedList<Match> matchesFollow) {
        this.matchesFollow = matchesFollow;
    }

    public void setSearchHisroty(LinkedList<String> searchHisroty) {
        this.searchHisroty = searchHisroty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    //</editor-fold>


    /**Eden*/
    public void  addMatchFollow(Match m){
        matchesFollow.add(m);
        m.addObserver(this);
    }



    /**Eden*/
    public void addNewComplaint(String comp) throws Exception {
        Complaint complaint=new Complaint(this,system);
        myComplaints.add(complaint);
        complaint.setContent(comp);
        complaint.send("new Complaint from: "+userName);

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


    /**Or**/
    public void subToPage(PrivatePage privatePage) throws Exception {
        if(privatePage== null){
            throw new Exception("private page null");
        }
        myPages.add(privatePage);
        privatePage.addFan(this);
        LOG.info(String.format("%s - %s", getUserName(), "subscriped to page"));
    }


    public void addComplaint(){

    }

    /**OR**/
    public void addPage(PrivatePage page){
        myPages.add(page);
    }

    /**OR**/
    public void logOut(){
        //nothing to do now.....
        LOG.info(String.format("%s - %s", userName, "loged out from system"));
    }


    //<editor-fold desc="Notifications Handler">
    /**Eden*/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  Match){
            notificationHashSet.add(new Notification(o, arg, false));
//            /**change place or time event */
//            /**Game result*/
//            if(arg instanceof String){
//                /**result event**/
//                if(((String)arg).contains("Result") ){
//
//                }
//                else{
//                    /**change place event */
//                    if(((String)arg).contains("Place") ){
//
//                    }
//                    else{
//                        /**change time event */
//                        if(((String)arg).contains("Time") ){
//
//                        }
//                    }
//                }
//            }
//            else{
//                if(arg instanceof Goal){
//                    return;
//                }
//                if(arg instanceof RedCard){
//                    return;
//                }
//                if(arg instanceof YellowCard){
//                    return;
//                }
//                if(arg instanceof OffSide){
//                    return;
//                }
//                if(arg instanceof Offense){
//                    return;
//                }
//                if(arg instanceof Injury){
//                    return;
//                }
//                if(arg instanceof Replacement){
//                    return;
//                }
//            }
        }
        if(o instanceof Complaint){
            Complaint comp=((Complaint)o);
            String ans=comp.getAnswer();
            notificationHashSet.add(new Notification(o, "Answer: "+ans, false));

        }
    }


    /**
     * mark notification as readen
     * @param not-unread notification to mark as read
     * @codeBy Eden
     */
    @Override
    public void MarkAsReadNotification(Notification not){
        not.isRead=true;
    }
    @Override
    public HashSet<Notification> getNotificationsList() {
        return notificationHashSet;
    }

    /***
     * @return only the unread notifications . if not have return null
     * @codeBy Eden
     */
    @Override
    public HashSet<Notification> genUnReadNotifications(){
        HashSet<Notification> unRead=new HashSet<>();
        for(Notification n: notificationHashSet){
            if(n.isRead==false){
                unRead.add(n);
            }
        }
        return unRead;
    }
    //</editor-fold>
}
