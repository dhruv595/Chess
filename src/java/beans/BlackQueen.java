package beans;

public class BlackQueen extends BlackPiece {
    
    static final long serialVersionUID = 5L;
     public String  validateMove(int rowInitial,int columnInitial ,int rowFinal ,int columnFinal ,Piece chessBoard[][])
     {
       String ret= "false";
       
       if(chessBoard[rowFinal][columnFinal] instanceof BlackPiece)
       {
           return "false";
       }
       if(rowInitial==rowFinal||columnInitial==columnFinal)
       {
           ret=new BlackRook().validateMove(rowInitial, columnInitial, rowFinal, columnFinal, chessBoard);
           return ret;
       }
       else if(java.lang.Math.abs(rowFinal-rowInitial)==java.lang.Math.abs(columnInitial-columnFinal))
       {
           ret=new BlackBishop().validateMove(rowInitial, columnInitial, rowFinal, columnFinal, chessBoard);
           return ret;
       }
       
        
       return ret;
    }
     
     
     
      public String validateCheck(int rowFinal,int columnFinal, beans.Game game)
    {
        String ret="true";
        
        ret=new beans.BlackRook().validateCheck(rowFinal, columnFinal, game);
        
        if(!(ret.equals("check")))
        {
            ret= new beans.BlackBishop().validateCheck(rowFinal, columnFinal, game);
        }
        return ret;
    }
    
}
