package DataAccess.DbAdapter;

import Domain.Users.TeamRole;

import java.util.List;

public class TeamRoleAdapter implements DbObject<TeamRole> {
    @Override
    public TeamRole ToObj(List<String> fields) throws Exception {
        return null;
    }

    @Override
    public List<String> ToDB(TeamRole obj) {
        return null;
    }
}
