import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

public class TeamOwnerTest {

    MainSystem ms = MainSystem.getInstance();
    Subscription yossi = new Subscription(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
    Team team = new Team();
    TeamOwner tOYossi = new TeamOwner(yossi, ms, team);
    Subscription moshe = new Subscription(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123" );
    Subscription david = new Subscription(ms, "David Hamelech", "0541235678","david@gmail.com", "DavidHamelech", "David123" );

    //adi
    @Test
    public void subscribeTeamOwnerTest() throws Exception {
        TeamOwner tOMoshe = tOYossi.subscribeTeamOwner(moshe, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(2, team.getTeamOwners().size());
    }
    //adi
    @Test
    public void removeTeamOwnerTest() throws Exception {
        TeamOwner tOMoshe = tOYossi.subscribeTeamOwner(moshe, ms, team);
        TeamOwner tODavid = tOMoshe.subscribeTeamOwner(david, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(3, team.getTeamOwners().size());
        tOYossi.removeTeamOwner(tOMoshe, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(1, team.getTeamOwners().size());
    }
    //adi
    @Test
    public void subscribeTeamManagerTest() throws Exception {
        HashSet<Permission> per = new HashSet<>();
        per.add(Permission.addRemoveEditPlayer);
        TeamManager tMDavid = tOYossi.subscribeTeamManager(david, ms, team, per);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(tMDavid, team.getTeamManager());
    }
    //adi
    @Test
    public void removeTeamManagerTest() throws Exception {
        HashSet<Permission> per = new HashSet<>();
        per.add(Permission.addRemoveEditPlayer);
        TeamManager tMDavid = tOYossi.subscribeTeamManager(david, ms, team, per);
        tOYossi.removeTeamManager(tMDavid, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(null, team.getTeamManager());
    }
    //adi
    @Test
    public void addTeamManagerTest() throws Exception {
        HashSet<Permission> per = new HashSet<>();
        per.add(Permission.addRemoveEditPlayer);
        TeamManager tMDavid = new TeamManager(david, ms, team, per);
        tOYossi.addTeamManager(tMDavid, team);
        Assert.assertEquals(tMDavid, team.getTeamManager());
    }
    //adi
    @Test
    public void addRemoveEditCoachTest() throws Exception {
        Coach cDavid = new Coach(david, ms, "mainCoach");
        tOYossi.addCoach(cDavid, team);
        Assert.assertEquals(cDavid, team.getCoach());
        Assert.assertEquals(team, cDavid.getCoachTeam());
        tOYossi.editCoachRole(cDavid, "trainer");
        Assert.assertEquals("trainer", cDavid.getRoleAtTeam());
        //tOYossi.removeCoach(cDavid, team);
        Assert.assertEquals(null, team.getCoach());
    }
    //adi
    @Test(expected = Exception.class)
    public void addRemoveEditPlayerTest() throws Exception {
        Date d = new Date();
        Player pDavid = new Player(david, ms, d);
        tOYossi.addPlayer(pDavid, team);
        Assert.assertTrue(team.getPlayers().contains(pDavid));
        Assert.assertEquals(team, pDavid.getTeam());
        tOYossi.editPlayerRole(pDavid, "defense");
        Assert.assertEquals("defense", pDavid.getRoleAtField());
        //doesnt remove player because less than 11, returns exception
        tOYossi.removePlayer(pDavid, team);
    }
    //adi
    @Test
    public void addRemoveEditFieldTest() throws Exception {
        Field field = new Field("Beer Sheva Field");
        tOYossi.addField(field, team);
        Assert.assertTrue(field.getTeams().contains(team));
        Assert.assertEquals(field, team.getField());
        Assert.assertEquals("Beer Sheva Field", field.getNameOfField());
        tOYossi.editFieldName(field, "Bash Field");
        Assert.assertEquals("Bash Field", field.getNameOfField());
        tOYossi.removeField(field, team);
        Assert.assertEquals(null, team.getField());
        Assert.assertFalse(field.getTeams().contains(team));
    }

    /**or**/
    @Test
    public void openNewTeam() throws ParseException {
        Rfa rfa= new Rfa(ms,"RFA","0543150912","rfa@gmail.com","rfa123","rfa123",MainSystem.birthDateFormat.parse("01-12-1995"));
        TeamRole teamOwner = new TeamRole(ms,"michael","0522150912","teamO@gmail.com","owner123","owner123",MainSystem.birthDateFormat.parse("09-12-1995"));
        teamOwner.becomeTeamOwner();
        teamOwner.getTeamOwner().requestNewTeam("hapoel Beer Sheva");
        Assert.assertTrue(rfa.getTeamRequests().size()==1);
        Assert.assertTrue(teamOwner.getTeamOwner().getRequestedTeams().size()==1);
    }


}
