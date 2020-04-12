import java.util.Date;

public class Player extends Subscription implements PageOwner {
    PrivatePage playerPage;
    Team playerTeam;
    private Date dateOfBirth;
    private String roleAtField;

    Player(Subscription sub, MainSystem ms, String roleAtField, Date dateOfBirth){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.roleAtField = roleAtField;
        this.dateOfBirth = dateOfBirth;
        //TODO add permissions
        //this.permissions.add();
    }

    @Override
    public void openPage() {

    }

    @Override
    public void managePage() {

    }
}
