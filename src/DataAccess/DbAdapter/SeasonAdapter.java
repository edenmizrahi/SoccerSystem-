package DataAccess.DbAdapter;

import Domain.LeagueManagment.Season;

import java.util.List;

public class SeasonAdapter implements DbObject<Season> {
    @Override
    public Season ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(Season obj) {
        return null;
    }
}
