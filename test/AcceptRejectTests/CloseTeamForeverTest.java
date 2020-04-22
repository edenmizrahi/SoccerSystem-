package AcceptRejectTests;
import Domain.BudgetControl.BudgetControl;
import Domain.LeagueManagment.Team;
import Domain.Users.SystemManager;
import Service.SystemManagerController;
import Service.SystemOperationsController;
import Stubs.TeamStub;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;


public class CloseTeamForeverTest {
    SystemManagerController managerController=new SystemManagerController();
    SystemOperationsController operationsController=new SystemOperationsController();
    @Test
    public void accept() throws Exception {
        /*****system init*****/
        SystemOperationsController.initSystemObjects();
        HashSet<Team> teams=operationsController.showAllTeams();
        assertTrue(teams.size()==2);
        int size=teams.size();
        SystemManager sm=operationsController.showAllSystemManagers().get(0);

        sm.removeTeamFromSystem(teams.iterator().next());


        assertTrue(operationsController.showAllTeams().size()==size-1);





    }

    public  void reject(){

    }
}
