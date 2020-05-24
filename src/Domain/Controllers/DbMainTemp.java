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
        List <String> q=new LinkedList<>();
        List <String> values=new LinkedList<>();
        q.add("bashField1");
        values.add("newwwwwwwwwww");
//        q.add("hapoel bash");
//        q.add("firstCoach");

        DaoFields daoFields=new DaoFields();
        daoFields.get(q);
        daoFields.getAll("name","aaa");
        daoFields.update(q,values);
        //daoFans.get(q);
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
