package AcceptRejectTests;
import Domain.BudgetControl.BudgetControl;
import Domain.LeagueManagment.Team;
import Domain.Main;
import Domain.MainSystem;
import Domain.TeamSubscription;
import Domain.Users.*;
import Service.SystemManagerController;
import Service.SystemOperationsController;
import Stubs.TeamStub;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;


public class CloseTeamForeverTest {
    SystemManagerController managerController=new SystemManagerController();
    SystemOperationsController operationsController=new SystemOperationsController();
    @Test
    public void accept() throws Exception {
        /*****system init*****/
        SystemOperationsController.initSystemObjects();
        MainSystem ma= MainSystem.getInstance();
        HashSet<Team> teams=operationsController.showAllTeams();
        assertTrue(teams.size()==2);
        int size=teams.size();
        SystemManager sm=operationsController.showAllSystemManagers().get(0);
        Team t1=teams.iterator().next();
        HashSet<TeamOwner> teamOwners=t1.getTeamOwners();
        TeamRole teamManager=t1.getTeamManager().getTeamRole();

        for (TeamOwner tO:teamOwners){
            assertTrue(tO.getTeams().contains(t1));
        }
        Coach coach=t1.getCoach();
        HashSet<Player> players=t1.getPlayers();

        try {
            sm.removeTeamFromSystem(t1);
        }
        catch (Exception e){
            fail();
        }
        /***delete from system**/
        assertTrue(operationsController.showAllTeams().size()==size-1);
        /***delete from all owners**/
        for (TeamOwner tO:teamOwners){
            assertTrue(!tO.getTeams().contains(t1));
        }
        /**all subscriptions deleted*/
        for (TeamOwner tO:teamOwners){
            HashSet<TeamSubscription> teamSubscriptions=tO.getMySubscriptions();
            for(TeamSubscription ts: teamSubscriptions){
                assertTrue(ts.team.getName()!=t1.getName());
            }
        }
        /**delete from teamManager*/
        assertTrue(!teamManager.isTeamManager());
        /**check for players*/
        for(Player p: players){
            assertTrue(p.getTeam()==null);
        }
        /**check for coach*/
        assertTrue(coach.getCoachTeam()==null);





    }

    public  void reject(){

    }
}
