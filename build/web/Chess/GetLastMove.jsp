<%
  
    int gameId=Integer.parseInt(session.getAttribute("gameId").toString());
    
    String ret=new datalayer.DALChess().getLastMove(gameId);
    
    



%>

<%=ret%>