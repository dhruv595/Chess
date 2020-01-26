package datalayer;

import beans.BlackKing;
import beans.BlackRook;
import beans.Game;
import beans.WhiteKing;
import beans.WhiteRook;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DALChess 
{
    private Connection con =null;
    
    public DALChess()
    {
        con=DBOperations.getConnection();
    }
    public String getLastMove(int gameId)
    {
        String ret="";
        try
        {
            PreparedStatement ps = con.prepareStatement("select lastmove,movestatus from games where gameid=?");
            ps.setInt(1, gameId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                ret=rs.getString("lastmove");
                ret=ret+rs.getString("MoveStatus");
            }
        }
        catch(Exception ex)
        {
            
        }
        
        return ret;
    }
    private String serializeGame(beans.Game game)
    {
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        ObjectOutput objectOut=null;
        String gameObject= null;
        try
        {
            objectOut= new ObjectOutputStream(baos);
            objectOut.writeObject(game);
            
            objectOut.flush();
            
            byte[] bytes=baos.toByteArray();
           
            baos.close();  
            gameObject= new String(bytes);
        
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return gameObject;
    }
    
    public int startGame(beans.Game game)
    {
        int gameId=0;
        String gameObject = serializeGame(game);
        
        try    
        {
           CallableStatement cs= con.prepareCall("{call addGame(?,?,?,?)}");
           cs.registerOutParameter(1, java.sql.Types.INTEGER);
           cs.setString(2,gameObject);
           cs.setInt(3, game.getBlack().getPlayerNo());
           cs.setInt(4,game.getWhite().getPlayerNo());
           cs.executeUpdate();
           gameId=cs.getInt(1);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return gameId;
    }
    public ArrayList<beans.Game> getGames(int playerNo)
    {
        ArrayList<beans.Game> games = new ArrayList<>();
        beans.Game game;
        try
        {
            PreparedStatement ps= con.prepareStatement("select g.gameId,convert(varchar,g.startDate,101)as [startDate], p.Name,p.country,p.playerno,g.status,ISNULL(g.winner,0)as winner\n" +
                                                        "from games as [g], players as [p]\n" +
                                                        "where g.player2=p.playerNo and g.player1=? and g.status!=-1 \n" +
                                                        "union\n" +
                                                        "select g.gameId,convert(varchar,g.startDate,101)as [startDate], p.Name,p.country,p.playerNo,g.status,ISNULL(g.winner,0)as winner\n" +
                                                        " from games as [g], players as [p]\n" +
                                                        " where g.player1=p.playerNo and g.player2=? and g.status!=-1 order by g.gameId desc");
            ps.setInt(1, playerNo);
            ps.setInt(2, playerNo);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                game= new Game();
                game.setGameId((rs.getInt("gameId")));
                game.setStartDate((rs.getString("startDate")));
                game.getBlack().setName((rs.getString("Name")));
                game.getBlack().setCountry(rs.getString("country"));
                game.getBlack().setPlayerNo(rs.getInt("playerno"));
                game.getWhite().setPlayerNo(rs.getInt("winner"));
                games.add(game);
            }
        }
        catch(Exception ex)
        {
            
        }
        
        return games;
    }
    
    public beans.Game getGame(int gameId)
    {
        Game game= null;
        try
        {
            PreparedStatement ps = con.prepareStatement("select * from games where gameId=?");
            ps.setInt(1,gameId);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                byte[] bytes=rs.getString("content").getBytes();
                ByteArrayInputStream bias= new ByteArrayInputStream(bytes);
                ObjectInput oi= new ObjectInputStream(bias);
                game=(Game)oi.readObject();
                oi.close();
                bias.close();
            }   
                
        }
        catch(Exception ex)
        {
            System.out.println("error h bhai");
            System.out.println(ex);
        }
        BlackKing.check=game.getBlackKingCheck();
        WhiteKing.check=game.getWhiteKingCheck();
        BlackKing.castle=game.getBlackKingCastle();
        WhiteKing.castle=game.getWhiteKingCastle();
        BlackRook.leftCastle=game.getBlackRookLeftCastle();
        BlackRook.rightCastle=game.getBlackRookRightCastle();
        WhiteRook.leftCastle=game.getWhiteRookLeftCastle();
        WhiteRook.rightCastle=game.getWhiteRookRightCastle();
        
        return game;
    }
    
    
    public int updateGame(beans.Game game,int gameId)
    {
        
        
          game.setBlackKingCheck(BlackKing.check);
          game.setWhiteKingCheck(WhiteKing.check);
          game.setBlackKingCastle(BlackKing.castle);
          game.setWhiteKingCastle(WhiteKing.castle);
          game.setBlackRookLeftCastle(BlackRook.leftCastle);
          game.setBlackRookRightCastle(BlackRook.rightCastle);
          game.setWhiteRookLeftCastle(WhiteRook.leftCastle);
          game.setWhiteRookRightCastle(WhiteRook.rightCastle);
          String gameObject = serializeGame(game);  
        try    
        {
           PreparedStatement ps= con.prepareStatement("update games set content=?,lastmove=?,moveStatus=? where gameid=?");
           ps.setString(1, gameObject);
           ps.setString(2, game.getLastMove());
           ps.setString(3, game.getMoveStatus());
           ps.setInt(4, gameId);
           ps.executeUpdate();
       }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return gameId;
    }
    public void unloadStaticVariable()
    {
        BlackKing.check=0;
        WhiteKing.check=0;
        BlackKing.castle=0;
        WhiteKing.castle=0;
        BlackRook.leftCastle=0;
        BlackRook.rightCastle=0;
        WhiteRook.leftCastle=0;
        WhiteRook.rightCastle=0;
        
    }
    
    public void endGame(int gameId,int playerNo)
    {
        try
        {
            PreparedStatement ps= con.prepareStatement("update games set status=?,winner=? where gameId=?");
            ps.setInt(1,1);
            ps.setInt(2, playerNo);
            ps.setInt(3,gameId);
            ps.executeUpdate();
        }
        catch(Exception ex)
        {
            
        }
    }
    public boolean pendingRequest(int playerNo)
    {
        boolean ret=false;
        try
        {
            PreparedStatement ps =con.prepareStatement("select * from  games where status=? and player2=?");
            ps.setInt(1,-1);
            ps.setInt(2,playerNo);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                ret=true;
            }
        
        }
        catch(Exception ex)
        {
            
        }
        
        
        return ret;
    }
    
    
    
    public ArrayList<beans.Game> getPendingChallenges(int playerNo)
    {
        ArrayList<beans.Game> games= new ArrayList<>();
        try
        {
          
            PreparedStatement ps =con.prepareStatement("select g.gameId,p.Name,p.country,p.playerNo"
                                                      + " from games  as [g],players as [p]"
                                                      +  "where g.status=? and g.player2=? and g.player1=p.playerno order by g.gameid");
            ps.setInt(1,-1);
            ps.setInt(2,playerNo);
            ResultSet rs=ps.executeQuery();
            beans.Game game;
            while(rs.next())
            {
                game= new Game();
                game.setGameId(rs.getInt("GameId"));
                game.getBlack().setName(rs.getString("Name"));
                game.getBlack().setPlayerNo(rs.getInt("playerNo"));
                game.getBlack().setCountry(rs.getString("Country"));
                games.add(game);
            }
        }
        catch(Exception ex)
        {
            
        }
        return games;
    }
   public void challengeAccepted( int gameId)
   {
       try
       {
           PreparedStatement ps= con.prepareStatement("update games set status=0 where gameId=?");
           ps.setInt(1, gameId);
           ps.executeUpdate();
       }
       catch(Exception ex)
       {
           
       }
   }
   public void challengeDeclined( int gameId)
   {
       try
       {
           PreparedStatement ps= con.prepareStatement("delete from games where gameId=?");
           ps.setInt(1, gameId);
           ps.executeUpdate();
       }
       catch(Exception ex)
       {
           
       }
   }
   
   
   
    
}









