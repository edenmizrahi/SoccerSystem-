import Domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

public class TeamManagerTest {
    MainSystem ms = MainSystem.getInstance();
    Fan yossi = new Fan(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123", MainSystem.birthDateFormat.parse("02-11-1996") );
    Team team = new Team();
    HashSet<Permission> per = new HashSet<>();
    TeamRole tMYossi = new TeamRole(yossi);
    Fan moshe = new Fan(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123", MainSystem.birthDateFormat.parse("02-11-1996"));
    Fan david = new Fan(ms, "David Hamelech", "0541235678","david@gmail.com", "DavidHamelech", "David123", MainSystem.birthDateFormat.parse("02-11-1996"));

    public TeamManagerTest() throws ParseException {
    }

    //adi
    @Test
    public void subscribeTeamOwnerTest() throws Exception {
        per.add(Permission.addRemoveEditTeamOwner);
        tMYossi.becomeTeamManager(team, per);
        TeamOwner tOMoshe = tMYossi.getTeamManager().subscribeTeamOwner(moshe, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(1, team.getTeamOwners().size());
    }
    //adi
    @Test
    public void removeTeamOwnerTest() throws Exception {
        per.add(Permission.addRemoveEditTeamOwner);
        tMYossi.becomeTeamManager(team, per);
        TeamOwner tOMoshe = tMYossi.getTeamManager().subscribeTeamOwner(moshe, ms, team);
        TeamOwner tODavid = tOMoshe.subscribeTeamOwner(david, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(2, team.getTeamOwners().size());
        tMYossi.getTeamManager().removeTeamOwner(tOMoshe, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(0, team.getTeamOwners().size());
    }
    //adi
    @Test
    public void addRemoveEditCoachTest() throws Exception {
        per.add(Permission.addRemoveEditCoach);
        tMYossi.becomeTeamManager(team, per);
        TeamRole teamRoleMoshe = new TeamRole(moshe);
        TeamRole teamRoleDavid = new TeamRole(david);
        Assert.assertEquals(3, ms.getUsers().size());
        Coach cDavid = new Coach(team, "mainCoach", teamRoleMoshe);
        team.addCoach(cDavid);
        tMYossi.getTeamManager().removeAndReplaceCoach(cDavid, teamRoleDavid, "main", team);
        Assert.assertEquals(teamRoleDavid, team.getCoach().getTeamRole());
        Assert.assertEquals(team, teamRoleDavid.getCoach().getCoachTeam());
        tMYossi.getTeamManager().editCoachRole(teamRoleDavid.getCoach(), "trainer");
        Assert.assertEquals("trainer", teamRoleDavid.getCoach().getRoleAtTeam());
    }
    //adi
    @Test(expected = Exception.class)
    public void addRemoveEditPlayerTest() throws Exception {
        per.add(Permission.addRemoveEditPlayer);
        tMYossi.becomeTeamManager(team, per);
        Date d = new Date();
        TeamRole teamRoleDavid = new TeamRole(david);
        teamRoleDavid.becomePlayer();
        tMYossi.getTeamManager().addPlayer(teamRoleDavid, "defense", team);
        Assert.assertTrue(team.getPlayers().contains(teamRoleDavid.getPlayer()));
        Assert.assertEquals(team, teamRoleDavid.getPlayer().getTeam());
        tMYossi.getTeamManager().editPlayerRole(teamRoleDavid.getPlayer(), "defense");
        Assert.assertEquals("defense", teamRoleDavid.getPlayer().getRoleAtField());
        //doesnt remove player because less than 11, returns exception
        tMYossi.getTeamManager().removePlayer(teamRoleDavid.getPlayer(), team);
    }
    //adi
    @Test
    public void addRemoveEditFieldTest() throws Exception {
        per.add(Permission.addRemoveEditField);
        tMYossi.becomeTeamManager(team, per);
        Field field = new Field("Beer Sheva Domain.Field");
        team.setField(field);
        Field field2 = new Field("Beer Sheeeeeeeva Domain.Field");
        tMYossi.getTeamManager().removeAndReplaceField(field, field2, team);
        Assert.assertTrue(field2.getTeams().contains(team));
        Assert.assertEquals(field2, team.getField());
        Assert.assertEquals("Beer Sheeeeeeeva Domain.Field", field2.getNameOfField());
        tMYossi.getTeamManager().editFieldName(field, "Bash Domain.Field");
        Assert.assertEquals("Bash Domain.Field", field.getNameOfField());
    }

}