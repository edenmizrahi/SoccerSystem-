import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;

public class TeamManager extends Subscription{

    private Team team;
    private static final Logger LOG = LogManager.getLogger();

    public TeamManager(Subscription sub, MainSystem ms, Team team) {

        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        ms.removeUser(sub);
        this.team = team;
        this.team.setTeamManager(this);
        //TODO add permissions
        //this.permissions.add();
    }

    //<editor-fold desc="getters and setters">
    public Team getTeam() { return team; }


    public void setTeam(Team team) { this.team = team; }
    //</editor-fold>

    /****more functions of team management****/
}
