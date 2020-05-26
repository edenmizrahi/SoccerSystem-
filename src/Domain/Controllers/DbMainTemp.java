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
        k.add("2020");
        k.add("hal");
        k.add("hapoel TLV");


        //values.add("gini");
        values.add("ginnnnnnnnnnnn");
        values.add("gini999bini");
        values.add("097827837");
        values.add("gini@gmail");
        values.add("03-06-2020");
        values.add("1");

        //newRow.add("adi");
        newRow.add("2020");
        newRow.add("hal");
        newRow.add("hapoel TLV");
        newRow.add("gini@gmail");
        newRow.add("03-06-2020");
        newRow.add(null);
//        newRow.add("hapoel TLV");
//        newRow.add("macabi TLV");
//        newRow.add("Offense");
//        newRow.add("14");

        DaoLeagueSeasonTeams daoLeagueSeasonTeams=new DaoLeagueSeasonTeams();
        daoLeagueSeasonTeams.get(k);
        List<List<String>> ans= daoLeagueSeasonTeams.getAll("league_name","hal");
        ans= daoLeagueSeasonTeams.getAll(null,null);
//        daoLeagueSeasonReferees.update(newRow,newRow);
        daoLeagueSeasonTeams.save(newRow);
        daoLeagueSeasonTeams.delete(newRow);

//        daoFans.get(k);
//        daoFans.getAll("FULLNAME","adi avinun");
//        daoFans.getAll(null,null);
//        //daoFans.save(newRow);
//        daoFans.update(k,values);
//        daoFans.delete(k);


    }
}
