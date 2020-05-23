package DataAccess;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static DB.Tables.Tables.FANS;
import static org.jooq.impl.DSL.name;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public final class DBHandler  {
    private  static String username="root";
    private static String password ="root";
    private static String myDriver="org.mariadb.jdbc.Driver";
    private static String myUrl="jdbc:mysql://132.72.65.77:3306/footballsystem_db";
    private static Connection connection=null;


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

    public boolean dslCheck(){
        DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
        Result<?> result= create.select().from(DSL.table(name("fans")))
                .where(DSL.field(name("userName")).eq("chen")).fetch();
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

    public static void conectToDB(){
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

    public static DSLContext getDSLConnect(){
        DSLContext create = DSL.using(connection, SQLDialect.MARIADB);
        return create;
    }

    public static void main( String[] args ) throws SQLException {
        DBHandler db=new DBHandler();
        conectToDB();
        System.out.println(db.dslCheck());
        System.out.println(db.dslCheckConfig());
    }

    }
