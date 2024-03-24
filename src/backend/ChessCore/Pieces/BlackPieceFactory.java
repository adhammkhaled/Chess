/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.ChessCore.Pieces;

import backend.ChessCore.Player;

/**
 *
 * @author adham
 */
public class BlackPieceFactory implements PieceFactory {
    private final Player owner = Player.BLACK;

    public BlackPieceFactory() {

    }

    @Override
    public Piece createPawn() {
        return new Pawn(owner);
    }

    @Override
    public Piece createRook() {
        return new Rook(owner); 
    }

    @Override
    public Piece createBishop() {
        return new Bishop(owner); 
    }

    @Override
    public Piece createKing() {
        return new King(owner);
    }

    @Override
    public Piece createQueen() {
        return new Queen(owner); 
    }

    @Override
    public Piece createKnight() {
        return new Knight(owner);
    }
}
