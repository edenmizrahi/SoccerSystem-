import org.junit.Assert;
import org.junit.Test;

public class TeamOwnerTest {

    MainSystem ms = new MainSystem();
    Subscription yossi = new Subscription(ms, "Yossi Hamelech", "0549716910","yossi@gmail.com", "YossiHamelech", "Yossi123" );
    Team team = new Team();
    TeamOwner tOYossi = new TeamOwner(yossi, ms, team);
    Subscription moshe = new Subscription(ms, "Moshe Hamelech", "0549715678","moshe@gmail.com", "MosheHamelech", "Moshe123" );

    @Test
    public void subscribeTeamOwnerTest() {
        TeamOwner tOMoshe = tOYossi.subscribeTeamOwner(moshe, ms, team);
        Assert.assertEquals(2, ms.users.size());
        Assert.assertEquals(2, team.getTeamOwners().size());
    }
    @Test
    public void removeTeamOwnerTest() {
        TeamOwner tOMoshe = tOYossi.subscribeTeamOwner(moshe, ms, team);
        tOYossi.removeTeamOwner(tOMoshe, ms, team);
        Assert.assertEquals(2, ms.users.size());
        Assert.assertEquals(1, team.getTeamOwners().size());
    }

}