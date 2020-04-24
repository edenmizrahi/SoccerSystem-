package AcceptRejectTests;


import Domain.MainSystem;
import Domain.Users.Rfa;
import Domain.Users.SystemManager;
import Service.RfaController;
import Service.SystemManagerController;
import Service.SystemOperationsController;
import org.junit.Test;

/**Adding a new  referee to system . UC: 30. **/


public class AddNewReferee {

    RfaController rfaController=new RfaController();
    SystemOperationsController operationsController=new SystemOperationsController();


    @Test
    public void accept() throws Exception{
        /*****system init*****/
        SystemOperationsController.initSystemObjectsAvital();
        MainSystem ma= MainSystem.getInstance();

        /*****get RFA *****/
        Rfa rfa=operationsController.getAllRFA().get(0);
        SystemManager sm=operationsController.showAllSystemManagers().get(0);

        /** referee added to system **/





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
