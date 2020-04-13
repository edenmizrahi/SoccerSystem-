import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Coach extends Subscription implements PageOwner{

    private Team coachTeam;
    private PrivatePage privatePage;
    private String roleAtTeam;
    private static final Logger LOG = LogManager.getLogger();

    public Coach(Subscription sub, MainSystem ms, String roleAtTeam){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        //TODO add permissions
        //this.permissions.add();
        this.roleAtTeam = roleAtTeam;
    }

    public Coach(MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms,name,phoneNumber,email,userName,password);
        this.privatePage=null;
        this.roleAtTeam=null;
        this.coachTeam=null;
        //TODO add permissions
        //this.permissions.add();
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


}
