
<%@page import="java.util.ArrayList"%>
<%@include file="Header.jsp" %>
        <%
            String msg="";
            String playerId="";
            String password="";
            if(request.getParameter("logout")!=null)
            {
                ArrayList<Integer> user=(ArrayList<Integer>)application.getAttribute("loggedPlayers");
                user.remove(user.indexOf(session.getAttribute("playerNo")));
                application.setAttribute("loggedPlayers",user);
                session.removeAttribute("playerNo");
                session.removeAttribute("available");
                msg="Successfully logged out";
            }
            if(request.getParameter("flag")!=null)
            {
                msg="Wrong credentials";
            }
            if(request.getAttribute("change")!=null)
            {
                msg="Password Successfully changed";
            }
            if(request.getAttribute("noLogin")!=null)
            {
                msg="You have not logged in yet";
                
            }
            
            
            Cookie[] allCookies=request.getCookies();
            if(allCookies!=null)
            {
            for(Cookie c:allCookies)
            {
                if(c.getName().equals("lastPlayerId"))
                {
                    playerId=c.getValue();
                    
                }
                if(c.getName().equals("lastPlayerPassword"))
                {
                    password=c.getValue();
                }
            }
            }
        %>

        <form method="post" action="PlayerServlet">
            <table>
                <tr>
                    <td>
                        <b>User name:</b>
                    </td>
                    <td>
                        <input type="text" id="txtPlayerId" name="txtPlayerId" value="<%=playerId%>" class="input" >
                        <span  class="alert" id="msgPlayerId"></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Password:</b>
                    </td>
                    <td>
                        <input type="password" id="txtPassword" name="txtPassword" value="<%=password%>" class="input">
                        <span class="alert" id="msgPassword"></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="checkbox" id="chkRememberMe" name="chkRememberMe"> Remember me</td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="btnLogin" class="register" value="Login" onclick="return validateLogin() "></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="ForgotPassword.jsp" class="link">Forgot Password?</a></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="Registration.jsp" class="link">Create a new account</td>
                </tr>
                <tr>
                    <td></td>
                    <td><%=msg%></td>
                </tr>
            </table>
        </form>
 <%@include file="Footer.jsp" %>
    
