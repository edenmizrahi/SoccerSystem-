import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Coach implements PageOwner{
    private TeamRole teamRole;
    private Team coachTeam;
    private PrivatePage privatePage;
    private String roleAtTeam;
    private static final Logger LOG = LogManager.getLogger();

    public Coach(Team team, String roleAtTeam, TeamRole teamRole){
        this.roleAtTeam = roleAtTeam;
        this.coachTeam = team;
        this.privatePage = null;
        this.teamRole= teamRole;
    }

    public Coach(TeamRole teamRole) {
        this.privatePage=null;
        this.roleAtTeam=null;
        this.coachTeam=null;
        this.teamRole= teamRole;
    }

    //<editor-fold desc="getter and setters">

    public Team getCoachTeam() {
        return coachTeam;
    }

    public void setCoachTeam(Team coachTeam) {
        this.coachTeam = coachTeam;
    }

    public PrivatePage getPrivatePage() {
        return privatePage;
    }

    public void setPrivatePage(PrivatePage privatePage) {
        this.privatePage = privatePage;
    }

    public String getRoleAtTeam() {
        return roleAtTeam;
    }

    public void setRoleAtTeam(String roleAtTeam) {
        this.roleAtTeam = roleAtTeam;
    }


    //</editor-fold>


    //<editor-fold desc="Page Owner Functions">
    /**Or**/
    @Override
    public void addRecordToPage(String record) throws Exception {
        this.privatePage.addRecords(record);
    }

    /**Or**/
    @Override
    public void removeRecordFromPage(String record) throws Exception {
        this.privatePage.removeRecord(record);
    }

    /**Or**/
    @Override
    public boolean createPrivatePage() {
        PrivatePage p = new PrivatePage();
        if(this.privatePage==null){// you can have only one page
            this.privatePage=p;
            p.setPageOwner(this);
            return true;
        }
        return false;
    }

    /**Or**/
    @Override
    public PrivatePage getPage() {
        return privatePage;
    }

    //</editor-fold>

}
