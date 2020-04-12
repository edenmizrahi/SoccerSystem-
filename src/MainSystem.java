import java.util.LinkedList;
import java.util.List;

public class MainSystem {

    private LinkedList<Complaint> complaints;// *
    private LinkedList<SystemManager> systemManagers; // 1..*
    private LinkedList<League> leagues;//*
    private LinkedList<User> users;//*

    public MainSystem(SystemManager sm) {
        this.complaints = new LinkedList<>();
        this.systemManagers = new LinkedList<>();
        systemManagers.add(sm);
        this.leagues = new LinkedList<>();
        this.users = new LinkedList<>();
    }
    public MainSystem() {
        this.complaints = new LinkedList<>();
        this.systemManagers = new LinkedList<>();
        this.leagues = new LinkedList<>();
        this.users = new LinkedList<>();
    }
    // adi
    public boolean removeUser(User user){
        if (users.contains(user)){
            users.remove(user);
            return true;
        }
        return false;
    }
    // adi
    public boolean addUser(User user){
        if (users.contains(user)){
            return false;
        }
        users.add(user);
        return true;
    }
    //<editor-fold desc="getters and setters">
    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(LinkedList<Complaint> complaints) {
        this.complaints = complaints;
    }

    public List<SystemManager> getSystemManagers() {
        return systemManagers;
    }

    public void setSystemManagers(LinkedList<SystemManager> systemManagers) {
        this.systemManagers = systemManagers;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(LinkedList<League> leagues) {
        this.leagues = leagues;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }
    //</editor-fold>
}
