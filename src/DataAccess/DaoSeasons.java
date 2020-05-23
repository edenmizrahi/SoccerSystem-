package DataAccess;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.util.List;

import static DB.Tables.Tables.FANS;

public class DaoSeasons implements Dao<String>{
    private DBHandler dbHandler=new DBHandler();

    @Override
    public List<String> get(List<String> keys) {
        String leagueYearKey= keys.get(0);

//        //DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
//        Result<?> result= create.select().
//                from(FANS).where(FANS.USERNAME.eq("eden")).fetch();
////        if(!result.isEmpty()) {
////            return true;
//        }
//        return false;





        return null;
    }

    @Override
    public List<List<String>> getAll(String collName, String filter) {
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
