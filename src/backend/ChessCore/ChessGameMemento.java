/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.ChessCore;

/**
 *
 * @author adham
 */
public class ChessGameMemento {
    private ChessBoard boardSnapshot;
    private GameStatus gameStatus;
    private Player whoseTurn;
    private Move lastMove;
    private boolean canWhiteCastleKingSide;
    private boolean canWhiteCastleQueenSide;
    private boolean canBlackCastleKingSide;
    private boolean canBlackCastleQueenSide;
    

    public ChessGameMemento(ChessBoard boardSnapshot, GameStatus gameStatus, Player whoseTurn, boolean canWhiteCastleKingSide
            ,boolean canWhiteCastleQueenSide, boolean canBlackCastleKingSide,boolean canBlackCastleQueenSide,Move lastMove){
        this.boardSnapshot = boardSnapshot;
        this.gameStatus = gameStatus;
        this.whoseTurn = whoseTurn;
        this.canWhiteCastleKingSide = canWhiteCastleKingSide;
        this.canWhiteCastleQueenSide = canWhiteCastleQueenSide;
        this.canBlackCastleKingSide = canBlackCastleKingSide;
        this.canBlackCastleQueenSide = canBlackCastleQueenSide;
        this.lastMove = lastMove;
        
        }
    //getters to all variables
    public ChessBoard getBoardSnapshot(){
        return this.boardSnapshot;
    }
    public GameStatus getGameStatus(){
        return this.gameStatus;
    }
    public Player getWhoseTurn(){
        return this.whoseTurn;
    }
    public Move getLastMove(){
        return this.lastMove;
    }
    public boolean getIsCanWhiteCastleKingSide(){
        return this.canWhiteCastleKingSide;
    }
    public boolean getIsCanWhiteCastleQueenSide(){
        return this.canWhiteCastleQueenSide;
    }
    public boolean getIsCanBlackCastleKingSide(){
        return this.canBlackCastleKingSide;
    }
    public boolean getIsCanBlackCastleQueenSide(){
        return this.canBlackCastleQueenSide;
    }
}


