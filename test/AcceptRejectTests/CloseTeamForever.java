package AcceptRejectTests;
import Domain.BudgetControl.BudgetControl;
import Domain.LeagueManagment.Team;
import Service.SystemManagerController;
import Service.SystemOperationsController;
import Stubs.TeamStub;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;


public class CloseTeamForever {
    SystemManagerController managerController=new SystemManagerController();
    SystemOperationsController operationsController=new SystemOperationsController();
    @Test
    public void accept() throws Exception {
        SystemOperationsController.initSystemObjects();
        HashSet<Team> teams=operationsController.showAllTeams();
        assertTrue(teams.size()==2);

    }

    public  void reject(){

    }
}
