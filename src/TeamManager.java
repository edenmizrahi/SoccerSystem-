import java.util.HashSet;

public class TeamManager extends Subscription{

    private Team team;
    private HashSet<Notification> notifications;

    public TeamManager(Team team, HashSet<Notification> notifications) {
        this.team = team;
        this.notifications = notifications;
    }

    public Team getTeam() { return team; }

    public HashSet<Notification> getNotifications() { return notifications; }

    public void setTeam(Team team) { this.team = team; }

    public void setNotifications(HashSet<Notification> notifications) { this.notifications = notifications; }

    /****more functions of team management****/
}
