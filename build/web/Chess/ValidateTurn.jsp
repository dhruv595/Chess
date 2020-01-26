<%@page import="datalayer.DALChess"%>
<%@page import="beans.WhiteRook"%>
<%@page import="beans.BlackRook"%>
<%@page import="beans.WhiteQueen"%>
<%@page import="beans.BlackQueen"%>
<%@page import="beans.Piece"%>
<%@page import="beans.WhiteKing"%>
<%@page import="beans.BlackKing"%>
<%@page import="beans.WhitePiece"%>
<%@page import="beans.BlackPiece"%>
<%
    int row1=Integer.parseInt(request.getParameter("row1").toString());
    int column1=Integer.parseInt(request.getParameter("column1").toString());
    int row2=Integer.parseInt(request.getParameter("row2").toString());
    int column2=Integer.parseInt(request.getParameter("column2").toString());

    int gameId=Integer.parseInt(session.getAttribute("gameId").toString());
    beans.Game game=new datalayer.DALChess().getGame(gameId);
    Piece ChessBoard[][]=game.getChessBoard();
    String ret= "false";
    String check="";
    Piece piece= null;
    Piece queen=null;
    
    int blackCheck=BlackKing.check;
    int whiteCheck=WhiteKing.check;
    if(ChessBoard[row1][column1] instanceof beans.BlackPiece)
    {   
        piece= new BlackKing();
        queen= new BlackQueen();
        beans.BlackPiece blackPiece=(BlackPiece) ChessBoard[row1][column1];
        ret=blackPiece.validateMove(row1,column1,row2,column2,ChessBoard);
        if(ret.equals("true"))
        {
            
                check=blackPiece.validateCheck(row2,column2,game);
                if(check.equals("check"))
                {
                    ret=check;
                    WhiteKing.check=1;
                    Piece.rowChecked=row2;
                    Piece.columnChecked=column2;
                    
                }
            
        }
        else if(ret.equals("revive"))
        {
               check=new BlackQueen().validateCheck(row2,column2, game);
               if(check.equals("check"))
               {
                   ret="rcheck";
                   WhiteKing.check=1;
                   Piece.rowChecked=row2;
                   Piece.columnChecked=column2;
                    
                        
               }
        }
        
        else if((ret.equals("left castle")||ret.equals("right castle"))&&blackCheck==1)
        {
            ret="Castling not possible";
        }
        else if(ret.equals("left castle"))
        {
                check= new BlackRook().validateCheck(row2, column2+1, game);
                if(check.equals("check"))
                {
                   ret="lcc";
                   WhiteKing.check=1;
                   Piece.rowChecked=row2;
                   Piece.columnChecked=column2+1;
                         
                }
        }
       else if(ret.equals("right castle"))
        {
                check= new BlackRook().validateCheck(row2, column2-1, game);
                if(check.equals("check"))
                {
                   ret="rcc";
                   WhiteKing.check=1;
                   Piece.rowChecked=row2;
                   Piece.columnChecked=column2-1;
                    
                        
                }
        }    
        
    }
    else
    {
        if(ChessBoard[row1][column1] instanceof beans.WhitePiece)
        {   
            piece= new WhiteKing();
            queen= new WhiteQueen();
            beans.WhitePiece whitePiece=(WhitePiece) ChessBoard[row1][column1];
            
            ret=whitePiece.validateMove(row1,column1,row2,column2,ChessBoard);
            
            if(ret.equals("true"))
            {
                    check=whitePiece.validateCheck(row2,column2, game);
                    if(check.equals("check"))
                    {
                        ret=check;
                        BlackKing.check=1;
                        Piece.rowChecked=row2;
                        Piece.columnChecked=column2;
                    
                    }
                
            }
            else if(ret.equals("revive"))
            {
                    check=new WhiteQueen().validateCheck(row2,column2, game);
                    if(check.equals("check"))
                    {
                        ret="rcheck";
                        BlackKing.check=1;
                        Piece.rowChecked=row2;
                        Piece.columnChecked=column2;
                    
                    }
            }
            else if((ret.equals("left castle")||ret.equals("right castle"))&&whiteCheck==1)
            {
                ret="Castling not possible";
            }
            else if(ret.equals("left castle"))
            {
                check= new WhiteRook().validateCheck(row2, column2+1, game);
                if(check.equals("check"))
                {
                   ret="lcc";
                   BlackKing.check=1;
                   Piece.rowChecked=row2;
                   Piece.columnChecked=column2+1;
                         
                }
            }
            else if(ret.equals("right castle"))
            {
                check= new WhiteRook().validateCheck(row2, column2-1, game);
                if(check.equals("check"))
                {
                   ret="rcc";
                   BlackKing.check=1;
                   Piece.rowChecked=row2;
                   Piece.columnChecked=column2-1;
                    
                        
                }
            }
        }
    }
    
    if(BlackKing.check==1&&ret=="true")
    {
        BlackKing.check=0;
    }
    if(WhiteKing.check==1&&ret=="true")
    {
        WhiteKing.check=0;
    }
    
    if(ret.equals("true")||ret.equals("check")||ret.equals("revive")||ret.equals("rcheck")||ret.equals("left castle")||ret.equals("right castle")||ret.equals("lcc")||ret.equals("rcc"))
    {   
        String save="";
        Piece pieceDelete=ChessBoard[row2][column2];
        ChessBoard[row2][column2]=ChessBoard[row1][column1];
        ChessBoard[row1][column1]=null;
        
        save=piece.saveKing(ChessBoard);
      
        if(save.equals("Illegal Move"))
        {
            ret=save;
            ChessBoard[row1][column1]=ChessBoard[row2][column2];
            ChessBoard[row2][column2]=pieceDelete;
            pieceDelete=null;
        }
        if(ret.equals("true"))
        {
            if(piece.isKingCheck(ChessBoard).equals("check"))
            {
                ret="check";
                if(piece instanceof BlackKing)
                {
                    WhiteKing.check=1;
                }
                if(piece instanceof WhiteKing)
                {
                    BlackKing.check=1;
                }
            }
        }
        if(ret.equals("left castle")||ret.equals("lcc"))
        {
            ChessBoard[row2][column2+1]=ChessBoard[row2][column2-1];
            ChessBoard[row2][column2-1]=null;
            if(ChessBoard[row2][column2] instanceof BlackKing )
            {
                BlackKing.castle=1;
            }
            else
            {
                WhiteKing.castle=1;
            }
                  
                
        }
        else if(ret.equals("right castle")||ret.equals("rcc"))
        {
            ChessBoard[row2][column2-1]=ChessBoard[row2][column2+2];
            ChessBoard[row2][column2+2]=null;
            if(ChessBoard[row2][column2] instanceof BlackKing )
            {
                BlackKing.castle=1;
            }
            else
            {
                WhiteKing.castle=1;
            }
        }
        if(ret.equals("revive")||ret.equals("rcheck"))
        {
            ChessBoard[row2][column2]=queen;
        }
        
        
        
        
        
        if(ret=="true" &&ChessBoard[row2][column2] instanceof BlackKing )
        {
            BlackKing.castle=1;
        }
        else if(ret=="true" &&ChessBoard[row2][column2] instanceof WhiteKing )    
        {
            WhiteKing.castle=1;
        }
      
         if(ret=="true"&&(row1==0&&column1==0))                     
         {
            BlackRook.leftCastle=1;
         }
         if(ret=="true"&&(row1==0&&column1==7))                     
         {
            BlackRook.rightCastle=1;
         }
         
        if(ret=="true"&&(row1==7&&column1==0))                     
        {
            WhiteRook.leftCastle=1;
        }
        if(ret=="true"&&(row1==7&&column1==7))                     
        {
            WhiteRook.rightCastle=1;
        }   
        
        if(pieceDelete!=null)
        {
            Piece dPiece[]=new Piece[16];
            if(pieceDelete instanceof BlackPiece)
            {
                dPiece=game.getBlackPieceDeleted();
                dPiece[game.getBlackDeletedCount()]=pieceDelete;
                game.setBlackDeletedCount(game.getBlackDeletedCount()+1);
                game.setBlackPieceDeleted(dPiece);
            }
            else if(pieceDelete instanceof WhitePiece)
            {
                dPiece=game.getWhitePieceDeleted();
                dPiece[game.getWhiteDeletedCount()]=pieceDelete;
                game.setWhiteDeletedCount(game.getWhiteDeletedCount()+1);
                game.setWhitePieceDeleted(dPiece);
            }
            
        }
        String checkMate="";
        if(BlackKing.check==1&&(ret.equals("check")||ret.equals("lcc")||ret.equals("rcc")||ret.equals("rcheck")))
        {
                    checkMate=BlackPiece.validateCheckmate(Piece.rowChecked, Piece.columnChecked,ChessBoard);
                    if(checkMate.equals("checkmate"))
                    {
                        ret=checkMate;
                        new datalayer.DALChess().endGame(gameId,game.getWhite().getPlayerNo());
                    }
        }
        else if(WhiteKing.check==1&&(ret.equals("check")||ret.equals("lcc")||ret.equals("rcc")||ret.equals("rcheck")))
        {
                checkMate=WhitePiece.validateCheckmate(Piece.rowChecked, Piece.columnChecked, ChessBoard);
                if(checkMate.equals("checkmate"))
                {
                    ret=checkMate;
                    new datalayer.DALChess().endGame(gameId,game.getBlack().getPlayerNo());

                }
        }
        if(!(ret.equals("Illegal Move"))) 
        {
            game.setLastMove(row1+""+column1+""+row2+""+column2);
            game.setMoveStatus(ret);
            if(game.getCurrentPlayer()==game.getBlack())
                game.setCurrentPlayer(game.getWhite());
            else
                game.setCurrentPlayer(game.getBlack());
        
            if(ret.equals("checkmate"))
            {
                game.setCurrentPlayer(null);
            }
        }        
            
        game.setChessBoard(ChessBoard);
        new datalayer.DALChess().updateGame(game, gameId);
        
    }
    
%>
<%=ret%>