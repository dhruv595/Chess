package datalayer;

import beans.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.xml.ws.Response;

public class DALPlayer 
{
    private Connection con =null;
    
    public DALPlayer()
    {
        con=DBOperations.getConnection();
    }
    public EnumStatus addPlayer(beans.Player player)
    {
        EnumStatus ret= null;
        try
        {
              

            PreparedStatement ps= con.prepareStatement("insert into players values(?,?,?,?,?,?,?,?)");
            ps.setString(1,player.getPlayerId());
            ps.setString(2,player.getPassword());
            ps.setString(3,player.getName());
            ps.setString(4,player.getGender());
            ps.setString(5,player.getCountry());
            ps.setString(6,player.getSecurityQuestion());
            ps.setString(7,player.getAnswer());
            ps.setString(8,player.getProfilePic());
            ps.execute();
            ret=EnumStatus.Success;
        }
        catch(Exception ex)
        {
           ret=EnumStatus.Fail; 
        }
        
        
        
        return ret;
    }
    
    public beans.Player authenticate(String PlayerId,String Password)
    {
        beans.Player player=new Player();
        try
        {
            PreparedStatement ps=con.prepareStatement("Select * from Players where PlayerId=? and Password=?");
            ps.setString(1,PlayerId);
            ps.setString(2,Password);
            
            ResultSet rs=ps.executeQuery();
            boolean ret=rs.next();
            if(ret)
            {
                player.setPlayerNo(rs.getInt("playerNo"));
                player.setPlayerId(rs.getString("playerId"));
                player.setName(rs.getString("name"));
                player.setGender(rs.getString("gender"));
                player.setCountry(rs.getString("country"));
                player.setSecurityQuestion(rs.getString("securityQuestion"));
                player.setAnswer(rs.getString("answer"));
                player.setProfilePic(rs.getString("profilePic"));
            }
            rs.close();
        }
        catch(Exception ex)
        {
            
        }
        return player;
    }
    
    public beans.Player getPlayer(int playerNo)
    {
        beans.Player player=new Player();
        try
        {
            PreparedStatement ps=con.prepareStatement("Select * from Players where playerno=?");
            ps.setInt(1,playerNo);
            
            
            ResultSet rs=ps.executeQuery();
            boolean ret=rs.next();
            if(ret)
            {
                player.setPlayerNo(rs.getInt("playerNo"));
                player.setPlayerId(rs.getString("playerId"));
                player.setPassword(rs.getString("password"));
                player.setName(rs.getString("name"));
                player.setGender(rs.getString("gender"));
                player.setCountry(rs.getString("country"));
                player.setSecurityQuestion(rs.getString("securityQuestion"));
                player.setAnswer(rs.getString("answer"));
                player.setProfilePic(rs.getString("profilePic"));
            }
            rs.close();
        }
        catch(Exception ex)
        {
            
        }
        return player;
    }
            
    public EnumStatus updateProfile(beans.Player player)
    {
        EnumStatus ret= null;
        try
        {
            PreparedStatement ps = con.prepareStatement("Update Players set name=?,gender=?,country=? where playerNo=?");
            ps.setString(1,player.getName());
            ps.setString(2,player.getGender());
            ps.setString(3,player.getCountry());
            ps.setInt(4,player.getPlayerNo());
            ps.execute();
            ret=EnumStatus.Success;
            
        }
        catch(Exception ex)
        {
            ret=EnumStatus.Fail;
        }
        return ret;
    }
    
    public EnumStatus changePassword(int userNo,String newPassword)
    {
        EnumStatus ret= null;
        try
        {
            PreparedStatement ps = con.prepareStatement("update players set password=? where playerno=?");
            ps.setString(1, newPassword);
            ps.setInt(2,userNo);
            ps.execute();
            ret=EnumStatus.Success;
        }
        catch(Exception ex)
        {
            ret=EnumStatus.Fail;
        }
        
        
        
        return ret;
    }
    public beans.Player forgotPassword(String PlayerId)
    {
        beans.Player player=new Player();
        try
        {
            PreparedStatement ps=con.prepareStatement("Select * from Players where PlayerId=?");
            ps.setString(1,PlayerId);
            
            ResultSet rs=ps.executeQuery();
            boolean ret=rs.next();
            if(ret)
            {
                
                player.setPlayerNo(rs.getInt("playerNo"));
                player.setPlayerId(rs.getString("playerId"));
                player.setPassword(rs.getString("password"));
                player.setName(rs.getString("name"));
                player.setGender(rs.getString("gender"));
                player.setCountry(rs.getString("country"));
                player.setSecurityQuestion(rs.getString("securityQuestion"));
                player.setAnswer(rs.getString("answer"));
                player.setProfilePic(rs.getString("profilePic"));
            }
            rs.close();
        }
        catch(Exception ex)
        {
            
        }
        return player;
    }
    public EnumStatus checkPlayerId(String playerId)
    { 
        EnumStatus ret=null;
        try
        {
            PreparedStatement ps = con.prepareStatement("select * from players where playerid=?");
            ps.setString(1,playerId);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                ret=EnumStatus.Success;
            }
            else
            {
                ret=EnumStatus.Fail;
            }
        }
        catch(Exception ex)
        {
            ret=EnumStatus.Fail;
        }
        return ret;
    }
    
    public ArrayList<beans.Player> getOnlinePlayers(ArrayList<Integer> loggedPlayers )
    {
        ArrayList<beans.Player> op= new ArrayList<>();
        StringBuilder ids=new StringBuilder();

        for( Integer player : loggedPlayers  )
        {
            ids.append(player+",");
        }
        String onlineUsers=ids.substring(0,ids.length()-1);
        
        try
        {
            ResultSet rs = con.createStatement().executeQuery("Select * From Players Where PlayerNo IN ("+ onlineUsers +")");
            
            beans.Player player;
            while(rs.next())
            {   player= new Player();
                player.setPlayerNo(rs.getInt("Playerno"));
                player.setName(rs.getString("Name"));
                player.setGender(rs.getString("Gender"));
                player.setCountry("Country");
                op.add(player);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex );
        }
        return op; 
    }
    
    public void updateProfilePic(int playerNo)
    
    {
        try 
        {
            PreparedStatement ps = con.prepareStatement("update Players set profilepic=? where playerno=?");
            ps.setInt(2, playerNo);
            ps.setString(1,"Pic"+playerNo+".jpg");
            ps.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println(ex);
        }

        
    }

}
