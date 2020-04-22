package AcceptRejectTests;
import Domain.LeagueManagment.Team;
import Domain.Users.Fan;
import Domain.Users.SystemManager;
import Domain.Users.TeamOwner;
import Domain.Users.TeamRole;
import Service.SystemManagerController;
import Service.SystemOperationsController;
import Service.TeamManagementController;
import org.junit.Test;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SubscribeTeamOwnerTest {
    SystemOperationsController systemOperationsController = new SystemOperationsController();
    TeamManagementController teamManagementController = new TeamManagementController();
    // subscribe a fan to team owner
    @Test
    public void accept() throws Exception {
        SystemOperationsController.initSystemObjects();
        LinkedList<Fan> possibleSubTeamOwners = teamManagementController.getAllPossibleSubscribeTeamOwner();
        assertTrue(possibleSubTeamOwners.size() == 40);
        // sub tamar - just a fan
        Fan toSub = possibleSubTeamOwners.get(39);

        TeamOwner ilan = systemOperationsController.showAllTeamOwner().get(0);
        Team t1Macabi = teamManagementController.getAllMyTeams(ilan).get(0);
        TeamRole newTeamManager = teamManagementController.subscribeTeamOwner(ilan.getTeamRole(), toSub, t1Macabi);
        assertTrue(t1Macabi.getTeamOwners().contains(newTeamManager.getTeamOwner()));
        assertTrue(newTeamManager.getTeamOwner().getTeams().contains(t1Macabi));
    }

    // same except someone who is already TeamRole
    @Test
    public void alternative() throws Exception {
        SystemOperationsController.initSystemObjects();
        LinkedList<Fan> possibleSubTeamOwners = teamManagementController.getAllPossibleSubscribeTeamOwner();
        assertTrue(possibleSubTeamOwners.size() == 40);
        // sub a player
        Fan toSub = possibleSubTeamOwners.get(5);

        TeamOwner ilan = systemOperationsController.showAllTeamOwner().get(0);
        Team t1Macabi = teamManagementController.getAllMyTeams(ilan).get(0);
        TeamRole newTeamManager = teamManagementController.subscribeTeamOwner(ilan.getTeamRole(), toSub, t1Macabi);
        assertTrue(t1Macabi.getTeamOwners().contains(newTeamManager.getTeamOwner()));
        assertTrue(newTeamManager.getTeamOwner().getTeams().contains(t1Macabi));
    }
}
