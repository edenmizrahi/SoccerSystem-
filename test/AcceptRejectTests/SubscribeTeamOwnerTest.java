package AcceptRejectTests;
import Domain.LeagueManagment.Team;
import Domain.Users.*;
import Service.SystemManagerController;
import Service.SystemOperationsController;
import Service.TeamManagementController;
import org.junit.Assert;
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
    public void accept1() throws Exception {
        SystemOperationsController.initSystemObjects();
        // just a fan
        Fan tamar = (Fan)systemOperationsController.getUserByUserName("Tamar");

        TeamRole ilan = (TeamRole)systemOperationsController.getUserByUserName("Ilan");
        Team t1Macabi = teamManagementController.getAllMyTeams(ilan.getTeamOwner()).get(0);
        TeamRole newTeamOwner = teamManagementController.subscribeTeamOwner(ilan, tamar, t1Macabi);
        assertTrue(t1Macabi.getTeamOwners().contains(newTeamOwner.getTeamOwner()));
        assertTrue(newTeamOwner.getTeamOwner().getTeams().contains(t1Macabi));
    }

    // same except someone who is already TeamRole
    @Test
    public void alternative1() throws Exception {
        SystemOperationsController.initSystemObjects();
        LinkedList<Fan> possibleSubTeamOwners = teamManagementController.getAllPossibleSubscribeTeamOwner();
        TeamRole ben = (TeamRole)systemOperationsController.getUserByUserName("Ben");

        TeamRole ilan = (TeamRole)systemOperationsController.getUserByUserName("Ilan");
        Team t1Macabi = teamManagementController.getAllMyTeams(ilan.getTeamOwner()).get(0);
        TeamRole newTeamOwner = teamManagementController.subscribeTeamOwner(ilan, ben, t1Macabi);
        assertTrue(t1Macabi.getTeamOwners().contains(newTeamOwner.getTeamOwner()));
        assertTrue(newTeamOwner.getTeamOwner().getTeams().contains(t1Macabi));
    }
    /***************SAME TESTS EXCEPT THIS TIME WITH TEAM MANAGER****************/

    // subscribe a fan to team owner
    @Test
    public void accept2() throws Exception {
        SystemOperationsController.initSystemObjects();
        // just a fan
        Fan tamar = (Fan)systemOperationsController.getUserByUserName("Tamar");

        TeamRole moshe = (TeamRole)systemOperationsController.getUserByUserName("Moshe");
        Team t1Macabi = moshe.getTeamManager().getTeam();
        TeamRole newTeamOwner = teamManagementController.subscribeTeamOwner(moshe, tamar, t1Macabi);
        assertTrue(t1Macabi.getTeamOwners().contains(newTeamOwner.getTeamOwner()));
        assertTrue(newTeamOwner.getTeamOwner().getTeams().contains(t1Macabi));
    }

    // same except someone who is already TeamRole
    @Test
    public void alternative2() throws Exception {
        SystemOperationsController.initSystemObjects();
        LinkedList<Fan> possibleSubTeamOwners = teamManagementController.getAllPossibleSubscribeTeamOwner();
        TeamRole ben = (TeamRole)systemOperationsController.getUserByUserName("Ben");

        TeamRole moshe = (TeamRole)systemOperationsController.getUserByUserName("Moshe");
        Team t1Macabi = moshe.getTeamManager().getTeam();
        TeamRole newTeamManager = teamManagementController.subscribeTeamOwner(moshe, ben, t1Macabi);
        assertTrue(t1Macabi.getTeamOwners().contains(newTeamManager.getTeamOwner()));
        assertTrue(newTeamManager.getTeamOwner().getTeams().contains(t1Macabi));
    }

    //team manager without permission
    @Test
    public void alternative3() throws Exception {
        SystemOperationsController.initSystemObjects();
        LinkedList<Fan> possibleSubTeamOwners = teamManagementController.getAllPossibleSubscribeTeamOwner();
        TeamRole ben = (TeamRole)systemOperationsController.getUserByUserName("Ben");

        TeamRole david = (TeamRole)systemOperationsController.getUserByUserName("David");
        Team t1Macabi = david.getTeamManager().getTeam();
        try {
            TeamRole newTeamManager = teamManagementController.subscribeTeamOwner(david, ben, t1Macabi);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("This user doesn't have the permission to do this action", e.getMessage());
        }
    }
}