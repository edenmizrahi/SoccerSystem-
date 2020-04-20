import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TeamTest {
    /**or**/
    MainSystem ms= MainSystem.getInstance();
    TeamRole teamOwner = new TeamRole(ms,"michael","0522150912","teamO@gmail.com","owner123","owner123",MainSystem.birthDateFormat.parse("09-12-1995"));
    Team t= new Team();
    HashSet<Player> players= new HashSet<>();
    Field f= new Field("nameF");
    TeamRole coach= new TeamRole(ms,"michael","0522150912","teamO@gmail.com","coach2232","coach2232",MainSystem.birthDateFormat.parse("09-12-1995"));
    HashSet<Permission> per = new HashSet<>();

    public TeamTest() throws ParseException {
    }


    /**adi**/
    @Test
    public void addAndRemoveTeamOwnerTest() throws Exception{
        teamOwner.becomeTeamOwner();
        t.addTeamOwner(teamOwner.getTeamOwner());
        Assert.assertTrue(t.getTeamOwners().contains(teamOwner.getTeamOwner()));
        t.removeTeamOwner(teamOwner.getTeamOwner());
        Assert.assertFalse(t.getTeamOwners().contains(teamOwner.getTeamOwner()));
    }
    /**adi**/
    @Test (expected = Exception.class)
    public void removeTeamManagerTest() throws Exception{
        per.add(Permission.addRemoveEditTeamOwner);
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
        //TeamRole coach= new TeamRole(ms,"coach","1234567890","coach@gmail.com","coach101","coach101",MainSystem.birthDateFormat.parse("01-11-2000"));
        coach.becomeCoach();
        t.addCoach(null);

        t.addCoach(coach.getCoach());
        Assert.assertEquals(t.getCoach(),coach.getCoach());


    }
}