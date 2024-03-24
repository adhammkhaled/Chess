/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.ChessCore.ChessGame;
import backend.ChessCore.ClassicChessGameSingleton;
import backend.ChessCore.Move;
import backend.ChessCore.PawnPromotion;
import backend.ChessCore.Square;

/**
 *
 * @author adham
 */
public class MoveCommand implements ChessGUICommand {
    private ChessGameGUI chessGameGUI;
    private Square sourceSquare;
    private Square destinationSquare;
    private PawnPromotion pawnPromotion;

    public MoveCommand(ChessGameGUI chessGameGUI, Square sourceSquare, Square destinationSquare, PawnPromotion pawnPromotion) {
        this.chessGameGUI = chessGameGUI;
        this.sourceSquare = sourceSquare;
        this.destinationSquare = destinationSquare;
        this.pawnPromotion = pawnPromotion;
    }

    @Override
    public void execute(ClassicChessGameSingleton chessGame,ChessGameGUI chessGameGUI) {
        Move requiredMove = new Move(sourceSquare, destinationSquare, pawnPromotion);
        if (chessGame.makeMove(requiredMove)) {
            chessGameGUI.flipBoard();
            chessGameGUI.initializeBoard();
        }
        chessGameGUI.resetSquares();
    }

    @Override
    public void undo(ClassicChessGameSingleton chessGame,ChessGameGUI chessGameGUI) {
        if (chessGame.undoLastMove()) {
        chessGameGUI.flipBoard();
        chessGameGUI.initializeBoard();
    } else {
        // no move to undo
    }
 }
}
