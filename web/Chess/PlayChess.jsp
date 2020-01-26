
<%@page import="beans.WhiteBishop"%>
<%@page import="beans.BlackBishop"%>
<%@page import="beans.WhiteKnight"%>
<%@page import="beans.BlackKnight"%>
<%@page import="beans.WhiteRook"%>
<%@page import="beans.BlackRook"%>
<%@page import="beans.WhiteQueen"%>
<%@page import="beans.BlackQueen"%>
<%@page import="beans.WhiteKing"%>
<%@page import="beans.WhitePawn"%>
<%@page import="beans.BlackPawn"%>
<%@page import="beans.Piece"%>
<%@page import="beans.BlackKing"%>
<%@page import="java.applet.AudioClip"%>
<%@page import="datalayer.DALPlayer"%>
<%@page import="beans.Game"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chess Mania</title>
        <link href="../style.css" rel="stylesheet" type="text/css"/>
        <script src="../Scripts/ChessScript.js" type="text/javascript"></script>
        <script src="../Scripts/jquery.js" type="text/javascript"></script>
    </head>
    <body>  
        <audio autoplay="" id="audio" >
            <source src="" type="audio/mpeg">
            Your browser does not support the <code>audio</code> tag.
        </audio>
        <%
            new datalayer.DALChess().unloadStaticVariable();
            int gameId=Integer.parseInt(session.getAttribute("gameId").toString());
            
            beans.Game game=new datalayer.DALChess().getGame(gameId);
            
        %>

        <%
            
            int i;
            boolean flag = false;

        
        %>
        
        <br><br>
        
        <input type="hidden" value="b" id="currentPlayer">
        <input type="hidden" value="<%=game.getBlackDeletedCount()%>" id="blackDeletedCount">
        <input type="hidden" value="<%=game.getWhiteDeletedCount()%>" id="whiteDeletedCount">
        <input type="hidden" value="<%=game.getBlack().getName()%> wins" id="blackWin">
        <input type="hidden" value="<%=game.getWhite().getName()%> wins" id="whiteWin">
        <center>
            
                
               
            
             
            <b style="color:#b22d00">Turn:</b><input type="text" value="black" class="status" id="currentColor" readonly="" >
            <b style="color:#b22d00">Move Status:</b> <input type="text" value="" class="Lstatus" id="txt1" readonly="">
            <input type="button" class="register" value="FLIP" onclick="reverse()">
            <a href="../Chess/ProfileDetails.jsp"><input type="button" class="register" hidden="" value="EXIT"  id="btnExit" ></a>
            
        </center>
        <div id="BlackDead">
            
            <h2 style="border:2pt black">
               <%=game.getBlack().getName() +"  " %>
            </h2>
                
            <table>
                <%
                    for(int k=0;k<=7;k++)
                    {
                %>  
                    <tr>
                        <td>
                            <img src="" id="blackDead<%=k%>" hidden="" />
                        </td>
                        
                    </tr>    
                <%
                    }
                 %>
            </table>
            <table>
                <%
                    for(int k=8;k<=15;k++)
                    {
                %>  
                    <tr>
                        <td>
                            <img src="" id="blackDead<%=k%>" hidden="" />
                        </td>
                        
                    </tr>    
                <%
                    }
                 %>
            </table>
        </div>
    <div id="ChessBoard">
        <table id="TChessBoard" >
            <%
                Piece chessBoard[][]=game.getChessBoard();
                
                for( i=0;i<8;i++)
                {
                    flag=!flag;
            %>
            <tr>
            <%
                    for(int j=0;j<8;j++)
                    {
                        String src="";
                        if(chessBoard[i][j] instanceof BlackPawn)
                        {
                            src="ChessImages/black_pawn.png";
                        }
                        else if(chessBoard[i][j] instanceof WhitePawn)
                        {
                            src="ChessImages/white_pawn.png";
                        }
                        else if(chessBoard[i][j] instanceof BlackKing)
                        {
                            src="ChessImages/black_king.png";
                        }
                        else if(chessBoard[i][j] instanceof WhiteKing)
                        {
                            src="ChessImages/white_king.png";
                        }
                        else if(chessBoard[i][j] instanceof BlackQueen)
                        {
                            src="ChessImages/black_queen.png";
                        }
                        else if(chessBoard[i][j] instanceof WhiteQueen)
                        {
                            src="ChessImages/white_queen.png";
                        }
                        else if(chessBoard[i][j] instanceof BlackRook)
                        {
                            src="ChessImages/black_rook.png";
                        }
                        else if(chessBoard[i][j] instanceof WhiteRook)
                        {
                            src="ChessImages/white_rook.png";
                        }
                        else if(chessBoard[i][j] instanceof BlackKnight)
                        {
                            src="ChessImages/black_knight.png";
                        }
                        else if(chessBoard[i][j] instanceof WhiteKnight)
                        {
                            src="ChessImages/white_knight.png";
                        }
                        else if(chessBoard[i][j] instanceof BlackBishop)
                        {
                            src="ChessImages/black_bishop.png";
                        }
                        else if(chessBoard[i][j] instanceof WhiteBishop)
                        {
                            src="ChessImages/white_bishop.png";
                        }
                        
                
                %>
                
                    <td class="<%=flag?"black":"white"%>" >
                        <img src="<%=src%>" onclick="validateMove(<%=i%>,<%=j%>)" id="cell<%=i%><%=j%>" >
                        
                    </td>
                
                
                <%
                    flag=!flag;
                  }
                %>
                     </tr>
                <%
                }   
                %>
        </table>
        </div>    
    <div id="WhiteDead">
            <h2 style="color:white">    
                <%="    "+game.getWhite().getName()%>
            </h2>
            <table>
                <%
                    for(int k=0;k<=7;k++)
                    {
                %>  
                    <tr>
                        <td>
                            <img src="" id="whiteDead<%=k%>" hidden="" />
                        </td>
                        
                    </tr>    
                <%
                    }
                 %>
            </table>
            <table>
                <%
                    for(int k=8;k<=15;k++)
                    {
                %>  
                    <tr>
                        <td>
                            <img src="" id="whiteDead<%=k%>" hidden="" />
                        </td>
                        
                    </tr>    
                <%
                    }
                 %>
            </table>
        </div>        
    </body>

</html>



















