package Service;

import Domain.Controllers.RefereeController;
import Domain.Users.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.text.ParseException;
import java.util.HashSet;
import java.util.LinkedList;

@Path("/RefereeApplication")
public class RefereeApplication {
    RefereeController refereeController = new RefereeController();


    //get list of players name
    //TODO???
    public LinkedList<String> getPlayersFromTeamInList(HashSet<Player> players){
        return refereeController.getPlayersFromTeamInList(players);
    }

    /**
     *
     * @param username
     * @param match
     * @return LinkedList<String> of players at specific match
     * @throws ParseException
     */
    @Path("/displayPlayersAtMatch/{username}/{match}")
    @GET
    @Produces("text/plain")
    public String displayPlayersAtMatch(@PathParam("username")String username, @PathParam("match")String match) {
        return refereeController.displayPlayersAtMatch(username, match);
    }


    /**
     *
     * @param username
     * @return match that the referee judging that take place right now - probably just one
     */
    @Path("/displayAllMatches/{username}")
    @GET
    @Produces("text/plain")
    public String displayAllMatches(@PathParam("username")String username){
        return refereeController.displayAllMatches(username);
    }

    /**
     * This function return all the matches that the referee is a main referee, and already over
     * @param username
     * @return
     */
    @Path("/getAllMatches/{username}")
    @GET
    @Produces("text/plain")
    public String getAllMatches(@PathParam("username")String username) {
        return refereeController.getAllMatches(username);
    }

    @Path("/createReportOfMatch/{match}/{username}")
    @GET
    @Produces("text/plain")
    public String createReportOfMatch(@PathParam("match")String match, @PathParam("username")String username) {
        return refereeController.createReportOfMatch(match, username);
    }

    @Path("/createGoalEvent/{username}/{match}/{player}")
    @GET
    @Produces("text/plain")
    public String createGoalEvent(@PathParam("username")String username,@PathParam("match") String match,
                                  @PathParam("player")String player) {
        return refereeController.createGoalEvent(username, match, player);
    }

    @Path("/createInjuryEvent/{username}/{match}/{player}")
    @GET
    @Produces("text/plain")
    public String createInjuryEvent(@PathParam("username")String username,@PathParam("match") String match,
                                    @PathParam("player")String player) {
        return refereeController.createInjuryEvent(username, match, player);
    }

    @Path("/createOffenseEvent/{username}/{match}/{player}")
    @GET
    @Produces("text/plain")
    public String createOffenseEvent(@PathParam("username")String username,@PathParam("match") String match,
                                     @PathParam("player")String player) {
        return refereeController.createOffenseEvent(username, match, player);
    }

    @Path("/createOffSideEvent/{username}/{match}/{player}")
    @GET
    @Produces("text/plain")
    public String createOffSideEvent(@PathParam("username")String username,@PathParam("match") String match,
                                     @PathParam("player")String player) {
        return refereeController.createOffSideEvent(username, match, player);
    }

    @Path("/createRedCardEvent/{username}/{match}/{player}")
    @GET
    @Produces("text/plain")
    public String createRedCardEvent(@PathParam("username")String username,@PathParam("match") String match,
                                     @PathParam("player")String player) {
        return refereeController.createRedCardEvent(username, match, player);
    }

    @Path("/createYellowCardEvent/{username}/{match}/{player}")
    @GET
    @Produces("text/plain")
    public String createYellowCardEvent(@PathParam("username")String username,@PathParam("match") String match,
                                        @PathParam("player")String player) {
        return refereeController.createYellowCardEvent(username, match, player);

    }

    @Path("/createReplaceEvent/{username}/{match}/{firstPlayer}/{secondPlayer}")
    @GET
    @Produces("text/plain")
    public String createReplaceEvent(@PathParam("username")String username, @PathParam("match") String match,
                                     @PathParam("firstPlayer")String firstPlayer, @PathParam("secondPlayer") String secondPlayer){
        return refereeController.createReplaceEvent(username, match, firstPlayer, secondPlayer);
    }

    @Path("/createExtraTimeEvent/{username}/{match}/{time}")
    @GET
    @Produces("text/plain")
    public String createExtraTimeEvent(@PathParam("username")String username,@PathParam("match") String match,
                                     @PathParam("time")String time) {
        return refereeController.createExtraTimeEvent(username, match, time);
    }



}
