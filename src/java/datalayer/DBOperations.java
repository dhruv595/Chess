package datalayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBOperations 
{
    private static Connection con = null;
    
    public static Connection getConnection()
    {
        if(con==null)
        {
            try
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con=DriverManager.getConnection("jdbc:sqlserver://SHARMA-JI\\DSSQLSERVER:53367;database=ChessDB;userName=sa;password=dhruv");
        
             }
            catch(Exception ex)
            {
            
            }
        
        }
        return con;
        
    }
}
