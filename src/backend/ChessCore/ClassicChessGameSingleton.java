package backend.ChessCore;

public final class ClassicChessGameSingleton extends ChessGame {
private static ClassicBoardInitializer instance = null;
    public ClassicChessGameSingleton() {
        super(ClassicBoardInitializer.getInstance());
    }
private synchronized static ClassicBoardInitializer getInstance() {
        
        if (instance == null) {
            instance = new ClassicBoardInitializer();
        }
        return instance;
    }
    @Override
    protected boolean isValidMoveCore(Move move) {
        return !Utilities.willOwnKingBeAttacked(this.getWhoseTurn(), move, this.getBoard());
    }
}
