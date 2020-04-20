import Domain.*;
import Domain.Events.Event;
import Domain.LeagueManagment.Field;
import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Team;
import Domain.Users.Player;
import Domain.Users.Referee;
import Domain.Users.Rfa;
import Domain.Users.TeamRole;
import Stubs.TeamStub;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashSet;

import static org.junit.Assert.*;

public class RfaTest {

    MainSystem ms = MainSystem.getInstance();
    Rfa nadav = new Rfa(ms,"nadav","052","nadav@","nadavS", "nadav123",MainSystem.birthDateFormat.parse("06-07-1992"));

    public RfaTest() throws ParseException {
    }

    /**Yarden**/
    @Test
    public void addReferee() throws Exception {

        nadav.addReferee("moshe","0546145795","moshe@gmail.com","moshe123","moshe123","a",MainSystem.birthDateFormat.parse("08-09-1995"));
        assertEquals(2,ms.getUsers().size());
        //invalid details

        try {
            nadav.addReferee("moshe", "0546", "moshe@gmail.com", "moshe123", "moshe123", "a", MainSystem.birthDateFormat.parse("08-09-1995"));
            fail();
        }
        catch (Exception e) {
            assertEquals(Exception.class, e.getClass());
            assertEquals("Invalid details - You can not add this referee",e.getMessage());
        }
        assertEquals(2, ms.getUsers().size());

    }

    /**Yarden**/
    @Test
    public void deleteReferee() throws Exception {

        /**referee is null**/
        try {
            nadav.deleteReferee(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Domain.Users.Referee is null",e.getMessage());
        }

        /**everything is ok**/
        Referee moshe = new Referee(ms,"moshe","0546145795","moseh@gmail.com","moshe123","moshe123","a",MainSystem.birthDateFormat.parse("08-09-1995"));
        TeamRole teamRole1 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden123", "yarden123", MainSystem.birthDateFormat.parse("08-09-1995"));
        TeamRole teamRole2 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden234", "yarden234", MainSystem.birthDateFormat.parse("09-09-1995"));
        TeamRole teamRole3 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden345", "yarden345", MainSystem.birthDateFormat.parse("10-09-1995"));
        TeamRole teamRole4 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden456", "yarden456", MainSystem.birthDateFormat.parse("11-09-1995"));
        TeamRole teamRole5 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden567", "yarden567", MainSystem.birthDateFormat.parse("12-09-1995"));
        TeamRole teamRole6 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden678", "yarden678", MainSystem.birthDateFormat.parse("13-09-1995"));
        TeamRole teamRole7 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden789", "yarden789", MainSystem.birthDateFormat.parse("14-09-1995"));
        TeamRole teamRole8 = new TeamRole(ms,"yarden","0546260171","yarden@gmail.com","yarden012", "yarden012", MainSystem.birthDateFormat.parse("15-09-1995"));

        Team t1 = new Team();
        Team t2 = new Team();

        teamRole1.becomePlayer();
        teamRole2.becomePlayer();
        teamRole3.becomePlayer();
        teamRole4.becomePlayer();
        teamRole5.becomePlayer();
        teamRole6.becomePlayer();
        teamRole7.becomePlayer();
        teamRole8.becomePlayer();

        t1.addPlayer(teamRole1.getPlayer());
        t1.addPlayer(teamRole2.getPlayer());
        t1.addPlayer(teamRole3.getPlayer());
        t1.addPlayer(teamRole4.getPlayer());

        t2.addPlayer(teamRole5.getPlayer());
        t2.addPlayer(teamRole6.getPlayer());
        t2.addPlayer(teamRole7.getPlayer());
        t2.addPlayer(teamRole8.getPlayer());

        t1.setName("Hapoel");
        Match m1 = new Match(0,0,t1,t2, new Field("f1"), new HashSet<Event>(), new HashSet<Referee>()
                , moshe,"17-04-2020 20:00:00");

        nadav.deleteReferee(moshe);

        /**there is a future match in his list**/
        Referee ref = new Referee(ms,"ref","0546145795","moseh@gmail.com","ref123","ref123","a",MainSystem.birthDateFormat.parse("08-09-1995"));
        Match m2 = new Match(0,0,t1,t2, new Field("f1"), new HashSet<Event>(), new HashSet<Referee>()
                , ref,"21-04-2020 20:00:00");
        try {
            nadav.deleteReferee(ref);
            fail();
        }
        catch (Exception e){
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("You can not delete this referee",e.getMessage());
        }
    }

    /**Yarden**/
    @Test
    public void createNewLeague(){


    }

    /**Yarden**/
    @Test
    public void defineSeasonToLeague(){

    }

    /**Yarden**/
    @Test
    public void startSchedulingPolicy(){

    }

    /**Yarden**/
    @Test
    public void startCalculationPolicy(){

    }

    /**or**/
    @Test
    public void answerRequest() throws ParseException {
        TeamStub team = new TeamStub("name");
        TeamRole owner= new TeamRole(ms,"coach","1234567890","coach@gmail.com","coach101","coach101",MainSystem.birthDateFormat.parse("01-11-2000"));
        owner.becomeTeamOwner();
        try {
            team.addTeamOwner(owner.getTeamOwner());
        } catch (Exception e) {
            fail();
        }
        Rfa rfa= new Rfa(ms,"nadav","052","nadav@gmail.com","nadavS", "nadav123", MainSystem.birthDateFormat.parse("01-02-1990"));
        rfa.getTeamRequests().add(team);

        try {
            rfa.answerRequest(team,true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Assert.assertTrue(rfa.getTeamRequests().size()==0);

        try {
            rfa.answerRequest(team,true);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("team not in request list",e.getMessage());
        }
    }
}