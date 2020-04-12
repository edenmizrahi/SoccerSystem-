import java.util.HashSet;
import java.util.LinkedList;

public class TeamManager extends Subscription{

    private Team team;
    private LinkedList<Notification> notifications;

    public TeamManager(Subscription sub, MainSystem ms, Team team) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.team = team;
        this.notifications = new LinkedList<>();
        //TODO add permissions
        //this.permissions.add();
    }

    //<editor-fold desc="getters and setters">
    public Team getTeam() { return team; }

    public LinkedList<Notification> getNotifications() { return notifications; }

    public void setTeam(Team team) { this.team = team; }
    //</editor-fold>

    /****more functions of team management****/
}
