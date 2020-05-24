package Domain.Controllers;

import DataAccess.DBHandler;
import DataAccess.DaoFans;
import DataAccess.DaoRfa;
import Domain.MainSystem;

import java.util.LinkedList;
import java.util.List;

public class DbMainTemp {



    public static void main(String[] args) throws Exception {

//        SystemOperationsController systemOperationsController = new SystemOperationsController();
//        systemOperationsController.initSystemObjectsAvitalForUI();
//        MainSystem ma= MainSystem.getInstance();



        DBHandler.conectToDB();
        DBHandler.conectToDB();
        //DaoRfa daoRfa=new DaoRfa();
        DaoFans daoFans=new DaoFans();
        List <String> q=new LinkedList<>();
        q.add("amir");
        //List <String> ans= daoFans.get(q);
        //daoFans.getAll("FULLNAME","adi avinun");
        daoFans.getAll("USERNAME","adi2");



        //launch(args);
    }
}
