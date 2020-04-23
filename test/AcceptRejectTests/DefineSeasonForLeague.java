package AcceptRejectTests;

import Domain.MainSystem;
import Domain.Users.Rfa;
import Domain.Users.SystemManager;
import Service.SystemManagerController;
import Service.SystemOperationsController;
import org.junit.Test;


/**  define a season for league by year. UC: 29   **/

public class DefineSeasonForLeague {


    SystemManagerController managerController=new SystemManagerController();
    SystemOperationsController operationsController=new SystemOperationsController();


    @Test
    public void accept() throws Exception{
        /*****system init*****/
        SystemOperationsController.initSystemObjectsAvital();
        MainSystem ma= MainSystem.getInstance();

        /*****get RFA *****/
        Rfa rfa=operationsController.getAllRFA().get(0);
        SystemManager sm=operationsController.showAllSystemManagers().get(0);

        // call to controler
        // bring 3 referees
    }

    @Test
    public void reject() throws Exception{
        /*****system init*****/
        SystemOperationsController.initSystemObjectsAvital();
        MainSystem ma= MainSystem.getInstance();

        /*****get RFA *****/
        Rfa rfa=operationsController.getAllRFA().get(0);
        SystemManager sm=operationsController.showAllSystemManagers().get(0);

    }
}
