import java.util.LinkedList;

public class TeamOwner extends Subscription{

    private LinkedList<Team> teams;
    private BudgetControl budgetControl;
    private LinkedList<Notification> notifications;

    //if already team owner of other teams
    public TeamOwner(Subscription sub, MainSystem ms, LinkedList<Team> teams) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        ms.removeUser(sub);
        this.teams = teams;
        this.budgetControl = new BudgetControl();
        notifications = new LinkedList<>();
        //TODO add permissions
        //this.permissions.add();
    }

    //first time team owner
    public TeamOwner(Subscription sub, MainSystem ms, Team team) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        ms.removeUser(sub);
        this.budgetControl = new BudgetControl();
        this.teams = new LinkedList<>();
        teams.add(team);
        team.addTeamOwner(this);
        notifications = new LinkedList<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public TeamOwner subscribeTeamOwner(Subscription sub, MainSystem ms, Team team){
        //create TeamOwner
        TeamOwner tO = new TeamOwner(sub, ms, team);
        //remove sub from system
        return tO;
    }
    //<editor-fold desc="setters and getters">
    public void setTeam(Team team) {
        this.teams.add(team);
    }

    public void setBudgetControl(BudgetControl budgetControl) {
        this.budgetControl = budgetControl;
    }

    public LinkedList<Team> getTeams() {
        return teams;
    }

    public BudgetControl getBudgetControl() {
        return budgetControl;
    }

    //</editor-fold>
}
