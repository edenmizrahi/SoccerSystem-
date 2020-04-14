import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class TeamOwner extends Subscription{

    private LinkedList<Team> teams;
    private BudgetControl budgetControl;
    private HashMap<Subscription, Team> mySubscriptions;
    private static final Logger LOG = LogManager.getLogger();

    //if already team owner of other teams
    public TeamOwner(Subscription sub, MainSystem ms, LinkedList<Team> teams) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        ms.removeUser(sub);
        this.teams = teams;
        this.budgetControl = new BudgetControl();
        mySubscriptions = new HashMap<>();
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
        mySubscriptions = new HashMap<>();
        //TODO add permissions
        //this.permissions.add();
    }
    // adi
    public TeamOwner subscribeTeamOwner(Subscription sub, MainSystem ms, Team team){
        TeamOwner tO = new TeamOwner(sub, ms, team);
        mySubscriptions.put(tO, team);
        return tO;
    }
    // adi
    public boolean removeTeamOwner (TeamOwner tO, MainSystem ms, Team team){
        if (mySubscriptions.containsKey(tO)){
            mySubscriptions.remove(tO);
            for (Map.Entry<Subscription, Team> entry : tO.mySubscriptions.entrySet()) {
                if (entry.getValue().equals(team)){
                    tO.removeTeamOwner((TeamOwner) entry.getKey(), ms, entry.getValue());
                }
            }
            team.removeTeamOwner(tO);
            ms.removeUser(tO);
            Subscription newSub = new Subscription(ms, tO.getName(), tO.getPhoneNumber(), tO.getEmail(), tO.getUserName(), tO.getPassword());
            return true;
        }
        return false;
    }
    // adi
    public TeamManager subscribeTeamManager(Subscription sub, MainSystem ms, Team team, HashSet<Permission> per){
        TeamManager tM = new TeamManager(sub, ms, team);
        tM.addPermissions(per);
        mySubscriptions.put(tM, team);
        return tM;
    }
    // adi
    public boolean removeTeamManager (TeamManager tM, MainSystem ms, Team team){
        if (mySubscriptions.containsKey(tM)){
            mySubscriptions.remove(tM);
            team.removeTeamManager(tM);
            ms.removeUser(tM);
            Subscription newSub = new Subscription(ms, tM.getName(), tM.getPhoneNumber(), tM.getEmail(), tM.getUserName(), tM.getPassword());
            return true;
        }
        return false;
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

    public HashMap<Subscription, Team> getMySubscriptions() {
        return mySubscriptions;
    }

    public BudgetControl getBudgetControl() {
        return budgetControl;
    }

    //</editor-fold>
}
