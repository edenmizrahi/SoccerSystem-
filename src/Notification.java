import java.util.HashSet;

public class Notification {

    private String text;
    /**should hold many?*/
    private SystemManager systemManager ;

    /**must hold all?**/
    private HashSet<TeamManager> teamManagers;
    private HashSet<TeamOwner> teamOwners;
    private HashSet<Referee> referees;
    private HashSet<Fan> fans;
    private HashSet<Rfa> rfas;

    public Notification(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SystemManager getSystemManager() {
        return systemManager;
    }

    public void setSystemManager(SystemManager systemManager) {
        this.systemManager = systemManager;
    }

    public HashSet<TeamManager> getTeamManagers() {
        return teamManagers;
    }

    public void setTeamManagers(HashSet<TeamManager> teamManagers) {
        this.teamManagers = teamManagers;
    }

    public HashSet<TeamOwner> getTeamOwners() {
        return teamOwners;
    }

    public void setTeamOwners(HashSet<TeamOwner> teamOwners) {
        this.teamOwners = teamOwners;
    }

    public HashSet<Referee> getReferees() {
        return referees;
    }

    public void setReferees(HashSet<Referee> referees) {
        this.referees = referees;
    }

    public HashSet<Fan> getFans() {
        return fans;
    }

    public void setFans(HashSet<Fan> fans) {
        this.fans = fans;
    }

    public HashSet<Rfa> getRfas() {
        return rfas;
    }

    public void setRfas(HashSet<Rfa> rfas) {
        this.rfas = rfas;
    }
}
