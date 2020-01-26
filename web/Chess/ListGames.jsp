<%@page import="datalayer.DALChess"%>
<%@include file="Header.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int total=0;
    int won=0;
    int lost=0;
    datalayer.DALChess objDAL= new DALChess();
    ArrayList<beans.Game> games=objDAL.getGames(Integer.parseInt(session.getAttribute("playerNo").toString()));
    if(games.size()==0)
    {
        
    
  %>
  <h3>***************You have not play any game yet*************</h3>
  <%
     } 
     else
    {
  %>    
  <div id="ListGame"> 
      <table id="ListGames">
       <tr>
          <th>Game ID </th>
          <th>Start Date </th>
          <th>Opponent </th>
          <th>Country   </th>
          <th>Result </th>
      </tr>
      
   <%   
    
    for(beans.Game game: games)
    {
        total++;
        String result="";
        if(game.getWhite().getPlayerNo()==0)
        {
            result="In Progress";
        }
        else if(game.getWhite().getPlayerNo()==Integer.parseInt(session.getAttribute("playerNo").toString()))
        {
             result="WON"; 
             won++;
        }
        else
        {
            result="LOST";
            lost++;
        }
  
    if(result.equals("WON")||result.equals("LOST"))    
    { 
   %>

<tr>

    <td>
    
    <%=game.getGameId()%> 
    </td>
    <td><%= game.getStartDate()%>    </td>
    <td><%=game.getBlack().getName()%>   </td>
    <td><%=game.getBlack().getCountry()%>  </td>
    <td><%=result%></td>
</tr>
<%
    }
    else
    {
%>
    <td>
    <a href="/Chess/ChessServlet?resume=<%=game.getGameId()%> " class="link"><%=game.getGameId()%> </a> 
    </td>
    <td><%= game.getStartDate()%>    </td>
    <td><a href="../PlayerServlet?otherPlayerNo=<%=game.getBlack().getPlayerNo()%>" class="link"><%=game.getBlack().getName()%> </a>  </td>
    <td><%=game.getBlack().getCountry()%>  </td>
    <td><%=result%></td>
</tr>

<%
    }
    }
 }
    %>
    
</table>
  </div>
  <div id="Total">  
   <table>
       <tr>
            <th>Games Played:</th> 
            <td><%=total%></td>  
            <th>Won:</th>
            <td><%=won%></td>
            <th>Lost:</th>
            <td><%=lost%></td>
       </tr>     
   </table> 
  </div>
      
<%@include file="Footer.jsp" %>
