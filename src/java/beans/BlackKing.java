package beans;
public class BlackKing extends BlackPiece{
    
    static   public int castle;
    public  static  int check;
    
    public BlackKing()
    {
        
        castle=BlackKing.castle;
        check=BlackKing.check;
    }
    
    static final long serialVersionUID = 1L;

    public String validateMove(int rowInitial,int columnInitial ,int rowFinal ,int columnFinal ,Piece chessBoard[][] )
    {
     String ret= "false";

        
        try
        {
            if(BlackKing.castle==0&&rowInitial==rowFinal&&columnInitial==columnFinal+2&&BlackRook.leftCastle==0)
            {
                if(chessBoard[rowFinal][columnFinal]==null&&chessBoard[rowFinal][columnFinal+1]==null)
                return "left castle";
            }
            if(BlackKing.castle==0&&rowInitial==rowFinal&&columnInitial==columnFinal-2&&BlackRook.rightCastle==0)
            {
                if(chessBoard[rowFinal][columnFinal]==null&&chessBoard[rowFinal][columnFinal-1]==null&&chessBoard[rowFinal][columnFinal+1]==null)
                return "right castle";
            }
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            if(chessBoard[rowFinal][columnFinal] instanceof BlackPiece)
            {
              return "false";
            }
        }
        catch(Exception ex)
        {
            
        }
        
        try
        {
            if(Math.abs(rowFinal-rowInitial)==1&&Math.abs(columnInitial-columnFinal)==1)
            {
                return "true";
            }      
        }
        catch(Exception ex)
        {
            
        }
         
        try
        {
            if((Math.abs(rowFinal-rowInitial)==1&&columnInitial==columnFinal)||(Math.abs(columnInitial-columnFinal)==1&&rowInitial==rowFinal))
            {
                return "true";
            }
        }
        catch(Exception ex)
        {
            
        }
        
        
           
    
    
    return ret;
    }
    public String validateCheck(int rowFinal,int columnFinal, beans.Game game)
    {
        String ret="true";
        
        
        
        
        return ret;
    }
    
    public  String saveKing(Object [][] ChessBoard)
     {
         String ret="safe";
         int row=0;
         int column=0;
        
         
         for(int i=0;i<=7;i++)
         {
             for(int j=0;j<=7;j++)
             {
                 if(ChessBoard[i][j] instanceof BlackKing)
                 {
                     row=i;
                     column=j;
                     break;
                 }
             }
         }
         
        ret=pawnCheck(row,column,ChessBoard);
        if(ret.equals("safe"))
         {
             ret=rookCheck(row,column,ChessBoard);
            
         }  
        if(ret.equals("safe"))
         {
             ret=bishopCheck(row,column,ChessBoard);
            
         }  
        if(ret.equals("safe"))
         {
             ret=knightCheck(row,column,ChessBoard);
            
         }
        if(ret.equals("safe"))
         {
             ret=kingCheck(row,column,ChessBoard);
            
         }
         return ret;
     }
     public  String pawnCheck(int row,int column,Object[][] ChessBoard)
     {
         String ret="safe";
         try
         {
            if(ChessBoard[row+1][column+1] instanceof WhitePawn||ChessBoard[row+1][column-1] instanceof WhitePawn)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }
         return ret;
     }
     public String rookCheck(int row,int column,Object[][] chessBoard)
     {
         String ret="safe";
         
        try
        {
            for(int i=row+1;i<=7;i++)
            {
                if(chessBoard[i][column] instanceof WhiteRook ||chessBoard[i][column] instanceof WhiteQueen)
                {
                 return "Illegal Move";
                }
                if(chessBoard[i][column] !=null)
                {
                  break;
                }
            }//downmove
         }
         catch(Exception ex)
         {
             
         }
         
        try
        {
            for(int i=row-1;i>=0;i--)
            {
                if(chessBoard[i][column] instanceof WhiteRook ||chessBoard[i][column] instanceof WhiteQueen)
                {
                    return "Illegal Move";
                }
                if(chessBoard[i][column] !=null)
                {
                    break;
                }
            }//upmove move
        }
        catch(Exception ex)
        {
            
        }
         
        try
        {
            for(int i=column+1;i<=7;i++)
            {
                 if(chessBoard[row][i] instanceof WhiteRook || chessBoard[row][i] instanceof WhiteQueen)
                {
                     return "Illegal Move";
                }
                if(chessBoard[row][i]!=null)
                {
                  break;
                }
            }//rigth move
        }
        catch(Exception ex)
        {
            
        }
        
        try
        {
            for(int i=column-1;i>=0;i--)
            {
                if(chessBoard[row][i] instanceof WhiteRook || chessBoard[row][i] instanceof WhiteQueen)
                {
                     return "Illegal Move";
                }
                if(chessBoard[row][i]!=null)
                {
                     break;
                }
             }//left
        }
        catch(Exception ex)
        {
            
        }
         return ret;
     }
     
