package SQLite;

import  java.sql.*;

public class SQLite {
    public static  void main (String [] args) throws ClassNotFoundException, SQLException {
        Connection con =null;
        try{
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite.football.db");
            System.out.println("sqlLite DB connected");

        }
        catch (Exception e){
            System.out.println("failed");
        }
    }


}
