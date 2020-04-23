package AcceptRejectTests;

import Domain.LeagueManagment.League;
import Domain.MainSystem;
import Domain.TeamSubscription;
import Domain.Users.Rfa;
import Domain.Users.SystemManager;
import Service.SystemManagerController;
import Service.SystemOperationsController;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**Adding a new  league to the system. UC: 28. **/

public class AddLeagueToSystem {

    SystemManagerController managerController=new SystemManagerController();
    SystemOperationsController operationsController=new SystemOperationsController();

    @Test
    public void accept() throws Exception {
        /*****system init*****/
        SystemOperationsController.initSystemObjectsAvital();// main system become
        MainSystem ma= MainSystem.getInstance();

        /*****get RFA *****/
        Rfa rfa=operationsController.getAllRFA().get(0);
        SystemManager sm=operationsController.showAllSystemManagers().get(0);
        String teamName="macabi Modiin";
        /*****RFA added league *****/
        try {
            //managerController. rfa controller - hoe to get it
            rfa.createNewLeague(teamName,ma);
        }
        catch (Exception e){
            fail();
        }
        Boolean foundInSystem=false;
        League myLeague=null;
        List<League> AllLeaguesInSystem=operationsController.showLeagus();
        for(League l: AllLeaguesInSystem){
            if(l.getName().equals(teamName)){
                myLeague=l;
                foundInSystem=true;
            }
        }
        /** created new league **/
        try{
            Assert.assertTrue(myLeague!=null);
        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }
        /**league Added to the list of leagues of system **/
        try{
            Assert.assertTrue(foundInSystem);
        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }
    }

    @Test
    public void reject() throws Exception {
        /**added league with name that already exist in system **/
        /*****system init*****/
        SystemOperationsController.initSystemObjectsAvital();// main system become
        MainSystem ma= MainSystem.getInstance();

        /*****get RFA *****/
        Rfa rfa=operationsController.getAllRFA().get(0);
        SystemManager sm=operationsController.showAllSystemManagers().get(0);
        String teamName="macabi Modiin";
        /*****RFA added league *****/
        try {
            rfa.createNewLeague(teamName,ma);
        }
        catch (Exception e){
            fail();
        }
        int counter=0;
        List<League> AllLeaguesInSystem=operationsController.showLeagus();
        for(League l: AllLeaguesInSystem){
            if(l.getName().equals(teamName)){
                counter++;
            }
        }
        /*****league not create*****/
        try{
            Assert.assertTrue(counter==1);
        } catch (Exception e) {
            Assert.fail("test fail");
            e.printStackTrace();
        }

    }
}
