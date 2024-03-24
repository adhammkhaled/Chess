/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.ChessCore.Pieces;

/**
 *
 * @author adham
 */
public interface PieceFactory {
   Piece createPawn();
   Piece createRook();
   Piece createBishop();
   Piece createKing();
   Piece createQueen();
   Piece createKnight (); 
}
