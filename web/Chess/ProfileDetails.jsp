<%@include file="Header.jsp" %>
<%@page import="datalayer.DALPlayer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%
            String message="";
            DALPlayer dp= new DALPlayer();
            int playerNo=Integer.parseInt(session.getAttribute("playerNo").toString());
            beans.Player player=dp.getPlayer(playerNo);
            if(request.getParameter("change")!=null)
            {
                message="Password Successfully changed";
            }
            if(request.getParameter("challenge")!=null)
            {
                message="Challenge sent";
            }
        %>
        
        <a href="ChangeProfilePic.jsp" class="link"><img src="../Pictures/<%=player.getProfilePic()%>" alt="" id="profileImg"></a>
        
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
            <tr></tr>
            <tr>
                <td><a href="UpdateProfile.jsp" class="link" >Update</a></td>
                <td><a href="ChangePassword.jsp" class="link">Change Password</a></td>
            </tr>
            <tr>
                <td></td>
                <td><%=message%></td>
            </tr>
        </table>
          
        <br><br><br>
<%@include file="Footer.jsp" %>