/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.ChessCore.*;
import backend.ChessCore.Pieces.Bishop;
import backend.ChessCore.Pieces.King;
import backend.ChessCore.Pieces.Knight;
import backend.ChessCore.Pieces.Pawn;
import backend.ChessCore.Pieces.Piece;
import backend.ChessCore.Pieces.Queen;
import backend.ChessCore.Pieces.Rook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author adham
 */
import java.util.List;
public class ChessGameGUI implements Node {
    private JButton[][] buttons = new JButton[8][8];
    private JFrame frame;
    private final ClassicChessGameSingleton chessGame = new ClassicChessGameSingleton();
    
    private boolean flippedBoard=false;
    private Square sourceSquare;
    private Square destinationSquare;
    private boolean blackInCheck=false;
    private boolean whiteInCheck=false;
    private PawnPromotion pawnPromotion;
    private JButton inCheckKingButton;
    
    private Color darkColor = new Color(139, 69, 19); 
    private Color lightColor = new Color(255, 228, 181);
    private JButton undoButton;
    
    private ChessGUICommand currentCommand;
    
    public ChessGameGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Chess GUI");
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        initializeBoard();
        initializeUndoButton();
        frame.setVisible(true);
    }
     void initializeBoard() {
        frame.getContentPane().removeAll();
        frame.repaint();
        JPanel chessboardPanel = new JPanel();
        chessboardPanel.setLayout(new GridLayout(8, 8));
        blackInCheck = false;
        whiteInCheck = false;
        GameStatus gameStatus = chessGame.getGameStatus();
        if (gameStatus == GameStatus.BLACK_UNDER_CHECK)
            blackInCheck = true;
        else if (gameStatus == GameStatus.WHITE_UNDER_CHECK)
            whiteInCheck = true;

        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                JButton button = createButton(row, col, chessboardPanel);
            }
        }

        // add chessboardPanel to the frame
        frame.add(chessboardPanel, BorderLayout.CENTER);
        initializeUndoButton(); 
        frame.revalidate();
}

    
    private JButton createButton(int row, int col,JPanel chessboardPanel) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));
        if ((row + col) % 2 == 0) {
                button.setBackground(lightColor);
            } else {
                button.setBackground(darkColor);
            }
        
            
        Square movingPiece;
        if(flippedBoard)
            movingPiece = new Square(BoardFile.values()[7-col], BoardRank.values()[7-row]);
        else 
            movingPiece = new Square(BoardFile.values()[col], BoardRank.values()[row]);
        Piece piece = chessGame.getPieceAtSquare(movingPiece);
        
        if (piece != null) {
            Player pieceOwner = piece.getOwner();
            String imageName = getImageName(piece,pieceOwner);
            ImageIcon icon = new ImageIcon(imageName);
            Image img = icon.getImage();

            
            Image scaledImg = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            
            
            button.setIcon(new ImageIcon (scaledImg));
            if(blackInCheck && piece instanceof King && piece.getOwner() == Player.BLACK
                    || whiteInCheck && piece instanceof King && piece.getOwner() == Player.WHITE){
                button.setBackground(new Color(255,127,127));
                inCheckKingButton = button;
            }
                
            
                
        }

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flippedBoard)
                    handleButtonClick(7-row,7-col);
                else
                    handleButtonClick(row, col);
                GameStatus gameStatus = chessGame.getGameStatus();

                    
                if(gameStatus == GameStatus.WHITE_WON)
                           showPanel("White",true);
                
                else if(gameStatus == GameStatus.BLACK_WON)
                    showPanel("Black",true);
                
                else if(gameStatus == GameStatus.INSUFFICIENT_MATERIAL)
                    showPanel("Insufficient material",false);
                
                else if(gameStatus == GameStatus.STALEMATE)
                    showPanel("Stalemate",false);
                
                
                
            }  
        });
        if(flippedBoard)
            buttons[7-row][7-col] = button;
        else
            buttons[row][col] = button;
        chessboardPanel.add(button);
        return button;
    }
    private String getImageName(Piece piece,Player owner) {
        if(owner == Player.WHITE){
                if(piece instanceof Pawn)
                    return "images/WhitePawn.png";
                else if(piece instanceof Bishop)
                    return "images/WhiteBishop.png";
                else if(piece instanceof Knight)
                    return "images/WhiteKnight.png";
                else if(piece instanceof Queen)
                    return "images/WhiteQueen.png";
                else if(piece instanceof Rook)
                    return "images/WhiteRook.png";
                else if(piece instanceof King)
                    return "images/WhiteKing.png";     
        }
        else if(owner == Player.BLACK){
                if(piece instanceof Pawn)
                    return "images/BlackPawn.png";
                else if(piece instanceof Bishop)
                    return "images/BlackBishop.png";
                else if(piece instanceof Knight)
                    return "images/BlackKnight.png";
                else if(piece instanceof Queen)
                    return "images/BlackQueen.png";
                else if(piece instanceof Rook)
                    return "images/BlackRook.png";
                else if(piece instanceof King)
                    return "images/BlackKing.png";     
        }
        return ""; 
    }
    
    private void handleButtonClick(int row, int col) {
        resetButtonColors();
        Square testSquare = new Square(BoardFile.values()[col], BoardRank.values()[row]);
        if (sourceSquare == null || (chessGame.getPieceAtSquare(testSquare)!=null&&chessGame.getPieceAtSquare(testSquare).getOwner()==chessGame.getWhoseTurn())) {
            sourceSquare = new Square(BoardFile.values()[col], BoardRank.values()[row]);
            highlightValidMoves(sourceSquare);
        }
        else {
            destinationSquare = new Square(BoardFile.values()[col], BoardRank.values()[row]);
            Move requiredMove=null;
            if(isPromotionMove()&&chessGame.getPieceAtSquare(sourceSquare).getOwner()==chessGame.getWhoseTurn()){
                PawnPromotion promotionOption=null;
                if(chessGame.hasPieceInSquareForPlayer(sourceSquare, Player.WHITE)){
                    WhitePromotionChooserFrame whitePromotionChooserFrame = new WhitePromotionChooserFrame();
                    whitePromotionChooserFrame.setVisible(true);
                    whitePromotionChooserFrame.setParentNode(this);
                    
                    
                }
                else if(chessGame.hasPieceInSquareForPlayer(sourceSquare, Player.BLACK)){
                    BlackPromotionChooserFrame blackPromotionChooserFrame = new BlackPromotionChooserFrame();
                    blackPromotionChooserFrame.setVisible(true);
                    blackPromotionChooserFrame.setParentNode(this);
                   
                    
                }
            }
                
            else{
                currentCommand = new MoveCommand(this, sourceSquare, destinationSquare, null);
                currentCommand.execute(chessGame,this);
            }
        }
    }

    void resetSquares(){
        this.sourceSquare = null;
        this.destinationSquare = null;
    }
    
    private void highlightValidMoves(Square source) {
        List<Square> possibleSquares = chessGame.getAllValidMovesFromSquare(source);
        for (Square square : possibleSquares) {
            int squareColumn = square.getFile().getValue();
            int squareRow = square.getRank().getValue();
            JButton possibleButton = buttons[squareRow][squareColumn];
            if(chessGame.getPieceAtSquare(square)!=null)
                possibleButton.setBackground(new Color(204,0,0));
            else
                possibleButton.setBackground(new Color(173, 216, 230));
        }
    }
    
    private void resetButtonColors() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                JButton button = buttons[r][c];
                button.setBackground((r + c) % 2 == 0 ? lightColor : darkColor);
            }
        }
        if(blackInCheck || whiteInCheck)
        inCheckKingButton.setBackground(new Color(255,127,127));
    }
    
    void flipBoard() {
    flippedBoard = !flippedBoard;
}
   private void showPanel(String message,boolean playerWon){
       JPanel panel = new JPanel();
       ImageIcon customImage;
       if(playerWon){
            customImage = new ImageIcon("images/medal.png");
            customImage.setImage(customImage.getImage().getScaledInstance(200, 297, Image.SCALE_SMOOTH));
       }
       
       else{
           customImage = new ImageIcon("images/handshake.png"); 
           customImage.setImage(customImage.getImage().getScaledInstance(200,200 , Image.SCALE_SMOOTH));
       }
       
        
        
        JLabel imageLabel = new JLabel(customImage);
        JLabel messageLabel;
        if(playerWon)
            messageLabel = new JLabel("  "+message + " Won.");
        else
            messageLabel = new JLabel("  "+message + ", it's a draw.");
        panel.setLayout(new BorderLayout());
        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(messageLabel, BorderLayout.CENTER);
        JOptionPane.showOptionDialog(
                null,
                panel,
                "Game Over",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, 
                null,
                new Object[]{},
                null
        );  
   }


   public void receivePromotionChoice(PawnPromotion promotionChoice) {
        pawnPromotion = promotionChoice;
        currentCommand = new MoveCommand(this, sourceSquare, destinationSquare, pawnPromotion);
        currentCommand.execute(chessGame,this);
        pawnPromotion=null;
    }

   private boolean isPromotionMove(){
       if(sourceSquare.getRank().getValue()==6&&destinationSquare.getRank().getValue()==7&&chessGame.hasPieceInSquareForPlayer(sourceSquare, Player.WHITE)&&chessGame.getPieceAtSquare(sourceSquare) instanceof Pawn)
           return true;
       else if(sourceSquare.getRank().getValue()==1&&destinationSquare.getRank().getValue()==0&&chessGame.hasPieceInSquareForPlayer(sourceSquare, Player.BLACK)&&chessGame.getPieceAtSquare(sourceSquare) instanceof Pawn)
           return true;
       
       return false;
   }
   
   private void initializeUndoButton() {
        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUndoButtonClick();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(undoButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }
   void handleUndoButtonClick() {
    currentCommand.undo(chessGame,this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChessGameGUI();
            }
        });
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public void setParentNode(Node node) {
   
    }
    
}
