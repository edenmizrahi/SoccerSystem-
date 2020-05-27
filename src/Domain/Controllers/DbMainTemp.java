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

//        k.add("01-06-2020 20:00:00");
//        k.add("macabi TLV");
//        k.add("hapoel TLV");
//        k.add("shimi");
        k.add("avital");
//        k.add("The match time between macabi TLV to hapoel TLV changed to 2020-06-01");
        //The match time between macabi TLV to hapoel TLV changed to 2020-06-01




//        values.add("player32");
//        values.add("player22");
//        values.add("gini999bini");
//        values.add("097827837");
//        values.add("gini@gmail");
//        values.add("03-06-2020");
        values.add("SchedualeOption2");
        values.add("CalculateOption1");
        values.add("0");

//        values.add("90");
//        values.add("90");
//        values.add("bashField2");
//        values.add("or");
//        values.add("10");

        //newRow.add("adi");
//        newRow.add("03-06-2020 20:00:00");
//        newRow.add("hapoel TLV");
//        newRow.add("macabi bash");
//        newRow.add("9");
//        newRow.add("0");
//        newRow.add("bashField1");
//        newRow.add("adi");
//        newRow.add("10");
//        newRow.add("hapoel TLV");
//        newRow.add("macabi TLV");
        newRow.add("2019");
        newRow.add("SchedualeOption1");
        newRow.add("CalculateOption1");
        newRow.add("1");





//        DaoNotificaionsReferee daoNotificaionsReferee=new DaoNotificaionsReferee();
//        daoNotificaionsReferee.get(k);
//        List<List<String>> ans= daoNotificaionsReferee.getAll(null,null);
//        ans=  daoNotificaionsReferee.getAll("read","0");
//        daoNotificaionsReferee.save(newRow);
//        daoNotificaionsReferee.update(newRow,values);
//        daoNotificaionsReferee.delete(newRow);

        DaoNotificationFan x=new DaoNotificationFan();
        DaoTeamRole y=new DaoTeamRole();
        DaoPlayer z=new DaoPlayer();
        z.save(k);
//        List<String> ll=x.get(k);
//        x.getAll(null,null);
//        x.getAll("username","timor");
//        x.getAll("home_team","macabi TLV");
        y.getAll("player","1");
        x.getAll("read","1");
        x.save(newRow);
        x.update(k,values);
        x.delete(k);


    }
}
