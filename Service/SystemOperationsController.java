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
     */
    public List<League> showLeagus(){
        return MainSystem.getInstance().getLeagues();
    }

    /**
     * show all TeamOwnerUsers in order to replace one of them.
     * @return all TeamOwners
     */
    public List<TeamOwner> showAllTeamOwner()
    {
        return MainSystem.getInstance().getAllTeamOwners();
    }

    public  List<Referee> showAllReferee(){
        return MainSystem.getInstance().getAllReferees();

    }


    public LinkedList<Season> getAllSeasons(){
        return MainSystem.getInstance().getSeasons();

    }

    public Season getSeasons(){
        return MainSystem.getInstance().getCurrSeason();

    }

}
