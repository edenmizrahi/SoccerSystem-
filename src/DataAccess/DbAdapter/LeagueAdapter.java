package DataAccess.DbAdapter;

import Domain.LeagueManagment.League;

import java.util.List;

public class LeagueAdapter implements DbObject<League>{
    @Override
    public League ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(League obj) {
        return null;
    }
}