     public String bishopCheck(int row,int column,Object[][] chessBoard)
     {
         String ret="safe";
        try
        {
            for(int i=row+1,j=column+1;i<=7;i++,j++)
            {
                if(chessBoard[i][j] instanceof WhiteBishop ||chessBoard[i][j] instanceof WhiteQueen)
                {
                 return "Illegal Move";
                }
                if(chessBoard[i][j] !=null)
                {
                  break;
                }
            }//down rigth move
         }
         catch(Exception ex)
         {
             
         }
        
        try
        {
            for(int i=row-1,j=column-1;i>=0;i--,j--)
            {
                if(chessBoard[i][j] instanceof WhiteBishop ||chessBoard[i][j] instanceof WhiteQueen)
                {
                    return "Illegal Move";
                }
                if(chessBoard[i][j] !=null)
                {
                    break;
                }
            }//up left move
        }
        catch(Exception ex)
        {
            
        } 
        
        try
        {
            for(int i=row-1,j=column+1;i>=0;i--,j++)
            {
                if(chessBoard[i][j] instanceof WhiteBishop ||chessBoard[i][j] instanceof WhiteQueen)
                {
                    return "Illegal Move";
                }
                if(chessBoard[i][j] !=null)
                {
                    break;
                }
            }//up rigth move
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            for(int i=row+1,j=column-1;i<=7;i++,j--)
            {
                if(chessBoard[i][j] instanceof WhiteBishop ||chessBoard[i][j] instanceof WhiteQueen)
                {
                 return "Illegal Move";
                }
                if(chessBoard[i][j] !=null)
                {
                  break;
                }
            }//down left move
         }
         catch(Exception ex)
         {
             
         }
        return ret;
        
        
     }
     
     public  String kingCheck(int row,int column,Object[][] ChessBoard)
     {
         String ret="safe";
         try
         {
            if(ChessBoard[row-1][column] instanceof WhiteKing)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }//up
         
         try
         {
            if(ChessBoard[row-1][column-1] instanceof WhiteKing)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }//up left
         
         try
         {
            if(ChessBoard[row-1][column+1] instanceof WhiteKing)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }//up rigth
         
         try
         {
            if(ChessBoard[row+1][column] instanceof WhiteKing)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }//down
         
         try
         {
            if(ChessBoard[row+1][column-1] instanceof WhiteKing)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }//down left
         
         try
         {
            if(ChessBoard[row+1][column+1] instanceof WhiteKing)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }//down rigth
         
         try
         {
            if(ChessBoard[row][column-1] instanceof WhiteKing)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }//left
           
         try
         {
            if(ChessBoard[row][column+1] instanceof WhiteKing)
            {
                ret="Illegal Move";
            }    
         }
         catch(Exception ex)
         {
             
         }//right
         return ret;
     }
    public String knightCheck(int row,int column,Object[][] chessBoard)
    {
        String ret="safe";
        try
        {
            if(chessBoard[row-1][column-2] instanceof WhiteKnight)
            {
                return "Illegal Move";
            }
        }
        catch(Exception ex)
        {
            
        }//right up
        
        try
        {
            if(chessBoard[row-1][column+2] instanceof WhiteKnight)
            {
                return "Illegal Move";
            }
        }
        catch(Exception ex)
        {
            
        }//left up
        
        try
        {
            if(chessBoard[row+1][column-2] instanceof WhiteKnight)
            {
                return "Illegal Move";
            }
        }
        catch(Exception ex)
        {
            
        }//left down
        
        try
        {
            if(chessBoard[row+1][column+2] instanceof WhiteKnight)
            {
                return "Illegal Move";
            }
        }
        catch(Exception ex)
        {
            
        }//right down
        
        try
        {
            if(chessBoard[row-2][column-1] instanceof WhiteKnight)
            {
                return "Illegal Move";
            }
        }
        catch(Exception ex)
        {
            
        }// up left
        
        try
        {
            if(chessBoard[row-2][column+1] instanceof WhiteKnight)
            {
                return "Illegal Move";
            }
        }
        catch(Exception ex)
        {
            
        }//up right
        
        try
        {
            if(chessBoard[row+2][column-1] instanceof WhiteKnight)
            {
                return "Illegal Move";
            }
        }
        catch(Exception ex)
        {
            
        }//down left
        
        try
        {
            if(chessBoard[row+2][column+1] instanceof WhiteKnight)
            {
                return "Illegal Move";
            }
        }
        catch(Exception ex)
        {
            
        }//down right
        return ret;
    }
    
