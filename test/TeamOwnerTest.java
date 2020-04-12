import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class TeamOwnerTest {

    MainSystem ms = new MainSystem();
    Subscription yossi = new Subscription(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
    Team team = new Team();
    TeamOwner tOYossi = new TeamOwner(yossi, ms, team);
    Subscription moshe = new Subscription(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123" );
    Subscription david = new Subscription(ms, "David Hamelech", "0541235678","david@gmail.com", "DavidHamelech", "David123" );
    @Test
    public void subscribeTeamOwnerTest() {
        TeamOwner tOMoshe = tOYossi.subscribeTeamOwner(moshe, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(2, team.getTeamOwners().size());
    }
    @Test
    public void removeTeamOwnerTest() {
        TeamOwner tOMoshe = tOYossi.subscribeTeamOwner(moshe, ms, team);
        TeamOwner tODavid = tOMoshe.subscribeTeamOwner(david, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(3, team.getTeamOwners().size());
        tOYossi.removeTeamOwner(tOMoshe, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(1, team.getTeamOwners().size());
    }
    @Test
    public void subscribeTeamManagerTest(){
        HashSet<Permission> per = new HashSet<>();
        per.add(Permission.DeleteUser);
        TeamManager tMDavid = tOYossi.subscribeTeamManager(david, ms, team, per);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(tMDavid, team.getTeamManager());
    }
    @Test
    public void removeTeamManagerTest() {
        HashSet<Permission> per = new HashSet<>();
        per.add(Permission.DeleteUser);
        TeamManager tMDavid = tOYossi.subscribeTeamManager(david, ms, team, per);
        tOYossi.removeTeamManager(tMDavid, ms, team);
        Assert.assertEquals(3, ms.getUsers().size());
        Assert.assertEquals(null, team.getTeamManager());
    }
}
