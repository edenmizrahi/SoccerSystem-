import java.util.List;

public class MainSystem {

    private List<Complaint> complaints;// *
    private List<SystemManager> systemManagers; // 1..*
    private List<League> leagues;//*
    private List<User> users;//*

    public MainSystem(List<Complaint> complaints, List<SystemManager> systemManagers, List<League> leagues, List<User> users) {
        this.complaints = complaints;
        this.systemManagers = systemManagers;
        this.leagues = leagues;
        this.users = users;
    }


    public boolean action(){// the function needs to accept Permission[], and Action - both enum
     return true;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public List<SystemManager> getSystemManagers() {
        return systemManagers;
    }

    public void setSystemManagers(List<SystemManager> systemManagers) {
        this.systemManagers = systemManagers;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
