import Domain.MainSystem;
import org.junit.Test;

public class LeagueTest {

    @Test
    public void addSeasonWithTeams() throws Exception {
        MainSystem sys = MainSystem.getInstance();
        //Domain.League l = new Domain.League("hahal", sys, null, null);
        //Domain.Season s = new Domain.Season(sys, null, 1884);

//        HashSet<Domain.Team> teams1=new HashSet<Domain.Team>();
//        HashSet<Domain.Player> players=new HashSet<>();
//        Subscription sub=new Subscription(sys,"ttt","tt","tt","tt","ttt");
//        for(int i=0;i<13 ;i++){
//            Date d= new Date();
//            players.add(new Domain.Player(sub,sys,d));
//            /*****/
//            /*****/
//            System.out.println("fjj");
//            System.out.println("fjj");
//        }
//        Subscription yossi = new Subscription(sys, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
//        //or added because the change in Domain.Team constructor
//        Domain.TeamOwner teamOwner = new Domain.TeamOwner(sys, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
//        Domain.Field field = new Domain.Field("Beer Sheva Domain.Field");
//
//        teams1.add(new Domain.Team("hahalufa",players,null,field,teamOwner));
//        teams1.add(new Domain.Team("hapuel",players,null,field, teamOwner));
//        teams1.add(new Domain.Team("macabi",players,null,field,teamOwner));
//        Domain.Team teamFor2Tests =new Domain.Team("hapuel-Rishon",players,null,field,teamOwner);
//        teams1.add(teamFor2Tests);
//        l.addSeasonWithTeams(s,teams1);
//        /**check if both are equals**/
//        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));
//        HashSet<Domain.Team> teams2=new HashSet<Domain.Team>();
//        teams2.add(new Domain.Team("beitar",players,null,field,teamOwner));
//        teams2.add(new Domain.Team("beitar2",players,null,field,teamOwner));
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