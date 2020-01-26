
package beans;

public class WhitePawn extends WhitePiece
{   static final long serialVersionUID = 14L;
    public String validateMove(int rowInitial,int columnInitial ,int rowFinal ,int columnFinal ,Piece chessBoard[][] )
    {
       String ret= "false";
       if(chessBoard[rowFinal][columnFinal]==null)
       {
            if(rowInitial==rowFinal+1&&columnInitial==columnFinal)
            {
                ret= "true";
            }
       }//one Step
       
       try
       {
            if(chessBoard[rowFinal][columnFinal]==null && chessBoard[rowFinal+1][columnFinal]==null )
            {
                 if(rowInitial==6 &&rowFinal==rowInitial-2 &&columnInitial==columnFinal)
                 {
                     ret= "true";
                 }
            }//two step
       }
       catch(Exception ex)
       {
           
       }
       
       if(chessBoard[rowFinal][columnFinal] instanceof BlackPiece)  
       {
           if(rowInitial==rowFinal+1&&(columnInitial==columnFinal-1||columnInitial==columnFinal+1))
           {
               ret= "true";
           }
               
       }//diagonally
       if(rowFinal==0&&ret=="true")
       {
           ret="revive";
       }
       return ret;
    }
    public String validateCheck(int rowFinal,int ColumnFinal,beans.Game game)
    {
        String ret="true";
        Object chessBoard[][]=game.getChessBoard();
        try
        {
            if(chessBoard[rowFinal-1][ColumnFinal+1] instanceof BlackKing )
            {
                ret="check";
            }
        }
        catch(Exception ex)
        {
            
        }
       try
        {
            if(chessBoard[rowFinal-1][ColumnFinal-1] instanceof BlackKing )
            {
                ret="check";
            }   
        }
        catch(Exception ex)
        {
            
        }
        
        return ret;
    }
    
}
