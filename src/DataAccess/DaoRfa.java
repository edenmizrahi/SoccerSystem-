package DataAccess;

import DB.Tables.tables.records.RfasRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Select;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static DB.Tables.Tables.RFAS;
import static DB.Tables.Tables.SEASONS;

public class DaoRfa implements Dao<String> {
    @Override
    public List<String> get(List<String> keys) {
        DBHandler.conectToDB();
        String rfaKey= keys.get(0);

        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);
        Result<?> result= create.select()
                .from(RFAS)
                .where(RFAS.USERNAME.eq(rfaKey)).fetch();
        List<String> ans=new LinkedList<>();

        RfasRecord rfasRecord=create.selectFrom(RFAS)
                .where(RFAS.USERNAME.eq(rfaKey)).fetchOne();
        List<String> titles1 = create.select().from(RFAS).fetch().getValues(RFAS.USERNAME);


        //good !!
        List<String> mm = create.select().from(RFAS).where(RFAS.USERNAME.eq(rfaKey)).fetch().getValues(RFAS.USERNAME); //good!!
        //ans=result.getValues()

        return null;
    }

    @Override
    public List<List<String>> getAll(String collName, String filter) {
        //upper case?? the field
        if (collName == null){

        }

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
