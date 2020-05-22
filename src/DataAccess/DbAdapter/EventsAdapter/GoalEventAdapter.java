package DataAccess.DbAdapter.EventsAdapter;

import DataAccess.*;
import DataAccess.DbAdapter.DbObject;
import Domain.Events.Goal;

import java.util.LinkedList;
import java.util.List;

public class GoalEventAdapter implements DbObject<Goal> {

    DaoReferee daoReferee = new DaoReferee();
    DaoFans daoFans = new DaoFans();
    DaoTeamRole daoTeamRole = new DaoTeamRole();
    DaoPlayer daoPlayer = new DaoPlayer();
    DaoMatch daoMatch = new DaoMatch();

    @Override
    public Goal ToObj(List<String> fields) throws Exception {
        // Goal(Referee referee, Match match, Player p)
        // Match(int homeScore, int guestScore, Team awayTeam, Team homeTeam, Field field, HashSet<Event> events, HashSet<Referee> referees
        //        , Referee mainReferee, String date)
        // Player(TeamRole teamRole, Team playerTeam, String roleAtField)
        // TeamRole(Fan fan)
        // Fan(String name, String phoneNumber, String email, String userName, String password, Date date)

        return null;
    }

    @Override
    public List<String> ToDB(Goal obj) {

        LinkedList<String> res=new LinkedList<>();

        res.add(Integer.toString(obj.getId()));
        res.add(obj.getPlayer().getTeamRole().getUserName());

        return res;

    }
}
