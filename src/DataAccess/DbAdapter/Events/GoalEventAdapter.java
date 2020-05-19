package DataAccess.DbAdapter.Events;

import DataAccess.DaoReferee;
import DataAccess.DbAdapter.DbObject;
import Domain.Events.Goal;

import javax.jnlp.IntegrationService;
import java.util.LinkedList;
import java.util.List;

public class GoalEventAdapter implements DbObject<Goal> {

    @Override
    public Goal ToObj(List<String> fields) throws Exception {
        // Goal(Referee referee, Match match, Player p)
        // Match(int homeScore, int guestScore, Team awayTeam, Team homeTeam, Field field, HashSet<Event> events, HashSet<Referee> referees
        //        , Referee mainReferee, String date)
        // Player(TeamRole teamRole, Team playerTeam, String roleAtField)
        // TeamRole(Fan fan)
        // Fan(String name, String phoneNumber, String email, String userName, String password, Date date)
        DaoReferee daoReferee;
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
