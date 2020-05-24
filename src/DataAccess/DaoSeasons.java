package DataAccess;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.util.List;

import static DB.Tables.Tables.FANS;
import static DB.Tables.Tables.SEASONS;

public class DaoSeasons implements Dao<String>{
    private DBHandler dbHandler=new DBHandler();

    @Override
    public List<String> get(List<String> keys) {
//        DBHandler.conectToDB();
//        String seasonYearKey= keys.get(0);
//        int seasonYear=Integer.parseInt(seasonYearKey);
//        DSLContext create = DBHandler.getDSLConnect();    //DSL.using(connection, SQLDialect.MARIADB);
//        Result<?> result= create.select().
//                from(SEASONS).where(SEASONS.YEAR.eq(seasonYear)).fetch();
//

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
    public void update(List<String> keys, List<String> string) {


    }

    @Override
    public void delete(List<String> strings) {

    }


}
