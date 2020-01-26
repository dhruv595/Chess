package beans;
public class BlackBishop extends BlackPiece {
static final long serialVersionUID = 20L;    
    public String  validateMove(int rowInitial,int columnInitial ,int rowFinal ,int columnFinal ,Piece chessBoard[][])
    {
        
       String ret= "true";
        
       
       if(chessBoard[rowFinal][columnFinal] instanceof BlackPiece)
       {
           return "false";
       }
       if(!(java.lang.Math.abs(rowFinal-rowInitial)==java.lang.Math.abs(columnInitial-columnFinal)))
       {
           return "false";
       }
       if(rowInitial==rowFinal || columnInitial==columnFinal)
       {
           return "false";
       }
       else
       {
           if(columnInitial<columnFinal)
            {
                if(rowInitial<rowFinal)
                {
                    
                    for(int i=rowInitial+1,j=columnInitial+1;i<rowFinal;i++,j++)
                    {
                        if(chessBoard[i][j]!=null)
                        {
                            return "false";

                        }    
                        else
                        {
                            ret="true";
                        }

                    }                  
                    
                }//down right move
                else
                {
                    for(int i=rowInitial-1,j=columnInitial+1;i>rowFinal;i--,j++)
                    {
                        if(chessBoard[i][j]!=null)
                        {
                            return "false";

                        }    
                        else
                        {
                            ret="true";
                        }

                    }
                }//up right move
            }
           else
           {
               if(rowInitial<rowFinal)
               {
                   for(int i=rowInitial+1,j=columnInitial-1; i<rowFinal ;i++,j--)
                    {
                        if(chessBoard[i][j]!=null)
                        {
                            return "false";

                        }    
                        else
                        {
                            ret="true";
                        }

                    }
               } // down left
               else
               {
               
                   for(int i=rowInitial-1,j=columnInitial-1; i>rowFinal ;i--,j--)
                    {
                        if(chessBoard[i][j]!=null)
                        {
                            return "false";

                        }    
                        else
                        {
                            ret="true";
                        }

                    }
               }//up left
           
           }
       }
        
       return ret;
    }

    public String validateCheck(int rowFinal,int columnFinal, beans.Game game)
    {
        String ret="true";
        Object chessBoard[][]=game.getChessBoard();
        
        for(int i=rowFinal+1, j=columnFinal+1;i<=7;i++,j++)
        {
            if(j>7)
            {
                break;
            }
            if(chessBoard[i][j] instanceof WhiteKing)
            {
                return "check";
            }
            if(chessBoard[i][j]!=null)
            {
                break;
            }
        }//down right move
        
        for(int i=rowFinal-1, j=columnFinal-1;i>=0;i--,j--)
        {
            if(j<0)
            {
                break;
            }
            if(chessBoard[i][j] instanceof WhiteKing)
            {
                return "check";
            }
            if(chessBoard[i][j]!=null)
            {
                break;
            }
        }//up left  move
        
        for(int i=rowFinal+1,j=columnFinal-1;i<=7;i++,j--)
        {
            if(j<0)
            {
                break;
            }
            if(chessBoard[i][j] instanceof WhiteKing)
            {
                return "check";
            }
            if(chessBoard[i][j]!=null)
            {
                break;
            }
        }//down left move
       
         for(int i=rowFinal-1,j=columnFinal+1;i>=0;i--,j++)
        {
            if(j>7)
            {
                break;
            }
            if(chessBoard[i][j] instanceof WhiteKing)
            {
                return "check";
            }
            if(chessBoard[i][j]!=null)
            {
                break;
            }
        }//up right
        
        return ret;
    }

}
