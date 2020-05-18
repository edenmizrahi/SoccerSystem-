package Service;
import Domain.Controllers.RfaController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.*;

@Path("/RfaApplication")
public class RfaApplication {

    RfaController rfaController = new RfaController();


    /**
     *
     * @return linkedList of all subClasses that implement SchedulingPolicy interface
     */
    @Path("/getAllschedulingString")
    @GET
    @Produces("text/plain")
    public String getAllschedulingString() {
        return rfaController.getAllschedulingString();
    }

    /**
     *
     * @return linkedList of all subClasses that implement Calculation interface
     */
    @Path("/getAllCalculationPoliciesString")
    @GET
    @Produces("text/plain")
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
    @Path("/DefinePoliciesToSeason/{year}/{calc}/{sched}/{rfausername}")
    @GET
    @Produces("text/plain")
    public void DefinePoliciesToSeason(@PathParam("year")String year, @PathParam("calc")String calc,
                                       @PathParam("sched")String sched, @PathParam("rfausername")String rfaUserName){
        rfaController.DefinePoliciesToSeason(year, calc, sched, rfaUserName);
    }
}
