import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;

class SeasonTest {

    /**Eden*/
    @Test
    void addLeagueWithTeams() throws Exception {
        MainSystem sys=MainSystem.getInstance();
        League l=new League("hahal",sys,null, null);
        Season s=new Season(sys,null,1884);

        HashSet<Team> teams1=new HashSet<Team>();
        HashSet<Player> players=new HashSet<>();
        Subscription sub=new Subscription(sys,"ttt","tt","tt","tt","ttt");
        for(int i=0;i<13 ;i++){
            Date d= new Date();
            players.add(new Player(sub,sys,d));
            /*****/
            /*****/
            System.out.println("fjj");
            System.out.println("fjj");
        }
        Subscription yossi = new Subscription(sys, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
        //or added because the change in Team constructor
        TeamOwner teamOwner = new TeamOwner(sys, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
        //
        teams1.add(new Team("hahalufa",12,players,null,null,teamOwner));
        teams1.add(new Team("hapuel",12,players,null,null,teamOwner));
        teams1.add(new Team("macabi",12,players,null,null,teamOwner));
        Team teamFor2Tests =new Team("hapuel-Rishon",12,players,null,null,teamOwner);
        teams1.add(teamFor2Tests);
        s.addLeagueWithTeams(l,teams1);
        /**check if both are equals**/
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));
        HashSet<Team> teams2=new HashSet<Team>();
        teams2.add(new Team("beitar",12,players,null,null,teamOwner));
        teams2.add(new Team("beitar2",12,players,null,null,teamOwner));
        teams2.add(teamFor2Tests);
        s.addLeagueWithTeams(l,teams1);
        /**add more teams and check if both are equals**/
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));

        /**double add again- both with no changes and equals*/
        s.addLeagueWithTeams(l,teams1);
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonLeagues().get(l));
        Assert.assertTrue(l.getTeamsInSeason().get(s).size()==6);

    }

}