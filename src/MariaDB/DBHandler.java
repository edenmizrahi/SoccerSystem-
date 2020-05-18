package MariaDB;

import DB.Tables.tables.Fans;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static DB.Tables.Tables.FANS;
import static org.jooq.impl.DSL.name;
//import org.jooq.DSLContext;
//import org.jooq.SQLDialect;
//import org.jooq.impl.DSL;

public class DBHandler  {
    String username="root";
    String password ="root";
    String myDriver="org.mariadb.jdbc.Driver";
    String myUrl="jdbc:mysql://132.72.65.77:3306/footballsystem_db";
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

    public  boolean dslCheck(){
        DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
        Result<?> result= create.select().from(DSL.table(name("fans")))
                .where(DSL.field(name("userName")).eq("eden")).fetch();
        if(!result.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean dslCheckConfig(){
        DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
        Result<?> result= create.select().
                from(FANS).where(FANS.USERNAME.eq("eden")).fetch();
        if(!result.isEmpty()) {
            return true;
        }
        return false;
    }




    public static void main( String[] args ) throws SQLException {
        DBHandler db=new DBHandler();
        System.out.println(db.dslCheck());
        System.out.println(db.dslCheckConfig());
    }

    }
