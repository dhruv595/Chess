package beans;
public class BlackKnight extends BlackPiece {
    
    static final long serialVersionUID = 2L;

     public String  validateMove(int rowInitial,int columnInitial ,int rowFinal ,int columnFinal ,Piece chessBoard[][] )
    {
       String ret="false";
       
       
       if(chessBoard[rowFinal][columnFinal] instanceof BlackPiece)
       {
           return "false";
       }
       if(rowInitial==rowFinal+2&&(columnInitial==columnFinal+1||columnInitial==columnFinal-1)) 
       {
           return "true";
       }//downmove
       if(rowInitial==rowFinal-2&&(columnInitial==columnFinal+1||columnInitial==columnFinal-1)) 
       {
           return "true";
       }//up move
       if(columnInitial==columnFinal+2&&(rowInitial==rowFinal+1||rowInitial==rowFinal-1))
       {
           return "true";
       }//right move
       if(columnInitial==columnFinal-2&&(rowInitial==rowFinal+1||rowInitial==rowFinal-1))
       {
           return "true";
       }//left 
       return ret;
    }

      public String validateCheck(int rowFinal,int columnFinal, beans.Game game)
      {
        String ret="true";
        Object chessBoard[][]=game.getChessBoard();
        try
        {
            if(chessBoard[rowFinal-1][columnFinal-2] instanceof WhiteKing)
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }//right up
        
        try
        {
            if(chessBoard[rowFinal-1][columnFinal+2] instanceof WhiteKing)
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }//left up
        
        try
        {
            if(chessBoard[rowFinal+1][columnFinal-2] instanceof WhiteKing)
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }//left down
        
        try
        {
            if(chessBoard[rowFinal+1][columnFinal+2] instanceof WhiteKing)
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }//right down
        
        try
        {
            if(chessBoard[rowFinal-2][columnFinal-1] instanceof WhiteKing)
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }// up left
        
        try
        {
            if(chessBoard[rowFinal-2][columnFinal+1] instanceof WhiteKing)
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }//up right
        
        try
        {
            if(chessBoard[rowFinal+2][columnFinal-1] instanceof WhiteKing)
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }//down left
        
        try
        {
            if(chessBoard[rowFinal+2][columnFinal+1] instanceof WhiteKing)
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }//down right
        return ret;
    }

}
