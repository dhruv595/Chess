package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game implements Serializable 
{
    static final long serialVersionUID = 7L;
    private Player black= new Player();
    private Player white= new Player();

    
    private Player currentPlayer= new Player();
    private Piece[][] chessBoard= new Piece[8][8];

    private int gameId;
    private String startDate;
    
    private int blackKingCheck;
    private int whiteKingCheck;
    private int blackKingCastle;
    private int whiteKingCastle;
    private int blackRookLeftCastle;
    private int blackRookRightCastle;
    private int whiteRookLeftCastle;
    private int whiteRookRightCastle;
    private Piece blackPieceDeleted[];
    private Piece whitePieceDeleted[];
    private int blackDeletedCount=0;
    private int whiteDeletedCount=0;
    private String lastMove="8888";
    private String moveStatus="";
    
    public Game()
    {
        for(int i=0;i<8;i++)
        {
            chessBoard[1][i]= new BlackPawn();
            chessBoard[6][i]= new WhitePawn();
        }
        
        chessBoard[7][0]=new WhiteRook();
        chessBoard[7][1]=new WhiteKnight();
        chessBoard[7][2]=new WhiteBishop();
        chessBoard[7][3]=new WhiteKing();
        chessBoard[7][4]=new WhiteQueen();
        chessBoard[7][5]=new WhiteBishop();
        chessBoard[7][6]=new WhiteKnight();
        chessBoard[7][7]=new WhiteRook();
        
        chessBoard[0][0]= new BlackRook();
        chessBoard[0][1]= new BlackKnight();
        chessBoard[0][2]= new BlackBishop();
        chessBoard[0][3]= new BlackKing();
        chessBoard[0][4]= new BlackQueen();
        chessBoard[0][5]= new BlackBishop();
        chessBoard[0][6]= new BlackKnight();
        chessBoard[0][7]= new BlackRook();
        
        blackPieceDeleted= new Piece[16];
        whitePieceDeleted= new Piece[16];
       
                
    }
    public Player getBlack() {
        return black;
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    public Player getWhite() {
        return white;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Piece[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Piece[][] chessBoard) {
        this.chessBoard = chessBoard;
    }
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getBlackKingCheck() {
        return blackKingCheck;
    }

    public void setBlackKingCheck(int blackKingCheck) {
        this.blackKingCheck = blackKingCheck;
    }

    public int getWhiteKingCheck() {
        return whiteKingCheck;
    }

    public void setWhiteKingCheck(int whiteKingCheck) {
        this.whiteKingCheck = whiteKingCheck;
    }

    public int getBlackKingCastle() {
        return blackKingCastle;
    }

    public void setBlackKingCastle(int blackKingCastle) {
        this.blackKingCastle = blackKingCastle;
    }

    public int getWhiteKingCastle() {
        return whiteKingCastle;
    }

    public void setWhiteKingCastle(int whiteKingCastle) {
        this.whiteKingCastle = whiteKingCastle;
    }

    public int getBlackRookLeftCastle() {
        return blackRookLeftCastle;
    }

    public void setBlackRookLeftCastle(int blackRookLeftCastle) {
        this.blackRookLeftCastle = blackRookLeftCastle;
    }

    public int getBlackRookRightCastle() {
        return blackRookRightCastle;
    }

    public void setBlackRookRightCastle(int blackRookRightCastle) {
        this.blackRookRightCastle = blackRookRightCastle;
    }

    public int getWhiteRookLeftCastle() {
        return whiteRookLeftCastle;
    }

    public void setWhiteRookLeftCastle(int whiteRookLeftCastle) {
        this.whiteRookLeftCastle = whiteRookLeftCastle;
    }

    public int getWhiteRookRightCastle() {
        return whiteRookRightCastle;
    }

    public void setWhiteRookRightCastle(int whiteRookRightCastle) {
        this.whiteRookRightCastle = whiteRookRightCastle;
    }

    public Piece[] getBlackPieceDeleted() {
        return blackPieceDeleted;
    }

    public void setBlackPieceDeleted(Piece[] blackPieceDeleted) {
        this.blackPieceDeleted = blackPieceDeleted;
    }

    public Piece[] getWhitePieceDeleted() {
        return whitePieceDeleted;
    }

    public void setWhitePieceDeleted(Piece[] whitePieceDeleted) {
        this.whitePieceDeleted = whitePieceDeleted;
    }

    public int getBlackDeletedCount() {
        return blackDeletedCount;
    }

    public void setBlackDeletedCount(int blackDeletedCount) {
        this.blackDeletedCount = blackDeletedCount;
    }

    public int getWhiteDeletedCount() {
        return whiteDeletedCount;
    }

    public void setWhiteDeletedCount(int whiteDeletedCount) {
        this.whiteDeletedCount = whiteDeletedCount;
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
    }

    public String getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(String moveStatus) {
        this.moveStatus = moveStatus;
    }

    

    

    

}
