package Domain.Controllers;

import DataAccess.*;
import Domain.MainSystem;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DbMainTemp {



    public static void main(String[] args) throws Exception {

        DBHandler.conectToDB();
        DBHandler.conectToDB();
        //DaoRfa daoRfa=new DaoRfa();
        DaoFans daoFans=new DaoFans();
        List <String> k=new LinkedList<>();
        List <String> values=new LinkedList<>();
        List <String> newRow=new LinkedList<>();
        k.add("avital");
        k.add("01-06-2020 20:00:00");
        k.add("macabi TLV");
        k.add("hapoel TLV");
        k.add("hapoel bash");
        values.add("1");
        values.add("1");
        values.add(null);
        values.add(null);
        newRow.add("adi");
        newRow.add("03-06-2020 20:00:00");
        newRow.add("hapoel TLV");
        newRow.add("macabi TLV");
        newRow.add("Offense");
        newRow.add("14");

        DaoFanMatchesFollow daoFanMatchesFollow=new DaoFanMatchesFollow();
        daoFanMatchesFollow.get(k);
        List<List<String>> ans= daoFanMatchesFollow.getAll("match_date","01-06-2020 20:00:00");
        ans= daoFanMatchesFollow.getAll(null,null);
        daoFanMatchesFollow.update(newRow,newRow);
        daoFanMatchesFollow.save(newRow);
        daoFanMatchesFollow.delete(newRow);
        //daoFans.get(k);
        daoFans.getAll("FULLNAME","adi avinun");
        daoFans.getAll(null,null);

        //daoCoaches.getAll("fullName","adi avinun");
        //daoCoaches.getAll(null,null);
        //daoCoaches.get(q);
        //daoCoaches.delete(q);
//        try {
//            daoCoaches.save(q);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
       // daoCoaches.delete(q);
        //daoCoaches.update("dan",q);



        /*
    List <String>list2 = (List<String>) result.getValues("roleAtTeam");

        create.select().from(COACHS)
                .where(COACHS.ROLEATTEAM.eq(filter)).fetch();

    List<String> titles1 = create.select().from(COACHS).fetch().getValues(COACHS.ROLEATTEAM); // list of all useanams

    Result<Record> rowsAfterFilter = create.select().from(COACHS)
            .where(COACHS.ROLEATTEAM.eq(filter)).fetch();

*/

    }
}
