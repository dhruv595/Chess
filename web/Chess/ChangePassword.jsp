
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="Header.jsp" %>
       
        <%
            String msg="";
            if(request.getParameter("flag")!=null)
            {
                msg="Old Password is wrong";
            }
            
        %>
        
        <form action="/Chess/PlayerServlet" method="post">
            <table>
                
                <tr>
                    <td>Old Password</td>
                    <td>
                        <input type="password" id="txtOldPassword" class="input" name="txtOldPassword" placeholder="Old Password">
                        <span class="alert" id="msgOldPassword"><%=msg%></span>
                    </td>
                    
                </tr>
                <tr>
                    <td>New Password</td>
                    <td>
                        <input type="password" id="txtNewPassword" class="input" name="txtNewPassword" placeholder="New Password">
                        <span class="alert" id="msgNewPassword"></span>
                    </td>
                </tr>
                <tr>
                    <td>Confirm Password</td>
                    <td>
                        <input type="password" id="txtConfirmPassword" class="input" name="txtConfirmPassword" placeholder="Confirm Password">
                        <span  class="alert" id="msgConfirmPassword"></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Change Password" name="btnChangePassword" class="register" onclick="return validateChangePassword()"></td>
                </tr>
            </table>
        </form>
<%@include file="Footer.jsp" %>
