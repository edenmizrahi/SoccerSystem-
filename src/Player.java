import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.commons.util.PackageUtils;

import java.util.Date;

public class Player implements PageOwner {
    private PrivatePage privatePage;
    private Team playerTeam;
    private Date dateOfBirth;
    private String roleAtField;
    private TeamRole teamRole;
    private static final Logger LOG = LogManager.getLogger();

    public Player(TeamRole teamRole,Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
        this.privatePage = null;
        this.playerTeam = null;
        this.roleAtField = null;
        this.teamRole= teamRole;
    }

    public Player(TeamRole teamRole,Date dateOfBirth, Team playerTeam) {
        this.dateOfBirth = dateOfBirth;
        this.privatePage = null;
        this.playerTeam = playerTeam;
        this.roleAtField = null;
        this.teamRole= teamRole;
    }

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



    //<editor-fold desc="getters and setters">
    public Team getTeam() {
        return playerTeam;
    }

    public TeamRole getTeamRole() {
        return teamRole;
    }
    public PrivatePage getPrivatePage() {
        return privatePage;
    }

    public void setPrivatePage(PrivatePage privatePage) {
        this.privatePage = privatePage;
    }


    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRoleAtField() {
        return roleAtField;
    }

    public void setRoleAtField(String roleAtField) {
        this.roleAtField = roleAtField;
    }
    //</editor-fold>



}
