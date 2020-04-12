public class Coach extends Subscription implements PageOwner{

    Team coachTeam;
    PrivatePage coachPage;
    private String roleAtTeam;

    Coach(Subscription sub, MainSystem ms, String roleAtTeam){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        //TODO add permissions
        //this.permissions.add();
        this.roleAtTeam = roleAtTeam;
    }

    @Override
    public void openPage() {

    }

    @Override
    public void managePage() {

    }


}
