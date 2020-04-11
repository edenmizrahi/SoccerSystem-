import java.util.HashSet;

public class TeamOwner extends Subscription{

    private HashSet<Team> teams;
    private BudgetControl budgetControl;
    private HashSet<Notification> notifications;
    private MainSystem mainSystem;

    public TeamOwner(MainSystem ms,HashSet<Team> teams, BudgetControl budgetControl) {
        this.teams = teams;
        this.budgetControl = budgetControl;
        this.mainSystem = ms;
        notifications = new HashSet<>();
    }

    public TeamOwner(MainSystem ms, BudgetControl budgetControl, Team team) {
        this.budgetControl = budgetControl;
        this.teams = new HashSet<>();
        teams.add(team);
        this.mainSystem = ms;
        notifications = new HashSet<>();
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

    public void setMainSystem(MainSystem mainSystem) {
        this.mainSystem = mainSystem;
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

    public MainSystem getMainSystem() {
        return mainSystem;
    }
    //</editor-fold>
}
