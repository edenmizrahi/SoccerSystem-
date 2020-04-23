import Domain.*;
import Domain.LeagueManagment.Calculation.CalculateOption1;
import Domain.LeagueManagment.Calculation.CalculationPolicy;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Scheduling.SchedualeOption1;
import Domain.LeagueManagment.Scheduling.SchedulingPolicy;
import Domain.LeagueManagment.Season;
import Domain.Users.Rfa;
import Domain.Users.User;
import Stubs.TeamStub;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.fail;

public class MainSystemTest {
    MainSystem ms= MainSystem.getInstance();

    /**or**/
    @Test
    public void removeUser() {
        User user= new User(ms);

        Assert.assertTrue(ms.removeUser(user));
        Assert.assertFalse(ms.getUsers().contains(user));
    }

    /**or**/
    @Test
    public void addUser() {
        User user= new User(ms);
        Assert.assertTrue(ms.getUsers().contains(user));
        Assert.assertFalse(ms.addUser(user));
    }


    @Test
    /**or**/
    public void removeLeague() {
        try {
            League league= new League("Laliga",ms);
            Assert.assertTrue(ms.getLeagues().size()==1);
            Assert.assertTrue(ms.removeLeague(league));
            Assert.assertTrue(ms.getLeagues().size()==0);
        } catch (Exception e) {
            fail();
            e.printStackTrace();

        }
    }

    @Test
    /**or**/
    public void addLeague() {
        try {
            League league= new League("Laliga",ms);
            Assert.assertTrue(ms.getLeagues().size()==1);
            Assert.assertFalse(ms.addLeague(league));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    /**or**/
    public void removeSeason() {
        try {
            SchedulingPolicy sp= new SchedualeOption1();
            CalculationPolicy cp= new CalculateOption1();
            Season s= new Season(ms,sp,cp,2004);
            Assert.assertTrue(ms.getSeasons().size()==1);
            Assert.assertTrue(ms.removeSeason(s));
            Assert.assertTrue(ms.getSeasons().size()==0);
        } catch (Exception e) {
            fail();
            e.printStackTrace();

        }
    }

    @Test
    /**or**/
    public void addSeason() {
        try {
            SchedulingPolicy sp= new SchedualeOption1();
            CalculationPolicy cp= new CalculateOption1();
            Season s= new Season(ms,sp,cp,2004);
            Assert.assertTrue(ms.getSeasons().size()==1);
            Assert.assertFalse(ms.addSeason(s));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    /**or**/
    public void removeActiveTeam() {
        try {
            TeamStub team = new TeamStub("hapoel beersheva");
            ms.addActiveTeam(team);
            Assert.assertTrue(ms.getActiveTeams().size()==1);
            Assert.assertTrue(ms.removeActiveTeam(team));
            Assert.assertTrue(ms.getSeasons().size()==0);
        } catch (Exception e) {
            fail();
            e.printStackTrace();

        }
    }

    @Test
    /**or**/
    public void addActiveTeam() {
        try {
            TeamStub team = new TeamStub("hapoel beersheva");
            ms.addActiveTeam(team);
            Assert.assertTrue(ms.getActiveTeams().size()==1);
            Assert.assertFalse(ms.addActiveTeam(team));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void addTeamName() {

       Assert.assertTrue(ms.addTeamName("teamName"));
        Assert.assertFalse(ms.addTeamName("teamName"));
    }

    @Test
    public void removeUserName() {
        Assert.assertFalse(ms.removeUserName("userName"));
        ms.addUserName("userName");
        Assert.assertTrue(ms.removeUserName("userName"));
    }

    @Test
    public void removeTeamName() {

        Assert.assertFalse(ms.removeTeamName("teamName"));
        ms.addTeamName("teamName");
        Assert.assertTrue(ms.removeTeamName("teamName"));
    }

    @Test
    /**or**/
    public void firstStartSystem() throws ParseException {
        ms.startSystem();
        Assert.assertTrue(ms.getSystemManagers().size()==1);
        Assert.assertTrue(ms.getExtSystem()!=null);

    }

    @Test
    /**or**/
    public void startSystem() throws ParseException {
        ms.startSystem();
        Assert.assertTrue(ms.getExtSystem()!=null);
        Assert.assertTrue(ms.getSystemManagers().size()==1);
    }

    @Test
    /**or**/
    public void numOfRFA() throws ParseException {
        Assert.assertEquals(0,ms.numOfRfa());
        Rfa rfa= new Rfa(ms,"rfa123","0542150912","oralfasi@gmail.com","rfa1234","rfa1234",MainSystem.birthDateFormat.parse("09-12-1995"));
        Assert.assertEquals(1,ms.numOfRfa());
    }
}