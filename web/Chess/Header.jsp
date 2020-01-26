
<%@page import="datalayer.DALPlayer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Chess Mania</title>
<link href="../style.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/icon.ico" />
<script src="../Scripts/ChessScript.js" type="text/javascript"></script>
</head>

    <body onload="start()">
  <%
           
           if(session.getAttribute("playerNo")==null)
           {
               RequestDispatcher dispatcher = request.getRequestDispatcher("../Login.jsp");
               request.setAttribute("noLogin", 1);
               dispatcher.forward(request,response);
           }
 %>         
  <div id="headerbg">
  <div id="headerblank">
    <div id="header">
      <div id="menu">
        <ul>
          <li><a href="ProfileDetails.jsp" class="menu">My Profile</a></li>
          <li><a href="Challenges.jsp" class="menu">Challenges</a></li>
          <li><a href="ListGames.jsp" class="menu">List Games</a></li>
          <li><a href="Rules.jsp" class="menu">How to play</a></li>
          <li><a href="../Login.jsp?logout=1" class="menu">LogOut</a></li>
        </ul>
          <br></br>
          <%
           
   
           String name="";
           ArrayList<Integer> loggedPlayers=(ArrayList)application.getAttribute("loggedPlayers");
           int count=loggedPlayers.size();
           DALPlayer dpo= new DALPlayer();
           ArrayList<beans.Player> onlinePlayers=dpo.getOnlinePlayers(loggedPlayers);
           
        %>
        <span id="msgSpan"  ></span><br>
        <table>
            <tr>
                <td>
                    <h4>   Online Players (<%=count-1%>)</h4>
                </td>
            </tr>
                   
        <%
           if(loggedPlayers.size()>1)
           {
               for(beans.Player player: onlinePlayers )
               {
           
                 if(player.getPlayerNo()!=Integer.parseInt(session.getAttribute("playerNo").toString()))
                 {
       %>
                     <tr>
                         <td><a href="../PlayerServlet?otherPlayerNo=<%=player.getPlayerNo()%>" class="link"><%=player.getName()%></a></td>
                    </tr>
        <%
                  }
                }    
            }
         
           else
           {   
           %>
           <tr>
               <td>No players</td>
           </tr>
            
           <%
               }
               %>
            
       </table>
      </div>
  
    </div>
  </div>
</div>
           

<div id="contentbg">
    
  <div id="contentblank">
    <div id="content">
      <div id="contentmid">
        <div id="purposetxt">
            