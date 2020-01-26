package beans;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class BlackPiece  extends Piece{
    
    static final long serialVersionUID = 4L;
        
     
     public abstract String  validateMove(int row1,int column1 ,int row2 ,int column2 ,Piece chessBoard[][] );
     
     public abstract String validateCheck(int row2,int column2,beans.Game game);

     public static String validateCheckmate(int row,int column ,Piece chessBoard[][])
     {
         String ret="safe";
         
         int kingRow=-1;
         int kingColumn=-1;
            
         for(int i=0;i<=7;i++)
         {
             for(int j=0;j<=7;j++)
             {
                 if(chessBoard[i][j] instanceof BlackKing)
                 {
                     kingRow=i;
                     kingColumn=j;
                     break;
                 }
             }
         }//finding king
         
         ret=kingMoves(kingRow, kingColumn, chessBoard);
         if(ret.equals("safe"))
         {
             return "safe";
         }
         ret=pieceMoves(row,column,chessBoard);
         if(ret.equals("safe"))
         {
             return "safe";
         }
         
         if(!(chessBoard[row][column] instanceof WhiteKnight))
         {
             ret=pieceBetween(row, column, kingRow, kingColumn, chessBoard);
         }
         if(ret.equals("safe"))
         {
             return "safe";
         }
         ret="checkmate";
        return ret;
     }
     
     public static String kingMoves(int kingRow,int kingColumn, Piece chessBoard[][])
     {
         String ret="";
          
             BlackKing blackKing= new BlackKing();
             Piece piece;
             try
             {
                ret= blackKing.validateMove(kingRow, kingColumn, kingRow+1, kingColumn, chessBoard);
                if(ret.equals("true"))
                {
                    piece=chessBoard[kingRow+1][kingColumn];
                    chessBoard[kingRow+1][kingColumn]=chessBoard[kingRow][kingColumn];
                    chessBoard[kingRow][kingColumn]=null;

                    ret=blackKing.saveKing(chessBoard);
                    chessBoard[kingRow][kingColumn]=chessBoard[kingRow+1][kingColumn];
                    chessBoard[kingRow+1][kingColumn]=piece;        
                    if(ret.equals("safe"))
                        return ret;


                 }//upmove
             }
             catch(Exception ex)
             {
                 
             }
             
             try
             {
                ret= blackKing.validateMove(kingRow, kingColumn, kingRow-1, kingColumn, chessBoard);
                if(ret.equals("true"))
                {
                    piece=chessBoard[kingRow-1][kingColumn];
                    chessBoard[kingRow-1][kingColumn]=chessBoard[kingRow][kingColumn];
                    chessBoard[kingRow][kingColumn]=null;

                    ret=blackKing.saveKing(chessBoard);
                    chessBoard[kingRow][kingColumn]=chessBoard[kingRow-1][kingColumn];
                    chessBoard[kingRow-1][kingColumn]=piece;        
                    if(ret.equals("safe"))
                        return ret;


                 }//downmove
             }
             catch(Exception ex)
             {
                 
             }
             try
             {
                ret= blackKing.validateMove(kingRow, kingColumn, kingRow, kingColumn+1, chessBoard);
                if(ret.equals("true"))
                {
                    piece=chessBoard[kingRow][kingColumn+1];
                    chessBoard[kingRow][kingColumn+1]=chessBoard[kingRow][kingColumn];
                    chessBoard[kingRow][kingColumn]=null;

                    ret=blackKing.saveKing(chessBoard);
                    chessBoard[kingRow][kingColumn]=chessBoard[kingRow][kingColumn+1];
                    chessBoard[kingRow][kingColumn+1]=piece;        
                    if(ret.equals("safe"))
                        return ret;


                 }//right move
             }
             catch(Exception ex)
             {
                 
             }
             
             try
             {
                ret= blackKing.validateMove(kingRow, kingColumn, kingRow, kingColumn-1, chessBoard);
                if(ret.equals("true"))
                {
                    piece=chessBoard[kingRow][kingColumn-1];
                    chessBoard[kingRow][kingColumn-1]=chessBoard[kingRow][kingColumn];
                    chessBoard[kingRow][kingColumn]=null;

                    ret=blackKing.saveKing(chessBoard);
                    chessBoard[kingRow][kingColumn]=chessBoard[kingRow][kingColumn-1];
                    chessBoard[kingRow][kingColumn-1]=piece;        
                    if(ret.equals("safe"))
                        return ret;


                 }//left move
             }
             catch(Exception ex)
             {
                 
             }
             
             try
             {
                ret= blackKing.validateMove(kingRow, kingColumn, kingRow+1, kingColumn+1, chessBoard);
                if(ret.equals("true"))
                {
                    piece=chessBoard[kingRow+1][kingColumn+1];
                    chessBoard[kingRow+1][kingColumn+1]=chessBoard[kingRow][kingColumn];
                    chessBoard[kingRow][kingColumn]=null;

                    ret=blackKing.saveKing(chessBoard);
                    chessBoard[kingRow][kingColumn]=chessBoard[kingRow+1][kingColumn+1];
                    chessBoard[kingRow+1][kingColumn+1]=piece;        
                    if(ret.equals("safe"))
                        return ret;


                 }//up right
             }
             catch(Exception ex)
             {
                 
             }
             
             try
             {
                ret= blackKing.validateMove(kingRow, kingColumn, kingRow+1, kingColumn-1, chessBoard);
                if(ret.equals("true"))
                {
                    piece=chessBoard[kingRow+1][kingColumn-1];
                    chessBoard[kingRow+1][kingColumn-1]=chessBoard[kingRow][kingColumn];
                    chessBoard[kingRow][kingColumn]=null;

                    ret=blackKing.saveKing(chessBoard);
                    chessBoard[kingRow][kingColumn]=chessBoard[kingRow+1][kingColumn-1];
                    chessBoard[kingRow+1][kingColumn-1]=piece;        
                    if(ret.equals("safe"))
                        return ret;


                 }//up left
             }
             catch(Exception ex)
             {
                 
             }
             
             try
             {
                ret= blackKing.validateMove(kingRow, kingColumn, kingRow-1, kingColumn+1, chessBoard);
                if(ret.equals("true"))
                {
                    piece=chessBoard[kingRow-1][kingColumn+1];
                    chessBoard[kingRow-1][kingColumn+1]=chessBoard[kingRow][kingColumn];
                    chessBoard[kingRow][kingColumn]=null;

                    ret=blackKing.saveKing(chessBoard);
                    chessBoard[kingRow][kingColumn]=chessBoard[kingRow-1][kingColumn+1];
                    chessBoard[kingRow-1][kingColumn+1]=piece;        
                    if(ret.equals("safe"))
                        return ret;


                 }//down right
             }
             catch(Exception ex)
             {
                 
             }
             
             try
             {
                ret= blackKing.validateMove(kingRow, kingColumn, kingRow-1, kingColumn-1, chessBoard);
                if(ret.equals("true"))
                {
                    piece=chessBoard[kingRow-1][kingColumn-1];
                    chessBoard[kingRow-1][kingColumn-1]=chessBoard[kingRow][kingColumn];
                    chessBoard[kingRow][kingColumn]=null;

                    ret=blackKing.saveKing(chessBoard);
                    chessBoard[kingRow][kingColumn]=chessBoard[kingRow-1][kingColumn-1];
                    chessBoard[kingRow-1][kingColumn-1]=piece;        
                    if(ret.equals("safe"))
                        return ret;


                 }//down left
             }
             catch(Exception ex)
             {
                 
             }
         
         return ret; 
     }
     
     public static String pieceMoves(int rowFinal,int columnFinal,Piece chessBoard[][])
     {
         String ret="";
         Piece piece;
         BlackKing blackKing= new BlackKing();
         for(int i=0;i<=7;i++)
         {
             for(int j=0;j<=7;j++)
             {
                 if(chessBoard[i][j] instanceof BlackPiece)
                 {
                     BlackPiece blackpiece=(BlackPiece)chessBoard[i][j];
                     ret=blackpiece.validateMove(i, j, rowFinal, columnFinal, chessBoard);
                     if(ret.equals("true"))
                    {
                        piece=chessBoard[rowFinal][columnFinal];
                        chessBoard[rowFinal][columnFinal]=chessBoard[i][j];
                        chessBoard[i][j]=null;
                 
                        ret=blackKing.saveKing(chessBoard);
                        
                        chessBoard[i][j]=chessBoard[rowFinal][columnFinal];
                        chessBoard[rowFinal][columnFinal]=piece;        
                        if(ret.equals("safe"))
                            return ret;
                  
                    }
                 }
             }
         }
         
         return ret;
     }
     
     public static String pieceBetween(int row,int column,int kingRow,int kingColumn, Piece chessBoard[][])
     {
         String ret="";
         ArrayList<Integer>rowBetween= new ArrayList<>();
         ArrayList<Integer>columnBetween= new ArrayList<>();
         
         if(row==kingRow)
         {
             if(column>kingColumn)
             {
                 for(int i=kingColumn+1;i<column;i++)
                 {
                     rowBetween.add(row);
                     columnBetween.add(i);
                 }
             }
             else if(column>kingColumn)
             {
                 for(int i=kingColumn-1;i>column;i--)
                 {
                     rowBetween.add(row);
                     columnBetween.add(i);
                 }
             }
         }
         else if(column==kingColumn)
         {
             if(row>kingRow)
             {
                 for(int i=kingRow+1;i<row;i++)
                 {
                     rowBetween.add(i);
                     columnBetween.add(column);
                 }
             }
             else if(row<kingRow)
             {
                 
                 for(int i=kingRow-1;i>row;i--)
                 {
                     rowBetween.add(i);
                     columnBetween.add(column);
                 }
             }
         }
         else if(row>kingRow)
         {
             if(column>kingColumn)
             {
                 for(int i=kingRow+1,j=kingColumn+1;i<row;i++,j++)
                 {
                     rowBetween.add(i);
                     columnBetween.add(j);
                 }
             }
             else if(column<kingColumn)
             {
                 for(int i=kingRow+1,j=kingColumn-1;i<row;i++,j--)
                 {
                     rowBetween.add(i);
                     columnBetween.add(j);
                 }
             }
                 
         }
         else if(row<kingRow)
         {
             if(column>kingColumn)
             {
                 for(int i=kingRow-1,j=kingColumn+1;i>row;i--,j++)
                 {
                     rowBetween.add(i);
                     columnBetween.add(j);
                 }
             }
             else if(column<kingColumn)
             {
                 for(int i=kingRow-1,j=kingColumn-1;i>row;i--,j--)
                 {
                     rowBetween.add(i);
                     columnBetween.add(j);
                 }
             }
                 
         }
         int size=rowBetween.size();
         BlackKing blackKing= new BlackKing();
         Piece piece;
         for(int i=0;i<=7;i++)
         {
             for(int j=0;j<=7;j++)
             {
                 if(chessBoard[i][j] instanceof BlackPiece)
                 {
                     BlackPiece blackPiece=(BlackPiece)chessBoard[i][j];
                     for(int k=0;k<size;k++)
                     {
                        ret=blackPiece.validateMove(i, j, rowBetween.get(k), columnBetween.get(k), chessBoard);
                        if(ret.equals("true"))
                        {
                            piece=chessBoard[rowBetween.get(k)][columnBetween.get(k)];
                            chessBoard[rowBetween.get(k)][columnBetween.get(k)]=chessBoard[i][j];
                            chessBoard[i][j]=null;
                 
                            ret=blackKing.saveKing(chessBoard);
                        
                            chessBoard[i][j]=chessBoard[rowBetween.get(k)][columnBetween.get(k)];
                            chessBoard[rowBetween.get(k)][columnBetween.get(k)]=piece;        
                            if(ret.equals("safe"))
                            return ret;
                  
                        }
                     }
                 }
             }
         }
         return ret;
     }
}
