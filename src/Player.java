import java.util.Date;

public class Player extends Subscription implements PageOwner {
    private PrivatePage privatePage;
    private Team playerTeam;
    private Date dateOfBirth;
    private String roleAtField;

    public Player(Subscription sub, MainSystem ms){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.roleAtField = roleAtField;
        this.dateOfBirth = dateOfBirth;
        //TODO add permissions
        //this.permissions.add();
        this.privatePage=null;
        this.playerTeam=null;
    }

    public Player(MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms, name, phoneNumber, email, userName, password);
        this.privatePage=null;
        this.playerTeam=null;
    }

    public Player(MainSystem ms, String name, String phoneNumber, String email, String userName, String password, PrivatePage privatePage, Team playerTeam) {
        super(ms, name, phoneNumber, email, userName, password);
        this.privatePage = privatePage;
        this.playerTeam = playerTeam;
    }

    @Override
    public void openPage() {

    }

    @Override
    public void managePage() {

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
