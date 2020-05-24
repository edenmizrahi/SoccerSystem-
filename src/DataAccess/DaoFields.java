package DataAccess;

import DB.Tables.tables.records.CoachsRecord;
import DB.Tables.tables.records.FieldsInSystemRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DB.Tables.Tables.COACHS;
import static DB.Tables.Tables.FIELDS_IN_SYSTEM;

/** table name: fields_in_system **/

public class DaoFields implements Dao<String>{
    @Override
    public List<String> get(List<String> keys) throws ParseException {

        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        String Key= keys.get(0);
        List<String> result=new LinkedList<>();
        /** select retrieval row from table  **/
        FieldsInSystemRecord fieldsInSystemRecord=create.selectFrom(FIELDS_IN_SYSTEM)
                .where(FIELDS_IN_SYSTEM.NAME.eq(Key)).fetchOne();
        /** key noy found in table  **/
        if (fieldsInSystemRecord== null || fieldsInSystemRecord.size()==0){
            //return null;
            throw new ParseException("key noy found in table",0);
        }

        for (int i = 0; i <fieldsInSystemRecord.size() ; i++) {
            if(fieldsInSystemRecord.get(i) instanceof String){
                result.add(fieldsInSystemRecord.get(i).toString());
            }
        }
        return result;
    }

    @Override
    public List<List<String>> getAll(String collName, String filter) {

        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        if( collName==null && filter==null){
            /** return all rows in table **/
            //String sql="SELECT * FROM coachs";

            Result<Record> result=create.select().from(FIELDS_IN_SYSTEM).fetch();

            /** iinitialize List<List<String>> **/
            List<List<String>> ans=new ArrayList<>(result.size());
            for(int i=0; i<result.size(); i++){
                List<String> temp = new LinkedList<>();
                ans.add(temp);
            }
            /** insert coll values to ans  **/
            int numOfCols=result.fields().length; //1 !
            for(int i=0;i< numOfCols;i++){
                List <String> currCol = (List<String>)result.getValues(i);
                for (int j = 0; j <result.size() ; j++) {
                    ans.get(j).add(currCol.get(j));

                }
            }
            return ans;

        }
        /** fikter **/
        ResultSet rs=null;
        Result<Record> result=null;
        int numOfCols=0;
        String sql="SELECT * FROM fields_in_system WHERE "+collName+"= '" + filter + "'"; //!!!!!!!!!!!!!!!!!!!!!!!!!
        try {
            rs=DBHandler.getConnection().createStatement().executeQuery(sql);
            result=DBHandler.getDSLConnect().fetch(rs);
            ResultSetMetaData rsmd=rs.getMetaData();
            numOfCols=rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /** iinitialize List<List<String>> **/
        List<List<String>> ans=new ArrayList<>(result.size());
        for(int i=0; i<result.size(); i++){
            List<String> temp = new LinkedList<>();
            ans.add(temp);
        }
        /** insert coll values to ans  **/
        for(int i=0;i< numOfCols;i++){
            List <String> currCol = (List<String>)result.getValues(i);
            for (int j = 0; j <result.size() ; j++) {
                ans.get(j).add(currCol.get(j));

            }
        }
        return ans;
    }

    @Override
    public void save(List<String> strings) throws SQLException {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);
        /** check if key not already exist in DB  **/
        String key= strings.get(0);
        Result<Record> isExist = create.select().from(FIELDS_IN_SYSTEM)
                .where(FIELDS_IN_SYSTEM.NAME.eq(key)).fetch();

        if(isExist.size()== 0){ // key not exist
            /**add new row to DB **/
            try{
                create.insertInto(FIELDS_IN_SYSTEM,
                        FIELDS_IN_SYSTEM.NAME)
                        .values(strings.get(0)).execute();

                System.out.println("new row add"); // dell ! !

            }catch (DataAccessException e){
                e.printStackTrace();
                throw new SQLException("added failed, foreign key not exist.");

            }
        }
        else {
            throw new SQLException("added failed, key already exist.");
        }
    }

    @Override
    public void update(List<String> keys, List<String> string) {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        String key= keys.get(0);
        /** select retrieval row from table  **/
        FieldsInSystemRecord fieldsInSystemRecord=create.selectFrom(FIELDS_IN_SYSTEM)
                .where(FIELDS_IN_SYSTEM.NAME.eq(key)).fetchOne();

        /** check if key exist in DB  **/
        if(fieldsInSystemRecord != null) {

            /**update row in  DB **/
            fieldsInSystemRecord.setName(string.get(0));
            try{
                fieldsInSystemRecord.store();

                /** foreign key not exist  **/
            }catch (DataAccessException e){
                //e.printStackTrace();
                System.out.println("foreign key not exist. need to change it");
            }
        }
    }

    @Override
    public void delete(List<String> strings) {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);
        String key= strings.get(0);

        create.delete(FIELDS_IN_SYSTEM)
                .where(FIELDS_IN_SYSTEM.NAME.eq(key))
                .execute();

    }
}
