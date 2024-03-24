package backend.ChessCore.Pieces;

import backend.ChessCore.ChessBoard;
import backend.ChessCore.ChessGame;
import backend.ChessCore.Move;
import backend.ChessCore.Player;
import backend.ChessCore.Square;

public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public abstract boolean isValidMove(Move move, ChessGame game);
    public abstract boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board);
    public abstract Piece copy();
}
