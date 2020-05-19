package DataAccess;

import Domain.Users.Fan;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DaoFans implements Dao<String> {
    @Override
    public List<String> get(List<String> keys) {
        return null
    }

    @Override
    public List<String> getAll(String collName, String filter) {
        return null;
    }

    @Override
    public void save(List<String> strings) throws SQLException {

    }

    @Override
    public void update(String s, List<String> string) {

    }

    @Override
    public void delete(List<String> strings) {

    }
}
