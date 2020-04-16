import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

public class LeagueTest {

    @Test
    public void addSeasonWithTeams() throws Exception {
        MainSystem sys=new MainSystem();
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
        TeamOwner teamOwner = new TeamOwner(yossi, sys, new Team());
        Field field = new Field("Beer Sheva Field");
        //
        teams1.add(new Team("hahalufa",players,null,field,teamOwner));
        teams1.add(new Team("hapuel",players,null,field, teamOwner));
        teams1.add(new Team("macabi",players,null,field,teamOwner));
        Team teamFor2Tests =new Team("hapuel-Rishon",players,null,field,teamOwner);
        teams1.add(teamFor2Tests);
        l.addSeasonWithTeams(s,teams1);
        /**check if both are equals**/
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonleagues().get(l));
        HashSet<Team> teams2=new HashSet<Team>();
        teams2.add(new Team("beitar",players,null,field,teamOwner));
        teams2.add(new Team("beitar2",players,null,field,teamOwner));
        teams2.add(teamFor2Tests);
        l.addSeasonWithTeams(s,teams2);
        /**add more teams and check if both are equals**/
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonleagues().get(l));

        /**double add again- both with no changes and equals*/
        l.addSeasonWithTeams(s,teams2);
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonleagues().get(l));
        Assert.assertTrue(l.getTeamsInSeason().get(s).size()==6);

    }
}