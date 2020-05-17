package MariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler  {
    String username="root";
    String password ="root";
    String myDriver="org.mariadb.jdbc.Driver";
    String myUrl="jdbc:mysql://132.72.65.105:3306/footballSystemdb";
    Connection connection=null;
    public DBHandler(){
        try{
            Class.forName(myDriver);
            connection= DriverManager.getConnection(myUrl,username,password);
            System.out.println("DB connected");

        }
        catch (SQLException e) {
            System.out.println("Connection in now null");
        }catch (ClassNotFoundException e){
            System.out.println("error connecting to server");
        }
    }

}
