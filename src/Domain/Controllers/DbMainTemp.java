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
        k.add("gini");
        k.add("01-06-2020 20:00:00");
        k.add("macabi TLV");
        k.add("hapoel TLV");
        k.add("hapoel bash");
        //values.add("gini");
        values.add("ginnnnnnnnnnnn");
        values.add("gini999bini");
        values.add("097827837");
        values.add("gini@gmail");
        values.add("03-06-2020");
        values.add("1");

        newRow.add("gini");
        newRow.add("gini bini");
        newRow.add("gini999bini");
        newRow.add("097827837");
        newRow.add("gini@gmail");
        newRow.add("03-06-2020");
        newRow.add(null);
//        newRow.add("hapoel TLV");
//        newRow.add("macabi TLV");
//        newRow.add("Offense");
//        newRow.add("14");

        DaoFanMatchesFollow daoFanMatchesFollow=new DaoFanMatchesFollow();
//        daoFanMatchesFollow.get(k);
//        List<List<String>> ans= daoFanMatchesFollow.getAll("match_date","01-06-2020 20:00:00");
//        ans= daoFanMatchesFollow.getAll(null,null);
//        daoFanMatchesFollow.update(newRow,newRow);
//        daoFanMatchesFollow.save(newRow);
//        daoFanMatchesFollow.delete(newRow);

//        daoFans.get(k);
//        daoFans.getAll("FULLNAME","adi avinun");
//        daoFans.getAll(null,null);
//        //daoFans.save(newRow);
//        daoFans.update(k,values);
//        daoFans.delete(k);


    }
}
