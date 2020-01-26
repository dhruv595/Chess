package datalayer;
import beans.Country;
import java.sql.*;
import java.util.ArrayList;
public class DALCommonFunctions {
    private static Connection con=null;
    public DALCommonFunctions()
    {
        con=DBOperations.getConnection();
    }
    public ArrayList<beans.Country> getCountries()
    {
        ArrayList<beans.Country> AC=new ArrayList<beans.Country>();
        try
        {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("Select * from Countries");
            beans.Country country;
            while(rs.next())
            {
                country=new Country();
                country.setCountryId(rs.getInt("countryId"));
                country.setCountryName(rs.getString("countryName"));
                AC.add(country);
            }
        }
        catch(Exception ex)
        {
            
        }
        return AC;
    }
    
    public  static void addCountry(String country)
    {
        try
        {
            PreparedStatement p= con.prepareStatement("select * from countries where countryName=?");
            p.setString(1,country);
            ResultSet rs=p.executeQuery();
            if(rs.next()!=true)
            {
                PreparedStatement ps = con.prepareStatement("insert into countries values(?)");
                ps.setString(1,country);
                ps.execute();
            }
        }
        catch(Exception ex)
        {
            
        }
    }
}