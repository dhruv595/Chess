
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp" %>
        <%
            
            
           
            
        %>
        
        <form action="/Chess/PlayerServlet" method="post">
            <table>
                
                <tr>
                    <td>New Password</td>
                    <td>
                        <input type="password" id="txtNewPassword"  name="txtNewPassword" class="input" placeholder="New Password">
                        <span class="alert" id="msgNewPassword"></span>
                    </td>
                </tr>
                <tr>
                    <td>Confirm Password</td>
                    <td>
                        <input type="password" id="txtConfirmPassword" class="input" name="txtConfirmPassword" placeholder="Confirm Password">
                        <span class="alert" id="msgConfirmPassword"></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Change Password" name="btnChangeForgotPassword" class="register" onclick="return validateChangePassword()"></td>
                </tr>
            </table>
        </form>
    
<%@include file="Footer.jsp" %>