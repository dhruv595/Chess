
<%@page import="datalayer.DALCommonFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALPlayer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp" %>
        <%
            DALPlayer dp= new DALPlayer();
            int playerNo=Integer.parseInt(session.getAttribute("playerNo").toString());
            beans.Player player=dp.getPlayer(playerNo);
            String rbtMale="",rbtFemale="";
            if(player.getGender().equals("Male"))
            {
                rbtMale="checked";
            }
            else
            {
                rbtFemale="checked";
            }
            
        %>
        <form action="/Chess/PlayerServlet"  method="post">
            <table>
                <tr>
                    <th>Name</th>
                    <td>
                        <input type="text" id="txtName" name="txtName" class="input" value="<%=player.getName()%>">
                        <span  class="alert" id="msgName"></span>
                    </td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td>
                        <input type="radio" id="rbtGender" name="rbtGender" value="Male" <%=rbtMale%>>Male
                        <input type="radio" id="rbtGender" name="rbtGender" value="Female" <%=rbtFemale%>>Female
                    </td>
                </tr>
                <tr>
                    <th>Country</th>
                    <td>
                        <select id="ddlCountry" name="ddlCountry" class="input" onchange="validateCountry()">
                            
                            <option>--Select your country--</option>
                            <%
                                datalayer.DALCommonFunctions objDCF=new DALCommonFunctions();
                                ArrayList<beans.Country> AC=objDCF.getCountries();
                                
                                for(beans.Country c:AC)
                                {
                                    if(!(player.getCountry().equals(c.getCountryName())))
                                    {
                            %>
                                        <option><%=c.getCountryName()%></option>                            
                            <%
                                    }
                                    else
                                    {
                             %>
                               
                                       <option selected><%=player.getCountry()%></option>
                            <%
                                    }
                                }           
                            %>
                            <option>Other</option>
                            <input type="text" id="txtOtherCountry" name="txtOtherCountry" class="input"  style="display:none "  >
                       
                    
                        </select>
                    
                        <span  class="alert" id="msgCountry"></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="btnUpdate" class="register" id="btnUpdate" onclick="return validateUpdate() ">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

<%@include file="Footer.jsp" %>