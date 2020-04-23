package AcceptRejectTests;
import Domain.Enums.TeamManagerPermissions;
import Domain.LeagueManagment.Team;
import Domain.Users.Fan;
import Domain.Users.TeamRole;
import Service.SystemOperationsController;
import Service.TeamManagementController;
import org.junit.Test;
import java.security.acl.Permission;
import java.util.HashSet;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SubscribeTeamManagerTest {
    SystemOperationsController systemOperationsController = new SystemOperationsController();
    TeamManagementController teamManagementController = new TeamManagementController();
    // subscribe a fan to team manager
    @Test
    public void accept1() throws Exception {
        SystemOperationsController.initSystemObjectsAdi();
        // just a fan
        Fan tamar = (Fan)systemOperationsController.getUserByUserName("Tamar");
        HashSet<TeamManagerPermissions> per = new HashSet<>();

        TeamRole ilan = (TeamRole)systemOperationsController.getUserByUserName("Ilan");
        Team t1Macabi = teamManagementController.getAllMyTeams(ilan.getTeamOwner()).get(0);
        TeamRole newTeamManager = teamManagementController.subscribeTeamManager(ilan.getTeamOwner(), tamar, t1Macabi, per);
        assertEquals(t1Macabi.getTeamManager(), newTeamManager.getTeamManager());
        assertEquals(newTeamManager.getTeamManager().getTeam(), t1Macabi);
    }

    // same except someone who is already TeamRole
    @Test
    public void alternative1() throws Exception {
        SystemOperationsController.initSystemObjectsAdi();
        TeamRole ben = (TeamRole)systemOperationsController.getUserByUserName("Ben");
        HashSet<TeamManagerPermissions> per = new HashSet<>();

        TeamRole ilan = (TeamRole)systemOperationsController.getUserByUserName("Ilan");
        Team t1Macabi = teamManagementController.getAllMyTeams(ilan.getTeamOwner()).get(0);
        TeamRole newTeamManager = teamManagementController.subscribeTeamManager(ilan.getTeamOwner(), ben, t1Macabi, per);
        assertEquals(t1Macabi.getTeamManager(), newTeamManager.getTeamManager());
        assertEquals(newTeamManager.getTeamManager().getTeam(), t1Macabi);
    }
}
