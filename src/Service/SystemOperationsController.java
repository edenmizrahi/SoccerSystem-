package Service;

import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Season;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Users.Player;
import Domain.Users.Referee;
import Domain.Users.TeamOwner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SystemOperationsController {

    public HashSet<Team> showAllTeams(){
        return MainSystem.getInstance().getActiveTeams();
    }

    /**
     * show to use all leagues at system
     * @return
     *  @codeBy Eden
     */
    public List<League> showLeagus(){
        return MainSystem.getInstance().getLeagues();
    }

    /**
     * show all TeamOwnerUsers in order to replace one of them.
     * @return all TeamOwners
     *  @codeBy Eden
     */
    public List<TeamOwner> showAllTeamOwner()
    {
        return MainSystem.getInstance().getAllTeamOwners();
    }

    /**
     * return all referee at system
     * @return
     *  @codeBy Eden
     */
    public  List<Referee> showAllReferee(){
        return MainSystem.getInstance().getAllReferees();

    }

    /**
     * return all seasons
     * @return
     *  @codeBy Eden
     */
    public LinkedList<Season> getAllSeasons(){
        return MainSystem.getInstance().getSeasons();

    }

    /**
     * return current season
     * @return
     *  @codeBy Eden
     */
    public Season getSeason(){
        return MainSystem.getInstance().getCurrSeason();

    }

     public  List<Player> getAllPlayers(){
        return MainSystem.getInstance().getAllPlayer();
     }


}
