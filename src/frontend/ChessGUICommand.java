/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package frontend;

import backend.ChessCore.ClassicChessGameSingleton;

/**
 *
 * @author adham
 */
public interface ChessGUICommand {
    void execute(ClassicChessGameSingleton chessGame,ChessGameGUI chessGameGUI);
    void undo(ClassicChessGameSingleton chessGame,ChessGameGUI chessGameGUI);
}
