
<%@page import="beans.BlackBishop"%>
<%@page import="beans.WhiteBishop"%>
<%@page import="beans.WhiteKnight"%>
<%@page import="beans.BlackKnight"%>
<%@page import="beans.WhiteRook"%>
<%@page import="beans.BlackRook"%>
<%@page import="beans.WhiteQueen"%>
<%@page import="beans.BlackQueen"%>
<%@page import="beans.WhiteKing"%>
<%@page import="beans.BlackKing"%>
<%@page import="beans.WhitePawn"%>
<%@page import="beans.BlackPawn"%>
<%@page import="beans.Piece"%>
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
        
        <%
            new datalayer.DALChess().unloadStaticVariable();
            int gameId=0;
            String turn="";
            String color="";
            String flip="";
            int playerNo=Integer.parseInt(session.getAttribute("playerNo").toString());
            if(session.getAttribute("gameId")!=null)
            {
                gameId =Integer.parseInt(session.getAttribute("gameId").toString());
            }
            beans.Game game=new datalayer.DALChess().getGame(gameId);
            if(game.getCurrentPlayer()!=null)
            {
                if(game.getCurrentPlayer()==game.getBlack())
                {
                    turn="black";
                    color="b";
                }
                else if(game.getCurrentPlayer()==game.getWhite())
                {
                    turn="white";
                    color="w";
                }
                
                if(game.getBlack().getPlayerNo()==playerNo)
                {
                    flip="reverse()";
                }
            }
        
        
        
        %>

        <%
            
            int i;
            boolean flag = false;

        
        %>
    <body onload="<%=flip%>">
        <audio autoplay="" id="audio" >
            <source src="" type="audio/mpeg">
            Your browser does not support the <code>audio</code> tag.
        </audio>
    
        <br>
        <br>
        <input type="hidden" value="<%=color%>" id="currentPlayer">
        <input type="hidden" value="<%=game.getBlackDeletedCount()%>" id="blackDeletedCount">
        <input type="hidden" value="<%=game.getWhiteDeletedCount()%>" id="whiteDeletedCount">
        <input type="hidden" value="<%=game.getBlack().getName()%> wins" id="blackWin">
        <input type="hidden" value="<%=game.getWhite().getName()%> wins" id="whiteWin">
        <input type="hidden" value="<%=game.getLastMove()%>"  id="lastMove">
        
        <center>                
            
            <b> Turn:</b><input type="text" value="<%=turn%>" class="status" id="currentColor" readonly="" >
            <b>Move Status: </b><input type="text" value="" class="Lstatus" id="txt1" readonly="">
            <a href="../Chess/ProfileDetails.jsp"><input type="button" class="register" value="EXIT"  id="btnExit"></a>
        
        </center>
        <div id="BlackDead">
            <h2>    
                <%=game.getBlack().getName()%>
            </h3>
            <table >
                <%
                    
                    Piece blackPieceDeleted[]=game.getBlackPieceDeleted();
                    for(int k=0;k<=7;k++)
                    {
                        
                        if(blackPieceDeleted[k]==null)
                        {
                %>
                    <tr>
                        <td>
                            <img src="" id="blackDead<%=k%>" hidden="" />
                        </td>
                        
                    </tr> 
                <%
                        }
                        else
                        {
                            String src="";
                            if(blackPieceDeleted[k] instanceof BlackPawn)
                            {
                                src="ChessImages/black_pawn.png";
                            }
                            
                            else if(blackPieceDeleted[k] instanceof BlackKing)
                            {
                                src="ChessImages/black_king.png";
                            }
                            else if(blackPieceDeleted[k] instanceof BlackQueen)
                            {
                                src="ChessImages/black_queen.png";
                            }
                            else if(blackPieceDeleted[k] instanceof BlackRook)
                            {
                                src="ChessImages/black_rook.png";
                            }
                            else if(blackPieceDeleted[k] instanceof BlackKnight)
                            {
                                src="ChessImages/black_knight.png";
                            }
                            
                            else if(blackPieceDeleted[k] instanceof BlackBishop)
                            {
                                src="ChessImages/black_bishop.png";
                            }
                
                %>  
                    <tr>
                        <td>
                            <img src="<%=src%>" id="blackDead<%=k%>"  />
                        </td>
                        
                    </tr>    
                <%
                         }
                    }        
                 %>
            </table>
            <table>
                <%
                    for(int k=8;k<=15;k++)
                    {
                        
                        if(blackPieceDeleted[k]==null)
                        {
                %>
                    <tr>
                        <td>
                            <img src="" id="blackDead<%=k%>" hidden="" />
                        </td>
                        
                    </tr> 
                <%
                        }
                        else
                        {
                            String src="";
                            if(blackPieceDeleted[k] instanceof BlackPawn)
                            {
                                src="ChessImages/black_pawn.png";
                            }
                            
                            else if(blackPieceDeleted[k] instanceof BlackKing)
                            {
                                src="ChessImages/black_king.png";
                            }
                            else if(blackPieceDeleted[k] instanceof BlackQueen)
                            {
                                src="ChessImages/black_queen.png";
                            }
                            else if(blackPieceDeleted[k] instanceof BlackRook)
                            {
                                src="ChessImages/black_rook.png";
                            }
                            else if(blackPieceDeleted[k] instanceof BlackKnight)
                            {
                                src="ChessImages/black_knight.png";
                            }
                            
                            else if(blackPieceDeleted[k] instanceof BlackBishop)
                            {
                                src="ChessImages/black_bishop.png";
                            }
                
                %>  
                    <tr>
                        <td>
                            <img src="<%=src%>" id="blackDead<%=k%>"  />
                        </td>
                        
                    </tr>    
                <%
                         }
                    }        
                 %>
            </table>
        </div>
         <%
         if(game.getCurrentPlayer()!=null)
         {
            if(game.getCurrentPlayer().getPlayerNo()!=playerNo)
            {
              
            
         %>
             <div  id="ChessBoard" style="pointer-events:none ">
            <%
                
            }
            else
            {
                %>
          <div  id="ChessBoard">      
                <%
            }
         }
         else
         {
                    %>
            <div  id="ChessBoard">        
          <%
         }  
         %>
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
                
                    <td class="<%=flag?"black":"white"%>" id="table<%=i%><%=j%>" >
                        <img src="<%=src%>" onclick="validateMove(<%=i%>,<%=j%>)" id="cell<%=i%><%=j%>" alt="">
                        
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
                    
                    Piece whitePieceDeleted[]=game.getWhitePieceDeleted();
                        
                    for(int k=0;k<=7;k++)
                    {
                        
                        if(whitePieceDeleted[k]==null)
                        {
                %>
                    <tr>
                        <td>
                            <img src="" id="whiteDead<%=k%>" hidden="" />
                        </td>
                        
                    </tr> 
                <%
                        }
                        else
                        {
                            String src="";

                            if(whitePieceDeleted[k] instanceof WhitePawn)
                            {
                                src="ChessImages/white_pawn.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteKing)
                            {
                                src="ChessImages/white_king.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteQueen)
                            {
                                src="ChessImages/white_queen.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteRook)
                            {
                                src="ChessImages/white_rook.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteKnight)
                            {
                                src="ChessImages/white_knight.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteBishop)
                            {
                                src="ChessImages/white_bishop.png";
                            }
                
                %>  
                    <tr>
                        <td>
                            <img src="<%=src%>" id="whiteDead<%=k%>"  />
                        </td>
                        
                    </tr>    
                <%
                         }
                    }        
                 %>
            </table>
            <table>
                <%
                    for(int k=8;k<=15;k++)
                    {
                        
                        if(whitePieceDeleted[k]==null)
                        {
                %>
                    <tr>
                        <td>
                            <img src="" id="whiteDead<%=k%>" hidden="" />
                        </td>
                        
                    </tr> 
                <%
                        }
                        else
                        {
                            String src="";

                            if(whitePieceDeleted[k] instanceof WhitePawn)
                            {
                                src="ChessImages/white_pawn.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteKing)
                            {
                                src="ChessImages/white_king.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteQueen)
                            {
                                src="ChessImages/white_queen.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteRook)
                            {
                                src="ChessImages/white_rook.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteKnight)
                            {
                                src="ChessImages/white_knight.png";
                            }
                            else if(whitePieceDeleted[k] instanceof WhiteBishop)
                            {
                                src="ChessImages/white_bishop.png";
                            }
                
                %>  
                    <tr>
                        <td>
                            <img src="<%=src%>" id="whiteDead<%=k%>"  />
                        </td>
                        
                    </tr>    
                <%
                         }
                    }        
                 %>
            </table>
                  </div> 
    </body>

</html>