    public String isKingCheck(Object[][] ChessBoard)
     {
         String ret="true";
         int row=0;
         int column=0;
         
         
         
         for(int i=0;i<=7;i++)
         {
             for(int j=0;j<=7;j++)
             {
                 if(ChessBoard[i][j] instanceof WhiteKing)
                 {
                     row=i;
                     column=j;
                     break;
                 }
             }
         }
         
        ret=isPawnCheck(row,column,ChessBoard);
        if(ret.equals("true"))
        {   
            ret=isRookCheck(row,column,ChessBoard);
        }
        if(ret.equals("true"))
        {
             ret=isBishopCheck(row,column,ChessBoard);
            
        }
        if(ret.equals("true"))
        {
             ret=isKnightCheck(row,column,ChessBoard);
            
        }
         return ret;
     }
     public static String isPawnCheck(int row,int column,Object[][] ChessBoard)
     {
         String ret="true";
         try
         {
            if(ChessBoard[row-1][column-1] instanceof BlackPawn)
            {
                ret="check";
                Piece.rowChecked=row-1;
                Piece.columnChecked=column-1;
            }    
         }
         catch(Exception ex)
         {
             
         }
         try
         {
            if(ChessBoard[row-1][column+1] instanceof BlackPawn)
            {
                ret="check";
                Piece.rowChecked=row-1;
                Piece.columnChecked=column+1;
            }    
         }
         catch(Exception ex)
         {
             
         }
         return ret;
     }
     
     public String isRookCheck(int row,int column,Object[][] chessBoard)
     {
         String ret="true";
         try
         {
            for(int i=row+1;i<=7;i++)
            {
                if(chessBoard[i][column] instanceof BlackRook ||chessBoard[i][column] instanceof BlackQueen)
                {
                    Piece.rowChecked=i;
                    Piece.columnChecked=column;
                    return "check";
                    
                }
                if(chessBoard[i][column] !=null)
                {
                    break;
                }
            }//downmove
         }
         catch(Exception ex)
         {
             
         }
         
         try
         {
            for(int i=row-1;i>=0;i--)
            {
                if(chessBoard[i][column] instanceof BlackRook ||chessBoard[i][column] instanceof BlackQueen)
                {
                    Piece.rowChecked=i;
                    Piece.columnChecked=column; 
                    return "check";
                }
                if(chessBoard[i][column] !=null)
                {
                    break;
                }
            }//upmove move
         }
         catch(Exception ex)
         {
             
         }
         
        try
        {
            for(int i=column+1;i<=7;i++)
            {
                if(chessBoard[row][i] instanceof BlackRook || chessBoard[row][i] instanceof BlackQueen)
                {
                    Piece.rowChecked=row;
                    Piece.columnChecked=i;
                    return "check";
                }
                if(chessBoard[row][i]!=null)
                {
                    break;
                }
            }//rigth move
        }
        catch(Exception ex)
        {
             
        }
         
        try
        {
            for(int i=column-1;i>=0;i--)
            {
                if(chessBoard[row][i] instanceof BlackRook || chessBoard[row][i] instanceof BlackQueen)
                {
                    Piece.rowChecked=row;
                    Piece.columnChecked=i;
                    return "check";
                }
                if(chessBoard[row][i]!=null)
                {
                    break;
                }
            }//left
        }
        catch(Exception ex)
        {
            
        }
        
         return ret;
     }

