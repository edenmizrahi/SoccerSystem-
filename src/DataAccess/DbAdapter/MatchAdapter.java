package DataAccess.DbAdapter;

import Domain.LeagueManagment.Match;
import Domain.MainSystem;

import java.util.LinkedList;
import java.util.List;

public class MatchAdapter implements DbObject<Match> {
    @Override
    public Match ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(Match obj) {
        LinkedList<String> res=new LinkedList<>();

        res.add(MainSystem.simpleDateFormat.format(obj.getStartDate()));
        res.add(obj.getHomeTeam().getName());
        res.add(obj.getAwayTeam().getName());
        res.add(Integer.toString(obj.getGuestScore()));
        res.add(Integer.toString(obj.getGuestScore()));
        res.add(obj.getField().getNameOfField());
        res.add(obj.getMainReferee().getUserName());
        res.add(Integer.toString(obj.getNumOfMinutes()));

        return res;
    }
}
