import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

public class TeamOwnerTest {

    MainSystem ms = MainSystem.getInstance();
    Fan yossi = new Fan(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123", MainSystem.birthDateFormat.parse("02-11-1996"));
    Team team = new Team();
    TeamRole tOYossi = new TeamRole(yossi);
    Fan moshe = new Fan(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123", MainSystem.birthDateFormat.parse("02-11-1996"));
    Fan david = new Fan(ms, "David Hamelech", "0541235678","david@gmail.com", "DavidHamelech", "David123", MainSystem.birthDateFormat.parse("02-11-1996"));

    public TeamOwnerTest() throws ParseException {
    }

    //adi
    @Test
    public void subscribeTeamOwnerTest() throws Exception {
        tOYossi.becomeTeamOwner();
        //should be createTeam instead
        tOYossi.getTeamOwner().setTeam(team);
        team.addTeamOwner(tOYossi.getTeamOwner());
        TeamOwner tOMoshe = tOYossi.getTeamOwner().subscribeTeamOwner(moshe, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(2, team.getTeamOwners().size());
        Assert.assertTrue(tOMoshe.getTeams().contains(team));

    }
    //adi
    @Test
    public void removeTeamOwnerTest() throws Exception {
        tOYossi.becomeTeamOwner();
        //should be createTeam instead
        tOYossi.getTeamOwner().setTeam(team);
        team.addTeamOwner(tOYossi.getTeamOwner());
        TeamOwner tOMoshe = tOYossi.getTeamOwner().subscribeTeamOwner(moshe, ms, team);
        TeamOwner tODavid = tOMoshe.subscribeTeamOwner(david, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(3, team.getTeamOwners().size());
        tOYossi.getTeamOwner().removeTeamOwner(tOMoshe, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(1, team.getTeamOwners().size());
    }
    //adi
    @Test
    public void subscribeTeamManagerTest() throws Exception {
        tOYossi.becomeTeamOwner();
        //should be createTeam instead
        tOYossi.getTeamOwner().setTeam(team);
        team.addTeamOwner(tOYossi.getTeamOwner());
        HashSet<Permission> per = new HashSet<>();
        per.add(Permission.addRemoveEditPlayer);
        TeamManager tMDavid = tOYossi.getTeamOwner().subscribeTeamManager(david, ms, team, per);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(tMDavid, team.getTeamManager());
    }
    //adi
    @Test
    public void removeTeamManagerTest() throws Exception {
        tOYossi.becomeTeamOwner();
        //should be createTeam instead
        tOYossi.getTeamOwner().setTeam(team);
        team.addTeamOwner(tOYossi.getTeamOwner());
        HashSet<Permission> per = new HashSet<>();
        per.add(Permission.addRemoveEditPlayer);
        TeamManager tMDavid = tOYossi.getTeamOwner().subscribeTeamManager(david, ms, team, per);
        tOYossi.getTeamOwner().removeTeamManager(tMDavid, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(null, team.getTeamManager());
    }
    //adi
    @Test
    public void addRemoveEditCoachTest() throws Exception {
        tOYossi.becomeTeamOwner();
        //should be createTeam instead
        tOYossi.getTeamOwner().setTeam(team);
        team.addTeamOwner(tOYossi.getTeamOwner());
        TeamRole teamRoleMoshe = new TeamRole(moshe);
        TeamRole teamRoleDavid = new TeamRole(david);
        Assert.assertEquals(3, ms.getUsers().size());
        Coach cDavid = new Coach(team, "mainCoach", teamRoleMoshe);
        team.addCoach(cDavid);
        tOYossi.getTeamOwner().removeAndReplaceCoach(cDavid, teamRoleDavid, "main", team);
        Assert.assertEquals(teamRoleDavid, team.getCoach().getTeamRole());
        Assert.assertEquals(team, teamRoleDavid.getCoach().getCoachTeam());
        tOYossi.getTeamOwner().editCoachRole(teamRoleDavid.getCoach(), "trainer");
        Assert.assertEquals("trainer", teamRoleDavid.getCoach().getRoleAtTeam());
    }
    //adi
    @Test(expected = Exception.class)
    public void addRemoveEditPlayerTest() throws Exception {
        tOYossi.becomeTeamOwner();
        //should be createTeam instead
        tOYossi.getTeamOwner().setTeam(team);
        team.addTeamOwner(tOYossi.getTeamOwner());
        Date d = new Date();
        TeamRole teamRoleDavid = new TeamRole(david);
        teamRoleDavid.becomePlayer();
        tOYossi.getTeamOwner().addPlayer(teamRoleDavid, "defense", team);
        Assert.assertTrue(team.getPlayers().contains(teamRoleDavid.getPlayer()));
        Assert.assertEquals(team, teamRoleDavid.getPlayer().getTeam());
        tOYossi.getTeamOwner().editPlayerRole(teamRoleDavid.getPlayer(), "defense");
        Assert.assertEquals("defense", teamRoleDavid.getPlayer().getRoleAtField());
        //doesnt remove player because less than 11, returns exception
        tOYossi.getTeamOwner().removePlayer(teamRoleDavid.getPlayer(), team);
    }
    //adi
    @Test
    public void addRemoveEditFieldTest() throws Exception {
        tOYossi.becomeTeamOwner();
        //should be createTeam instead
        tOYossi.getTeamOwner().setTeam(team);
        team.addTeamOwner(tOYossi.getTeamOwner());
        Field field = new Field("Beer Sheva Field");
        team.setField(field);
        Field field2 = new Field("Beer Sheeeeeeeva Field");
        tOYossi.getTeamOwner().removeAndReplaceField(field, field2, team);
        Assert.assertTrue(field2.getTeams().contains(team));
        Assert.assertEquals(field2, team.getField());
        Assert.assertEquals("Beer Sheeeeeeeva Field", field2.getNameOfField());
        tOYossi.getTeamOwner().editFieldName(field, "Bash Field");
        Assert.assertEquals("Bash Field", field.getNameOfField());
    }

    /**or**/
    @Test
    public void requestNewTeam() throws ParseException {
        Rfa rfa= new Rfa(ms,"RFA","0543150912","rfa@gmail.com","rfa123","rfa123",MainSystem.birthDateFormat.parse("01-12-1995"));
        TeamRole teamOwner = new TeamRole(ms,"michael","0522150912","teamO@gmail.com","owner123","owner123",MainSystem.birthDateFormat.parse("09-12-1995"));
        teamOwner.becomeTeamOwner();
        try {
            teamOwner.getTeamOwner().requestNewTeam("hapoel Beer Sheva");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(rfa.getTeamRequests().size()==1);
        Assert.assertTrue(teamOwner.getTeamOwner().getRequestedTeams().size()==1);
    }


}