     public String isBishopCheck(int row,int column,Object[][] chessBoard)
     {
         String ret="true";
        try
        {
            for(int i=row+1,j=column+1;i<=7;i++,j++)
            {
                if(chessBoard[i][j] instanceof BlackBishop ||chessBoard[i][j] instanceof BlackQueen)
                {
                    
                    Piece.rowChecked=i;
                    Piece.columnChecked=j;
                    return "check";
                }
                if(chessBoard[i][j] !=null)
                {
                  break;
                }
            }//down rigth move
         }
         catch(Exception ex)
         {
             
         }
        
        try
        {
            for(int i=row-1,j=column-1;i>=0;i--,j--)
            {
                if(chessBoard[i][j] instanceof BlackBishop ||chessBoard[i][j] instanceof BlackQueen)
                {                    
                    Piece.rowChecked=i;
                    Piece.columnChecked=j;
                    return "check";
                }
                if(chessBoard[i][j] !=null)
                {
                    break;
                }
            }//up left move
        }
        catch(Exception ex)
        {
            
        } 
        
        try
        {
            for(int i=row-1,j=column+1;i>=0;i--,j++)
            {
                if(chessBoard[i][j] instanceof BlackBishop ||chessBoard[i][j] instanceof BlackQueen)
                {
                    
                    Piece.rowChecked=i;
                    Piece.columnChecked=j;
                    return "check";
                }
                if(chessBoard[i][j] !=null)
                {
                    break;
                }
            }//up rigth move
        }
        catch(Exception ex)
        {
            
        }
        try
        {
            for(int i=row+1,j=column-1;i<=7;i++,j--)
            {
                if(chessBoard[i][j] instanceof BlackBishop ||chessBoard[i][j] instanceof BlackQueen)
                {
                    
                    Piece.rowChecked=i;
                    Piece.columnChecked=j;
                    return "check";
                }
                if(chessBoard[i][j] !=null)
                {
                  break;
                }
            }//down left move
         }
         catch(Exception ex)
         {
             
         }
        return ret;
        
        
     }
     
     public String isKnightCheck(int row,int column,Object[][] chessBoard)
     {
        String ret="true";
        try
        {
            if(chessBoard[row-1][column-2] instanceof BlackKnight)
            {
                    Piece.rowChecked=row-1;
                    Piece.columnChecked=column-2;
                    return "check";
            }
        }
        catch(Exception ex)
        {
            
        }//right up
        
        try
        {
            if(chessBoard[row-1][column+2] instanceof BlackKnight)
            {
                Piece.rowChecked=row-1;
                Piece.columnChecked=column+2;
                return "check";
            }
        }
        catch(Exception ex)
        {
            
        }//left up
        
        try
        {
            if(chessBoard[row+1][column-2] instanceof BlackKnight)
            {
                Piece.rowChecked=row+1;
                Piece.columnChecked=column-2;
                return "check";
            }
        }
        catch(Exception ex)
        {
            
        }//left down
        
        try
        {
            if(chessBoard[row+1][column+2] instanceof BlackKnight)
            {
                Piece.rowChecked=row+1;
                Piece.columnChecked=column+2;
                return "check";
            }
        }
        catch(Exception ex)
        {
            
        }//right down
        
        try
        {
            if(chessBoard[row-2][column-1] instanceof BlackKnight)
            {
                Piece.rowChecked=row-2;
                Piece.columnChecked=column-1;
                return "check";
            }
        }
        catch(Exception ex)
        {
            
        }// up left
        
        try
        {
            if(chessBoard[row-2][column+1] instanceof BlackKnight)
            {
                Piece.rowChecked=row-2;
                Piece.columnChecked=column+1;
                return "check";
            }
        }
        catch(Exception ex)
        {
            
        }//up right
        
        try
        {
            if(chessBoard[row+2][column-1] instanceof BlackKnight)
            {
               Piece.rowChecked=row+2;
               Piece.columnChecked=column-1;
               return "check";
            }
        }
        catch(Exception ex)
        {
            
        }//down left
        
        try
        {
            if(chessBoard[row+2][column+1] instanceof BlackKnight)
            {
               Piece.rowChecked=row+2;
               Piece.columnChecked=column+1;
               return "check";
            }
        }
        catch(Exception ex)
        {
            
        }//down right
        return ret;
    }

     
    
}