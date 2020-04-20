import Domain.MainSystem;
import org.junit.Test;

public class LeagueTest {

    @Test
    public void addSeasonWithTeams() throws Exception {
        MainSystem sys = MainSystem.getInstance();
        //Domain.LeagueManagment.League l = new Domain.LeagueManagment.League("hahal", sys, null, null);
        //Domain.LeagueManagment.Season s = new Domain.LeagueManagment.Season(sys, null, 1884);

//        HashSet<Domain.LeagueManagment.Team> teams1=new HashSet<Domain.LeagueManagment.Team>();
//        HashSet<Domain.Users.Player> players=new HashSet<>();
//        Subscription sub=new Subscription(sys,"ttt","tt","tt","tt","ttt");
//        for(int i=0;i<13 ;i++){
//            Date d= new Date();
//            players.add(new Domain.Users.Player(sub,sys,d));
//            /*****/
//            /*****/
//            System.out.println("fjj");
//            System.out.println("fjj");
//        }
//        Subscription yossi = new Subscription(sys, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
//        //or added because the change in Domain.LeagueManagment.Team constructor
//        Domain.Users.TeamOwner teamOwner = new Domain.Users.TeamOwner(sys, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
//        Domain.LeagueManagment.Field field = new Domain.LeagueManagment.Field("Beer Sheva Domain.LeagueManagment.Field");
//
//        teams1.add(new Domain.LeagueManagment.Team("hahalufa",players,null,field,teamOwner));
//        teams1.add(new Domain.LeagueManagment.Team("hapuel",players,null,field, teamOwner));
//        teams1.add(new Domain.LeagueManagment.Team("macabi",players,null,field,teamOwner));
//        Domain.LeagueManagment.Team teamFor2Tests =new Domain.LeagueManagment.Team("hapuel-Rishon",players,null,field,teamOwner);
//        teams1.add(teamFor2Tests);
//        l.addSeasonWithTeams(s,teams1);
//        /**check if both are equals**/
//        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));
//        HashSet<Domain.LeagueManagment.Team> teams2=new HashSet<Domain.LeagueManagment.Team>();
//        teams2.add(new Domain.LeagueManagment.Team("beitar",players,null,field,teamOwner));
//        teams2.add(new Domain.LeagueManagment.Team("beitar2",players,null,field,teamOwner));
//        teams2.add(teamFor2Tests);
//        l.addSeasonWithTeams(s,teams2);
//        /**add more teams and check if both are equals**/
//        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));
//
//        /**double add again- both with no changes and equals*/
//        l.addSeasonWithTeams(s,teams2);
//        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));
//        Assert.assertTrue(l.getTeamsInSeason().get(s).size()==6);

    }
}