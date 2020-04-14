import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

public class LeagueTest {

    @Test
    public void addSeasonWithTeams() throws Exception {
        League l=new League("hahal",null,null, null);
        Season s=new Season(null,1884);
        MainSystem sys=new MainSystem(null);
        HashSet<Team> teams1=new HashSet<Team>();
        HashSet<Player> players=new HashSet<>();
        Subscription sub=new Subscription(sys,"ttt","tt","tt","tt","ttt");
        for(int i=0;i<13 ;i++){
            Date d= new Date();
            players.add(new Player(sub,sys,d));

        }
        teams1.add(new Team("hahalufa",12,players,null,null));
        teams1.add(new Team("hapuel",12,players,null,null));
        teams1.add(new Team("macabi",12,players,null,null));
        Team teamFor2Tests =new Team("hapuel-Rishon",12,players,null,null);
        teams1.add(teamFor2Tests);
        l.addSeasonWithTeams(s,teams1);
        /**check if both are equals**/
        Assert.assertEquals(l.getTeamsInSeason().get(s),s.getTeamsInCurrentSeasonleagues().get(l));
        HashSet<Team> teams2=new HashSet<Team>();
        teams2.add(new Team("beitar",12,players,null,null));
        teams2.add(new Team("beitar2",12,players,null,null));
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