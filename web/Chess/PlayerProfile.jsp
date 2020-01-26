<%@include file="Header.jsp" %>
<%@page import="datalayer.DALPlayer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%
            
            DALPlayer dp= new DALPlayer();
            int playerNo=Integer.parseInt(request.getParameter("otherPlayer").toString());
            beans.Player player=dp.getPlayer(playerNo);
            
        %>
        <img src="../Pictures/<%=player.getProfilePic()%>" alt="" id="profileImg">
        <br>
        <br>
        <br>
        <table>
            <tr>
                <td><b>Name:</b></td>
                <td><%=player.getName()%></td>
            </tr>
            <tr>
                <td><b>Gender:</b></td>
                <td><%=player.getGender()%></td>
            </tr>
            <tr>
                <td><b>Country:</b></td>
                <td><%=player.getCountry()%></td>
            </tr>
            <tr>
                
            </tr>
            <tr>
                
                <td colspan="2" ><a href="../ChessServlet?game=1&otherPlayerNo=<%=playerNo%>"><input type="submit" value="Challenge" class="register" id="btnChallenge" ></a></td>
                
            </tr>
        </table>
                <br><br><br>
<%@include file="Footer.jsp" %>