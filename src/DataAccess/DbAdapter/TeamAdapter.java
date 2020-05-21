package DataAccess.DbAdapter;

import Domain.LeagueManagment.Team;

import java.util.List;

public class TeamAdapter implements  DbObject<Team> {
    @Override
    public Team ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(Team obj) {
        return null;
    }
}
