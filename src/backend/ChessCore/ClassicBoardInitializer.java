package backend.ChessCore;

import backend.ChessCore.Pieces.WhitePieceFactory;
import backend.ChessCore.Pieces.BlackPieceFactory;
import backend.ChessCore.Pieces.Piece;


public final class ClassicBoardInitializer implements BoardInitializer {
    private static final BoardInitializer instance = new ClassicBoardInitializer();
    private final WhitePieceFactory whitePieceFactory = new WhitePieceFactory();
    private final BlackPieceFactory blackPieceFactory = new BlackPieceFactory();
    ClassicBoardInitializer() {
    }

    public static BoardInitializer getInstance() {
        return instance;
    }

    @Override
    public Piece[][] initialize() {
        Piece[][] initialState = { 
            {whitePieceFactory.createRook(),whitePieceFactory.createKnight(), whitePieceFactory.createBishop(), whitePieceFactory.createQueen(),whitePieceFactory.createKing(), whitePieceFactory.createBishop(), whitePieceFactory.createKnight(), whitePieceFactory.createRook()},
            {whitePieceFactory.createPawn(), whitePieceFactory.createPawn(), whitePieceFactory.createPawn(), whitePieceFactory.createPawn(),whitePieceFactory.createPawn(), whitePieceFactory.createPawn(),whitePieceFactory.createPawn(), whitePieceFactory.createPawn()},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {blackPieceFactory.createPawn(), blackPieceFactory.createPawn(), blackPieceFactory.createPawn(),blackPieceFactory.createPawn(),blackPieceFactory.createPawn(),blackPieceFactory.createPawn(), blackPieceFactory.createPawn(),blackPieceFactory.createPawn()},
            {blackPieceFactory.createRook(), blackPieceFactory.createKnight(),blackPieceFactory.createBishop(), blackPieceFactory.createQueen(),blackPieceFactory.createKing(),blackPieceFactory.createBishop(), blackPieceFactory.createKnight(),blackPieceFactory.createRook()}
        };
        return initialState;
    }
}
