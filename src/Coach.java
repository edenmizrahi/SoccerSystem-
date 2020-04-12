public class Coach extends Subscription implements PageOwner{

    private Team coachTeam;
    private PrivatePage privatePage;
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
