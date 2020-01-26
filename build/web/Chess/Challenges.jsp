<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="Header.jsp" %>
<%
    
    int playerNo=Integer.parseInt(session.getAttribute("playerNo").toString());
    ArrayList<beans.Game> games= new datalayer.DALChess().getPendingChallenges(playerNo);
    if(games.size()>0)
    {

%>
<div id="Rules">
    <table id="ListGames">
        <tr>
            <th>Game Id</th>
            <th>Name</th>
            <th>Country</th>
            <th>Accept/Reject</th>
        </tr>
        <%
            
            for(beans.Game game:games)
            {
        %>
        
        <tr>
            <td><%=game.getGameId()%></td>
            <td><a href="../PlayerServlet?otherPlayerNo=<%=game.getBlack().getPlayerNo()%>" class="link"><%=game.getBlack().getName()%></a></td>
            <td><%=game.getBlack().getCountry()%></td>
            <td>
                <button class="register"><a href="/Chess/ChessServlet?gameId=<%=game.getGameId()%>" class="link"> Yes</a></button>
                <button class="register"><a href="/Chess/ChessServlet?deleteGameId=<%=game.getGameId()%>" class="link"> No</a></button>
                
                
            </td>
        </tr>
        
        
        <%
                }
            } 
            else
            {
         %>
         <tr>
             <td><h3>*****************You have no Challenges yet**********************</h3></td>
         </tr>
         <%
            }
         %>
    </table>
    
    
</div>


<%@include file="Footer.jsp" %>

