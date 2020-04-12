import java.util.HashSet;

public class TeamOwner extends Subscription{

    private HashSet<Team> teams;
    private BudgetControl budgetControl;
    private HashSet<Notification> notifications;

    //if already team owner of other teams
    public TeamOwner(Subscription sub, MainSystem ms,HashSet<Team> teams) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.teams = teams;
        this.budgetControl = new BudgetControl();
        notifications = new HashSet<>();
        //TODO add permissions
        //this.permissions.add();
    }

    //first time team owner
    public TeamOwner(Subscription sub, MainSystem ms, Team team) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.budgetControl = new BudgetControl();
        this.teams = new HashSet<>();
        teams.add(team);
        notifications = new HashSet<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public TeamOwner subscribeTeamOwner(Subscription sub, MainSystem ms, Team team){
        //create TeamOwner
        TeamOwner tO = new TeamOwner(sub, ms, team);
        //remove sub from system
        if(ms.removeUser(sub)){
            //add tO to system
            if (ms.addUser(tO)){
                return tO;
            }
        }
        return null;
    }
    //<editor-fold desc="setters and getters">
    public void setTeams(HashSet<Team> teams) {
        this.teams = teams;
    }

    public void setTeam(Team team) {
        this.teams.add(team);
    }

    public void setBudgetControl(BudgetControl budgetControl) {
        this.budgetControl = budgetControl;
    }

    public void setNotifications(HashSet<Notification> notifications) {
        this.notifications = notifications;
    }

    public HashSet<Team> getTeams() {
        return teams;
    }

    public BudgetControl getBudgetControl() {
        return budgetControl;
    }

    public HashSet<Notification> getNotifications() {
        return notifications;
    }

    //</editor-fold>
}
