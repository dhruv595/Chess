<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALCommonFunctions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp" %>
        <%
            String message="";
            String name="";
            String id="";
            if(request.getAttribute("name")!=null)
            {
                message="User name already exists!!";
                name=request.getAttribute("name").toString();
            }
            if(request.getAttribute("id")!=null)
            {
                message="User name already exists!!";
                id=request.getAttribute("id").toString();
            }
            if(request.getAttribute("idAvailable")!=null)
            {
                message="Id available";
                id=request.getAttribute("idAvailable").toString();
            }
         %>
        <table>
            <form action=PlayerServlet method=post>
                <tr>
                    <td><b>User name :</b></td>
                    <td>
                        <input type="text" id="txtPlayerId" name="txtPlayerId" value="<%=id%>" placeholder="eg-dhruv123" class="input" onblur=" return validateUsername()" >
                        <span class="alert" id="msgPlayerId"><%=message%></span>
                        
                    
                    </td>
                    <td>
                        <input type="submit" id="" name="btnCheck" value="Check" class="more">
                        </td>
                </tr>
                <tr>
                    <td><b>Password :</b></td>
                    <td>
                        <input type="password" id="txtPassword" name="txtPassword" class="input">
                        <span class="alert" id="msgPassword"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Confirm Password :</b></td>
                    <td>
                        <input type="password" id="txtConfirmPassword" name="txtConfirmPassword" class="input">
                        <span class="alert" id="msgConfirmPassword"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Name :</b></td>
                    <td>
                        <input type="text" id="txtName" name="txtName"  value="<%=name%>" placeholder="eg-Dhruv Sharma" class="input">
                        <span class="alert" id="msgName"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Gender :</b></td>
                    <td>
                        <input type="radio" id="rbtGender" name="rbtGender" value="Male" checked>Male
                        <input type="radio" id="rbtGender" name="rbtGender" value="Female">Female
                    </td>
                </tr>
                <tr>
                    <td><b>Country :</b></td>
                    <td>
                        <select id="ddlCountry" name="ddlCountry" onchange="validateCountry()" class="input" >
                            <option selected>Select your Country</option>
                            <%
                                datalayer.DALCommonFunctions objDCF=new DALCommonFunctions();
                                ArrayList<beans.Country> AC=objDCF.getCountries();
                                for(beans.Country c:AC)
                                {
                            %>
                            <option><%=c.getCountryName()%></option>                            
                            <%
                                }
                            %>
                            <option>Other</option>
                        </select>
                        <span class="alert" id="msgCountry"></span>
                            
                        <input type="text" id="txtOtherCountry" name="txtOtherCountry" class="input"  style="display:none"   >
                       
                    </td>
                </tr>
                <tr>
                    <td><b>Security Question :</b></td>
                    <td>
                        <select id="ddlSecurityQuestion" name="ddlSecurityQuestion" class="input" >
                            <option>--Security Question--</option>
                            <option>What was your childhood nickname?</option>
                            <option>What was your favorite food as a child?</option>
                            <option>What is your favorite movie?</option>
                            <option>What is your birth's place?</option>
                        </select>
                        
                    
                        <span  class="alert" id="msgSecurityQuestion"></span>
                    </td>
                </tr>
                <tr>
                    <td><b>Answer :</b></td>
                    <td><input type="password" id="txtAnswer" name="txtAnswer" class="input">
                        <span  class="alert" id="msgAnswer"></span>
                    </td>
                </tr>
                
                <tr>
                    
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Register" id="btnRegister" name="btnRegister" onclick="return validateRegistration()" class="register"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="Login.jsp" class="link">Already a User?</a></td>
                </tr>
            </table>
        </form>
     <%@include file="Footer.jsp" %>

