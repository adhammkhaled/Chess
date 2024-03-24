package backend.ChessCore.Pieces;

import backend.ChessCore.ChessBoard;
import backend.ChessCore.ChessGame;
import backend.ChessCore.Move;
import backend.ChessCore.Player;
import backend.ChessCore.Square;

public final class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        return move.isDiagonalMove() && !game.isTherePieceInBetween(move);
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move(pieceSquare, squareUnderAttack);
        return move.isDiagonalMove() && !board.isTherePieceInBetween(move);
    }
    public Piece copy() {
        return new Bishop(getOwner());
    }
}
