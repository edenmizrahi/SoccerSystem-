package AcceptRejectTests;

import Domain.BudgetControl.BudgetControl;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Season;
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

import java.sql.Ref;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;



public class DeleteUserForever {
    SystemManagerController managerController=new SystemManagerController();
    SystemOperationsController operationsController=new SystemOperationsController();

    @Test
    public void accept() throws Exception {
        /*****system init*****/
        SystemOperationsController.initSystemObjectsEden();
        MainSystem system= MainSystem.getInstance();


        /**delete RFA*/
        List<Rfa> rfas=operationsController.getAllRFA();
        SystemManager sm=operationsController.showAllSystemManagers().get(0);
        managerController.deleteUserForever(rfas.get(0),sm);
        Assert.assertTrue(operationsController.getUserByUserName(rfas.get(0).getUserName())==null);

        /**delete system manager*/
        List<SystemManager> systemManagers=operationsController.getAllSystemManager();
        SystemManager currentSM=systemManagers.get(0)==sm?systemManagers.get(1):systemManagers.get(0);
        managerController.deleteUserForever(currentSM,sm);
        Assert.assertTrue(operationsController.getUserByUserName(currentSM.getUserName())==null);

        /***delete referee*/
        List<Referee> referees=operationsController.showAllReferee();
        managerController.deleteUserForever(referees.get(0),sm);
        Assert.assertTrue(operationsController.getUserByUserName(referees.get(0).getUserName())==null);

        /***delete Team Owner with subscriptions,
         *post: delete user and remove his subscriptions */
        List<TeamOwner> teamOwners=operationsController.showAllTeamOwner();
        /**DELETE avi(subscribe avi and moshe to be owners of macabi, moshe still team manager (ilan subscribr him))**/
        TeamOwner teamOwnerToDelete=null;
        for(TeamOwner to: teamOwners){
            if(to.getTeamRole().getUserName().equals("Avi")){
                teamOwnerToDelete=to;
            }
        }
        managerController.deleteUserForever(teamOwnerToDelete.getTeamRole(),sm);

        HashSet<Team> teams=operationsController.showAllTeams();
        Iterator<Team > iter =teams.iterator();
        Team first=iter.next();
        Team second=iter.next();
        Team t1=first.getName().equals("macabi")?first:second;
        Team t2=first.getName().equals("macabi")?second:first;

        /***check if avi deleted from system**/
        Assert.assertTrue(!system.getUsers().contains(teamOwnerToDelete.getTeamRole()));
        Assert.assertTrue(!system.getUserNames().contains(teamOwnerToDelete.getTeamRole().getUserName()));

        /***check t1 subscriptions **/
        /**avi and moshe deleted from ownership*/
        /**moshe is the manager*/
        /*****************************/



    }

    @Test
    public void reject() throws Exception {
        /*****system init*****/
        SystemOperationsController.initSystemObjectsEden();
        MainSystem system= MainSystem.getInstance();
        List<Rfa> rfas=operationsController.getAllRFA();
        SystemManager sm=operationsController.showAllSystemManagers().get(0);
        managerController.deleteUserForever(rfas.get(0),sm);
        try {
            managerController.deleteUserForever(rfas.get(0), sm);
            Assert.fail();
        }
        catch (Exception e){
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("There is only one RFA left, you cannot delete it ",e.getMessage());
        }

        /**system Manager delete yourself***/
        List<SystemManager> systemManagers=operationsController.getAllSystemManager();
        SystemManager currentSM=systemManagers.get(0)==sm?systemManagers.get(0):systemManagers.get(1);
        try {
            managerController.deleteUserForever(currentSM,sm);
            Assert.fail();
        }
        catch (Exception e){
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("You Cannot Delete Yourself!!!",e.getMessage());
        }

        /**system Manager delete the last one***/
        systemManagers=operationsController.getAllSystemManager();
        currentSM=systemManagers.get(0)==sm?systemManagers.get(1):systemManagers.get(0);
        managerController.deleteUserForever(currentSM,sm);

        try {
            currentSM=systemManagers.get(0);
            managerController.deleteUserForever(currentSM,sm);


            Assert.fail();
        }
        catch (Exception e){
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("You Cannot Delete Yourself!!!",e.getMessage());
        }


        /**delete Referee with future games */
        List<Referee> referees=operationsController.showAllReferee();
        Referee rf=referees.get(0);
        rf.getMatches().add(new Match(MainSystem.birthDateFormat.parse("02-11-2030")));
        try {
            managerController.deleteUserForever(referees.get(0),sm);
            Assert.fail();
        }
        catch (Exception e){
            Assert.assertEquals(Exception.class, e.getClass());
            Assert.assertEquals("You cannot remove referee , he has matches in this season",e.getMessage());
        }



        //"you have to subscribe another coach to " coach
        //"You Cannot Delete player From " player
        //" is founder of:" Team manager


    }

}
