package Service;
import Domain.Controllers.RfaController;

public class RfaApplication {

    RfaController rfaController = new RfaController();


    /**
     *
     * @return linkedList of all subClasses that implement SchedulingPolicy interface
     */
    public String getAllschedulingString() {
        return rfaController.getAllschedulingString();
    }

    /**
     *
     * @return linkedList of all subClasses that implement Calculation interface
     */
    public String getAllCalculationPoliciesString() {
        return rfaController.getAllCalculationPoliciesString();
    }


    /**
     * Define calculate and schedule policies to specific season
     * if season exist, just update the policies, else create new season with those policies
     * @param year
     * @param calc
     * @param sched
     */
    public void DefinePoliciesToSeason(String year, String calc, String sched, String rfaUserName){
        rfaController.DefinePoliciesToSeason(year, calc, sched, rfaUserName);
    }
}
