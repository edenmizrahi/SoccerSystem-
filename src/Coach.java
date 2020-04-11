public class Coach extends Subscription implements PageOwner{

    Team coachTeam;
    PrivatePage coachPage;

    Coach(Subscription sub, MainSystem ms){
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
