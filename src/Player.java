import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class Player extends Subscription implements PageOwner {
    private PrivatePage privatePage;
    private Team playerTeam;
    private Date dateOfBirth;
    private String roleAtField;
    private static final Logger LOG = LogManager.getLogger();

    public Player(Subscription sub, MainSystem ms, Date dateOfBirth){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.roleAtField = null;
        this.dateOfBirth = dateOfBirth;
        //TODO add permissions
        //this.permissions.add();
        this.privatePage = null;
        this.playerTeam = null;
    }

    public Player(MainSystem ms, String name, String phoneNumber, String email, String userName, String password, Date dateOfBirth) {
        super(ms, name, phoneNumber, email, userName, password);
        this.dateOfBirth = dateOfBirth;
        this.privatePage = null;
        this.playerTeam = null;
        this.roleAtField = null;
    }

    public Player(MainSystem ms, String name, String phoneNumber, String email, String userName, String password, Date dateOfBirth, PrivatePage privatePage, Team playerTeam) {
        super(ms, name, phoneNumber, email, userName, password);
        this.dateOfBirth = dateOfBirth;
        this.privatePage = privatePage;
        this.playerTeam = playerTeam;
        this.roleAtField = null;
    }

    @Override
    public void addRecordToPage(String record) {
        this.privatePage.addRecords(record);
    }

    @Override
    public void removeRecordFromPage(String record) {
        this.privatePage.removeRecord(record);
    }

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

    @Override
    public PrivatePage getPage() {
        return privatePage;
    }

    //<editor-fold desc="getters and setters">
    public Team getTeam() {
        return playerTeam;
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
