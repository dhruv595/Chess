package beans;

public class WhiteQueen extends WhitePiece {
    static final long serialVersionUID = 17L;
  public String  validateMove(int rowInitial,int columnInitial ,int rowFinal ,int columnFinal ,Piece chessBoard[][] )
    {
       String ret= "false";
       
       if(chessBoard[rowFinal][columnFinal] instanceof WhitePiece)
       {
           return "false";
       }
       if(rowInitial==rowFinal||columnInitial==columnFinal)
       {
           ret=new WhiteRook().validateMove(rowInitial, columnInitial, rowFinal, columnFinal, chessBoard);
           return ret;
       }
       else if(java.lang.Math.abs(rowFinal-rowInitial)==java.lang.Math.abs(columnInitial-columnFinal))
       {
           ret=new WhiteBishop().validateMove(rowInitial, columnInitial, rowFinal, columnFinal, chessBoard);
           return ret;
       }
       
        
       return ret;
    }
   
    public String validateCheck(int rowFinal,int columnFinal, beans.Game game)
    {
        String ret="true";
        
        ret=new beans.WhiteRook().validateCheck(rowFinal, columnFinal, game);
        
        if(!(ret.equals("check")))
        {
            ret= new beans.WhiteBishop().validateCheck(rowFinal, columnFinal, game);
        }
        return ret;
    }
}
