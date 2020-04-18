import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TeamManagerTest {
    MainSystem ms = MainSystem.getInstance();
//    Subscription yossi = new Subscription(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
//    Team team = new Team();
//    HashSet<Permission> per = new HashSet<>();
//    TeamManager tMYossi = new TeamManager(yossi, ms, team, per);
//    Subscription moshe = new Subscription(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123" );
//    Subscription david = new Subscription(ms, "David Hamelech", "0541235678","david@gmail.com", "DavidHamelech", "David123" );
//
//    //adi
//    @Test
//    public void subscribeTeamOwnerTest() throws Exception {
//        tMYossi.addPermission(Permission.addRemoveEditTeamOwner);
//        TeamOwner tOMoshe = tMYossi.subscribeTeamOwner(moshe, ms, team);
//        Assert.assertEquals(3, ms.getUsers().size());
//        Assert.assertEquals(1, team.getTeamOwners().size());
//    }
//    //adi
//    @Test
//    public void removeTeamOwnerTest() throws Exception {
//        tMYossi.addPermission(Permission.addRemoveEditTeamOwner);
//        TeamOwner tOMoshe = tMYossi.subscribeTeamOwner(moshe, ms, team);
//        TeamOwner tODavid = tOMoshe.subscribeTeamOwner(david, ms, team);
//        Assert.assertEquals(3, ms.getUsers().size());
//        Assert.assertEquals(2, team.getTeamOwners().size());
//        tMYossi.removeTeamOwner(tOMoshe, ms, team);
//        Assert.assertEquals(3, ms.getUsers().size());
//        Assert.assertEquals(0, team.getTeamOwners().size());
//    }
//    //adi
//    @Test
//    public void addRemoveEditCoachTest() throws Exception {
//        tMYossi.addPermission(Permission.addRemoveEditCoach);
//        Coach cDavid = new Coach(david, ms, "mainCoach");
//        tMYossi.addCoach(cDavid, team);
//        Assert.assertEquals(cDavid, team.getCoach());
//        Assert.assertEquals(team, cDavid.getCoachTeam());
//        tMYossi.editCoachRole(cDavid, "trainer");
//        Assert.assertEquals("trainer", cDavid.getRoleAtTeam());
//        //tMYossi.removeCoach(cDavid, team);
//        Assert.assertEquals(null, team.getCoach());
//    }
//    //adi
//    @Test(expected = Exception.class)
//    public void addRemoveEditPlayerTest() throws Exception {
//        tMYossi.addPermission(Permission.addRemoveEditPlayer);
//        Date d = new Date();
//        Player pDavid = new Player(david, ms, d);
//        tMYossi.addPlayer(pDavid, team);
//        Assert.assertTrue(team.getPlayers().contains(pDavid));
//        Assert.assertEquals(team, pDavid.getTeam());
//        tMYossi.editPlayerRole(pDavid, "defense");
//        Assert.assertEquals("defense", pDavid.getRoleAtField());
//        //doesnt remove player because less than 11, returns exception
//        tMYossi.removePlayer(pDavid, team);
//    }
//    //adi
//    @Test
//    public void addRemoveEditFieldTest() throws Exception {
//        tMYossi.addPermission(Permission.addRemoveEditField);
//        Field field = new Field("Beer Sheva Field");
//        tMYossi.addField(field, team);
//        Assert.assertTrue(field.getTeams().contains(team));
//        Assert.assertEquals(field, team.getField());
//        Assert.assertEquals("Beer Sheva Field", field.getNameOfField());
//        tMYossi.editFieldName(field, "Bash Field");
//        Assert.assertEquals("Bash Field", field.getNameOfField());
//        tMYossi.removeField(field, team);
//        Assert.assertEquals(null, team.getField());
//        Assert.assertFalse(field.getTeams().contains(team));
//    }

}