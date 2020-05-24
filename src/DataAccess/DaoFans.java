package DataAccess;

import DB.Tables.tables.records.FansRecord;
import DB.Tables.tables.records.RfasRecord;
import Domain.MainSystem;
import org.jooq.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static DB.Tables.Tables.FANS;
import static DB.Tables.Tables.RFAS;

public class DaoFans implements Dao<String> {



    @Override
    public List<String > get(List<String> keys) throws ParseException {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        String fanKey= keys.get(0);
        List<String> selectedRow=new LinkedList<>();
        /** select retrieval row from table  **/
        FansRecord fansRecord =create.selectFrom(FANS)
                .where(FANS.USERNAME.eq(fanKey)).fetchOne();

        if (fansRecord == null){
            return null;
        }
        selectedRow.add(fansRecord.getUsername());
        selectedRow.add(fansRecord.getFullname());
        selectedRow.add(fansRecord.getPasswordHash());
        selectedRow.add(fansRecord.getPhonenumber());
        selectedRow.add(fansRecord.getEmail());
        String dateFormat= convertToDateFormat(fansRecord.getBirthday().toString());
        selectedRow.add(dateFormat);

        return selectedRow;
    }

    public String convertToDateFormat(String st){
        String[] parts = st.split("-");
        String dateFormat= parts[2]+"-"+parts[1]+"-"+parts[0];
        return dateFormat;
    }

    @Override
    public List<List<String>> getAll(String collName, String filter){
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);
        if( collName==null && filter==null){ // return all rows in table

            Result<Record> a=create.select().from(FANS).fetch();
        }

        String s= "FULLNAME";
        create.select().from(FANS)
                .where(FANS.FULLNAME.eq(filter)).fetch();

        List<String> titles1 = create.select().from(FANS).fetch().getValues(FANS.USERNAME); // list of all useanams
        //Row6 row6Values= fansRecord.valuesRow();

        Result<Record> titles2 = create.select().from(FANS)
                .where(FANS.USERNAME.eq(filter)).fetch();
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
