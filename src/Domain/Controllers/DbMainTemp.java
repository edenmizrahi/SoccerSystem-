package Domain.Controllers;

import DataAccess.DBHandler;
import DataAccess.DaoCoaches;
import DataAccess.DaoFans;
import DataAccess.DaoRfa;
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
        //q.add("avital");
        q.add("hapoel bash");
        q.add("firstCoach");


        //List <String> ans= daoFans.get(q);
        //daoFans.getAll("FULLNAME","adi avinun");
        daoFans.getAll("USERNAME","adi2");
        DaoCoaches daoCoaches=new DaoCoaches();
        //daoCoaches.getAll("roleAtTeam","firstCoach");
        //daoCoaches.getAll(null,null);
        //daoCoaches.get(q);
        //daoCoaches.delete(q);
//        try {
//            daoCoaches.save(q);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        daoCoaches.delete(q);
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
