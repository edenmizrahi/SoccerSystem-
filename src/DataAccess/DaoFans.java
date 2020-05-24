package DataAccess;

import DB.Tables.tables.records.FansRecord;
import DB.Tables.tables.records.RfasRecord;
import Domain.MainSystem;
import org.jooq.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static DB.Tables.Tables.FANS;
import static DB.Tables.Tables.RFAS;

public class DaoFans implements Dao<String> {



    @Override
    public List<String > get(List<String> keys) throws ParseException {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        String fanKey= keys.get(0);
        List<String> result=new LinkedList<>();
        /** select retrieval row from table  **/
        FansRecord fansRecord =create.selectFrom(FANS)
                .where(FANS.USERNAME.eq(fanKey)).fetchOne();
        /** key noy found in table  **/
        if (fansRecord == null || fansRecord.size()==0){
            //return null;
            throw new ParseException("key noy found in table",0);
        }


        for (int i = 0; i <fansRecord.size() ; i++) {
            if(fansRecord.get(i) instanceof String){
                result.add(fansRecord.get(i).toString());
            }
            else if(fansRecord.get(i) instanceof LocalDate){
                String dateFormat= DBHandler.convertToDateFormat(fansRecord.getBirthday().toString());
                result.add(dateFormat);
            }
        }

//        result.add(fansRecord.getUsername());
//        result.add(fansRecord.getFullname());
//        result.add(fansRecord.getPasswordHash());
//        result.add(fansRecord.getPhonenumber());
//        result.add(fansRecord.getEmail());
//       String dateFormat= convertToDateFormat(fansRecord.getBirthday().toString());
//        result.add(dateFormat);

        return result;
    }


    @Override
    public List<List<String>> getAll(String collName, String filter){
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        if( collName==null && filter==null){
            /** return all rows in table **/
            Result<Record> result=create.select().from(FANS).fetch();
            /** iinitialize List<List<String>> **/
            List<List<String>> ans=new ArrayList<>(result.size());
            for(int i=0; i<result.size(); i++){
                List<String> temp = new LinkedList<>();
                ans.add(temp);
            }
            /** insert coll values to ans  **/
            int numOfCols=result.fields().length; //6 !
            for(int i=0;i< numOfCols;i++){
                List <String> currCol = (List<String>)result.getValues(i);
                for (int j = 0; j <result.size() ; j++) {
                    ans.get(j).add(currCol.get(j));
                }
            }
        }

        /** do filter **/
        ResultSet rs=null;
        Result<Record> result=null;
        ResultSetMetaData rsmd=null;
        int numOfCols=0;
        String sql="SELECT * FROM fans WHERE "+collName+"= '" + filter + "'";
        try {
            rs=DBHandler.getConnection().createStatement().executeQuery(sql);
            result=DBHandler.getDSLConnect().fetch(rs);
            rsmd=rs.getMetaData();
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


//                if(currCol.get(j).contains("-")){
//                    if( Pattern.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", currCol.get(j))){
//                        String dateFormat= DBHandler.convertToDateFormat(currCol.get(j));
//                        ans.get(j).add(dateFormat);
//                    }
//                }

                ans.get(j).add(currCol.get(j));
            }
        }

        return ans;
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
