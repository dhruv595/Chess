package beans;

public class BlackRook extends BlackPiece {
    
     static  public int leftCastle;
     static  public int rightCastle;
     static final long serialVersionUID = 6L;
     public BlackRook()
     {
         leftCastle=BlackRook.leftCastle;
         rightCastle=BlackRook.rightCastle;
     }
     public String  validateMove(int rowInitial,int columnInitial ,int rowFinal ,int columnFinal ,Piece chessBoard[][] )
      {
       String ret= "false";
        if(chessBoard[rowFinal][columnFinal] instanceof BlackPiece)
        {
            return "false";
        }
        if(rowInitial==rowFinal)
        {   
            if(columnInitial<columnFinal)
            {
                for(int i=columnInitial+1;i<columnFinal;i++)
                {
                    if(chessBoard[rowInitial][i]!=null)
                    {
                        return "false";
                       
                    }    
                    
                    
                }
                ret= "true";
            }//right move
            else
            {
                for(int i=columnInitial-1;i>columnFinal;i--)
                {
                    if(chessBoard[rowInitial][i]!=null)
                    {
                        return "false";
                       
                    }    
                    
                    
                }
                ret="true";
            }//left move
        }    
        else if(columnInitial==columnFinal)
        {
            if(rowInitial<rowFinal)
            {
                for(int i=rowInitial+1;i<rowFinal;i++)
                {
                    if(chessBoard[i][columnInitial]!=null)
                    {
                        return "false";
                        
                    }
                    
                }
                ret= "true";
            }//down move
            else
            {
                for(int i=rowInitial-1;i>rowFinal;i--)
                {
                    if(chessBoard[i][columnInitial]!=null)
                    {
                        return "false";
                        
                    }
                    
                }
                ret="true";
            }//upmove
        }
       
                
        
        
       return ret;
    }

    public String validateCheck(int rowFinal,int columnFinal, beans.Game game)
    {
        String ret="true";
        Object chessBoard[][]=game.getChessBoard();
        
        for(int i=rowFinal+1;i<=7;i++)
        {
            if(chessBoard[i][columnFinal] instanceof WhiteKing)
            {
                return "check";
            }
            if(chessBoard[i][columnFinal]!=null)
            {
                break;
            }
        }//down move
        
        for(int i=rowFinal-1;i>=0;i--)
        {
            if(chessBoard[i][columnFinal] instanceof WhiteKing)
            {
                return "check";
            }
            if(chessBoard[i][columnFinal]!=null)
            {
                break;
            }
        }//upmove move
        
        for(int i=columnFinal+1;i<=7;i++)
        {
            if(chessBoard[rowFinal][i] instanceof WhiteKing)
            {
                return "check";
            }
            if(chessBoard[rowFinal][i]!=null)
            {
                break;
            }
        }//rigth move
       
         for(int i=columnFinal-1;i>=0;i--)
        {
            if(chessBoard[rowFinal][i] instanceof WhiteKing)
            {
                return "check";
            }
            if(chessBoard[rowFinal][i]!=null)
            {
                break;
            }
        }//left move
        
        return ret;
    }

}
