
<%@page import="datalayer.DALPlayer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp" %>        
<%
            String msg="";
            String button="Next";
            String id="";
            String error="";
            String userError="";
            if(request.getParameter("flag")!=null)
            {
                int playerNo=Integer.parseInt(session.getAttribute("playerNo").toString());
                datalayer.DALPlayer objDAL=new DALPlayer();
                beans.Player player=objDAL.getPlayer(playerNo);
                msg=player.getSecurityQuestion();
                button="Submit";
                id=player.getPlayerId();
            }
            if(request.getAttribute("change")!=null)
            {
                error="Answer is not correct";
            }
            if(request.getParameter("wrong")!=null)
            {
                userError="UserId doesnot Exists";
            }
        %>
        <form action="/Chess/PlayerServlet" method="post">
            <table>
                <tr>
                    <td>Username</td>
                    <td>
                        <input type="text" class="input" name="txtPlayerId" id="txtPlayerId" value="<%=id%>">                    
                        <span class="alert" > <%=userError%></span>
                    </td>
                </tr>
                <%
                    if(request.getParameter("flag")!=null)
                    {
                %>
                <tr>
                    <td>Security Question</td>
                    <td><span class="alert" id="msgSecurityQuestion"><%=msg%></span></td>                   
                </tr>
                <tr>
                    <td>Answer:</td>
                    <td><input type="password" name="txtAnswer" id="txtAnswer"</td>
                </tr>
                <%
                    }
                 %>
                <tr>
                    <td></td>
                    <td><input type="submit" class="register" name="btn<%=button%>" value="<%=button%>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><%=error%></td>
                </tr>
                 
            </table>
        </form>

<%@include file="Footer.jsp"%>