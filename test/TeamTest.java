import Domain.*;
import Domain.Enums.TeamManagerPermissions;
import Domain.LeagueManagment.Field;
import Domain.LeagueManagment.Team;
import Domain.Users.Player;
import Domain.Users.TeamRole;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TeamTest {
    /**or**/
    MainSystem ms= MainSystem.getInstance();
    TeamRole teamOwner = new TeamRole(ms,"michael","0522150912","teamO@gmail.com","owner123","owner123",MainSystem.birthDateFormat.parse("09-12-1995"));
    TeamRole teamOwner1 = new TeamRole(ms,"r","0522150912","owner1O@gmail.com","r1234","r1234",MainSystem.birthDateFormat.parse("09-12-1995"));
    Team t= new Team();
    HashSet<Player> players= new HashSet<>();
    Field f= new Field("nameF");
    TeamRole coach= new TeamRole(ms,"michael","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
    HashSet<TeamManagerPermissions> per = new HashSet<>();

    public TeamTest() throws ParseException {
    }


    /**adi**/
    @Test
    public void addAndRemoveTeamOwnerTest() throws Exception{

        /****Add Domain.Users.TeamOwner****/
        /**null check**/
        try {
            t.addTeamOwner(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Domain.Users.TeamOwner is null",e.getMessage());
        }

        /**ok**/
        teamOwner.becomeTeamOwner();
        t.addTeamOwner(teamOwner.getTeamOwner());
        Assert.assertTrue(t.getTeamOwners().contains(teamOwner.getTeamOwner()));

        /**Domain.Users.TeamOwner is already in the team**/
        try {
            t.addTeamOwner(teamOwner.getTeamOwner());
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Domain.Users.TeamOwner is already in this team",e.getMessage());
        }

        /****Remove Domain.Users.TeamOwner****/
        /**Domain.Users.TeamOwner doesnt exist in the team**/
        teamOwner1.becomeTeamOwner();
        try {
            t.removeTeamOwner(teamOwner1.getTeamOwner());
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Domain.Users.TeamOwner doesn't exist in this team",e.getMessage());
        }

        /**ok**/
        t.removeTeamOwner(teamOwner.getTeamOwner());
        Assert.assertFalse(t.getTeamOwners().contains(teamOwner.getTeamOwner()));
        /**null check**/
        try {
            t.removeTeamOwner(null);
            fail();
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Domain.Users.TeamOwner is null",e.getMessage());
        }

    }

    /**adi**/
    @Test (expected = Exception.class)
    public void removeTeamManagerTest() throws Exception{
        per.add(TeamManagerPermissions.addRemoveEditTeamOwner);
        teamOwner.becomeTeamManager(t, per);
        Assert.assertTrue(t.getTeamManager().equals(teamOwner.getTeamManager()));
        t.removeTeamManager(teamOwner.getTeamManager());
        Assert.assertFalse(t.getTeamManager().equals(teamOwner.getTeamManager()));
    }
    /**adi**/
    @Test (expected = Exception.class)
    public void addAndRemoveCoachTest() throws Exception{
        coach.becomeCoach();
        t.addCoach(coach.getCoach());
        Assert.assertTrue(t.getCoach().equals(coach.getCoach()));
        t.removeCoach(coach.getCoach());
        Assert.assertFalse(t.getCoach().equals(coach.getCoach()));
    }
    /**adi**/
    @Test (expected = Exception.class)
    public void addAndRemovePlayerTest() throws Exception{
        coach.becomePlayer();
        t.addPlayer(coach.getPlayer());
        Assert.assertTrue(t.getPlayers().contains(coach.getPlayer()));
        t.removePlayer(coach.getPlayer());
        Assert.assertTrue(t.getPlayers().contains(coach.getPlayer()));
    }
    /**adi**/
    @Test (expected = Exception.class)
    public void removeFieldTest() throws Exception{
        t.removeField(f);
        t.getField();
    }

    /**or**/
    @Test
    public void becomeActive() throws ParseException {
        //init
        int counter=0;
        while(counter<11){
            TeamRole player= new TeamRole(ms,"player", "1234567890","email@gmail.com","player"+counter,"player"+counter,MainSystem.birthDateFormat.parse("09-12-1995"));
            player.becomePlayer();
            players.add(player.getPlayer());
            counter++;
        }
        coach.becomeCoach();


        Assert.assertFalse(t.isActive());
        try {
            t.becomeActive(players,coach.getCoach(),f);
        } catch (Exception e) {
            fail();
        }
        Assert.assertTrue(t.isActive());
        Assert.assertTrue(t.getPlayers().size()!=0);
    }

    /**or**/
    @Test
    public void deleteTeamByTeamOwner() throws ParseException {
        //init
        int counter=0;
        while(counter<11){
            TeamRole player= new TeamRole(ms,"player", "1234567890","email@gmail.com","player"+counter,"player"+counter,MainSystem.birthDateFormat.parse("09-12-1995"));
            player.becomePlayer();
            players.add(player.getPlayer());
            counter++;
        }
        coach.becomeCoach();
        try {
            t.becomeActive(players,coach.getCoach(),f);
        } catch (Exception e) {
            fail();
        }

        t.deleteTeamByTeamOwner();
        Assert.assertFalse(t.isActive());
        Assert.assertTrue(t.getCoach().getCoachTeam()==null);
    }

    @Test
    public void addCoach() throws ParseException {
        //Domain.Users.TeamRole coach= new Domain.Users.TeamRole(ms,"coach","1234567890","coach@gmail.com","coach101","coach101",Domain.MainSystem.birthDateFormat.parse("01-11-2000"));
        coach.becomeCoach();
        t.addCoach(null);

        t.addCoach(coach.getCoach());
        Assert.assertEquals(t.getCoach(),coach.getCoach());


    }
}