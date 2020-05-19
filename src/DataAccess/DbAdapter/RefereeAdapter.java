package DataAccess.DbAdapter;

import Domain.Events.Event;
import Domain.Users.Referee;

import java.util.List;

public class RefereeAdapter implements DbObject<Referee>{
    @Override
    public Referee ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(Referee obj) {
        return null;
    }
}
