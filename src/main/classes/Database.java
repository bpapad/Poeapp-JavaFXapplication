package main.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;


public class Database {

    private Connection conn;
    //private Statement statement;

    public Connection openConnection(){
        if(conn==null){
            String url = "jdbc:mysql://localhost/";
            String dbName = "poeapp";
            String driver = "com.mysql.cj.jdbc.Driver";
            String dbUser = "poeappuser";
            String dbPassword = "poeapp123";

            try{
                Class.forName(driver);
                this.conn = DriverManager.getConnection(url+dbName,dbUser,dbPassword);
                //System.out.println("CONNECTION SUCCESSFUL");
            }catch(ClassNotFoundException | SQLException sqle){
                System.out.println("CONNECTION FAILED");
            }
        }
        return conn;
    }
}

/*  If we want to see an extensive list of Exceptions and Errors to pinpoint the problem the below general 'catch(Exception e)' is used:

    }catch(Exception e){
                e.printStackTrace();
                e.getCause();
                System.out.println("CONNECTION FAILED");
    }

    -------------------------

    Else we can just catch the most common Exceptions thrown which are 'ClassNotFoundException' and 'SQLException' and use the code below:

    }catch(ClassNotFoundException | SQLException sqle){
        System.out.println("CONNECTION FAILED");
    }
*/