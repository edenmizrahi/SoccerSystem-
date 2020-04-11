public class Player extends Subscription implements PageOwner {
    PrivatePage playerPage;
    Team playerTeam;

    Player(Subscription sub, MainSystem ms){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
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
