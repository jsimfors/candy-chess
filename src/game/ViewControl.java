package game;// lämpliga import-satser här
import pieces.Piece;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ViewControl extends JFrame {
    private static ViewControl view = null;
    protected JPanel boardPanel;
    private JLabel mess = new JLabel();
    private Square[][] squares;
    private JButton goWithPiece = null;


    protected ViewControl() {
        Chess chess = Chess.getCurrentBoard();

        // we can't apply the grid directly to the big frame (this) so we add it to a JPanel first
        JPanel chessboardPanel = new JPanel();
        chessboardPanel.setLayout(new GridLayout(8, 8));

        squares = new Square[8][8];
        Chess chessboard = Chess.getCurrentBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square;
                // Check if this position on the board includes a piece
                // If yes: Include image of a piece, else: if we just apply a color:
                // if there is no piece, we will get Piece piece = null.
                Piece piece = chessboard.gettingPieceAtPosition(new PiecePosition(i, j));

                /* White if: i=0 and j = even */
                /* white if: i=1, and j = odd */
                if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)) {
                    square = new Square(i, j, Color.decode("#f7f0eb"), chessboard.getPosition(i, j));
                    if (piece != null) {
                        // Add the chess piece image if there is any
                        square.addPieceToSquare(piece);
                    }
                } else {
                    square = new Square(i, j, Color.decode("#121111"), chessboard.getPosition(i, j));
                    if (piece != null) {
                        // Add the chess piece image if there is any
                        square.addPieceToSquare(piece);
                    }
                }
                squares[i][j] = square;
                // Add action listener (previosly we only added the image of the piece, now we add the logic)
                square.addActionListener(new ViewControl.AddActionToSquare());
                chessboardPanel.add(square);
            }
        }


        this.setBackground(Color.decode("#ffeb99"));
        boardPanel = new JPanel(new BorderLayout());
        boardPanel.setBackground(Color.decode("#ffeb99"));
        boardPanel.add(chessboardPanel);

        JLabel header;
        header = new JLabel(new ImageIcon("src/pieces/imgs/header_smaller.png"));
        header.setBackground(Color.decode("#ffeb99"));
        boardPanel.add(header, BorderLayout.NORTH);

        mess.setText(chess.getMessage());
        mess.setFont(new Font("Monospaced", Font.PLAIN, 21));

        boardPanel.add(mess, BorderLayout.SOUTH);
        this.add(boardPanel, BorderLayout.NORTH);

        // Styling:
        Border padding = BorderFactory.createEmptyBorder(15, 40, 15, 40);
        boardPanel.setBorder(padding);

        this.setContentPane(boardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // If we don't include this.pack() the window gets super small:
        this.pack();
        this.setVisible(true);
    }


    public static ViewControl getCurrentWindow() {
        if (view == null) { view = new ViewControl(); }
        return view;
    }


    protected void highlightPath(boolean showPath) {
        Chess chess = Chess.getCurrentBoard();

        // Position of the piece we want to go with (move)
        PiecePosition piecePosition = (PiecePosition) goWithPiece
                .getClientProperty("location");
        Piece piece = chess.gettingPieceAtPosition(piecePosition);

        for (PiecePosition availableLocation : piece.getOk_movements()) {
            JButton chessSquare = squares[availableLocation.getY()][availableLocation.getX()];
            // color = the CURRENT color of the board, we will need this for choosing our green shadde.
            Color color = (Color) chessSquare.getClientProperty("color");

            if (showPath) {
                // If we want to highlight a new path:
                if(color.getRed() == 18){
                    // We have a black box = darker green
                    color = Color.decode("#179c3f");
                    chessSquare.setBackground(color);
                } else {
                    // We have a white box = lighter green
                    color = Color.decode("#70ff9b");
                    chessSquare.setBackground(color);
                }
            }
            // else: we go back to it's original color
            // (If we don't do this, the color remains green even in the next move)
            chessSquare.setBackground(color);
        }
    }

    public class AddActionToSquare implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Chess chess = Chess.getCurrentBoard();
            JButton goToSquare = (JButton) e.getSource();
            PiecePosition piecePosition = (PiecePosition) goToSquare
                    .getClientProperty("location");
            Piece piece = chess.gettingPieceAtPosition(piecePosition);

            // Update message to front end:
            chess.setMessage("It is " + chess.getCurrentUser().getColour() + "'s turn");
            mess.setText(chess.getMessage());

            if (piece != null) {
                if(piece.getUser() == chess.getCurrentUser()){
                    // A piece is selected, and it is from the correct team.
                    if (goWithPiece != null) {
                        highlightPath(false);
                    }
                    goWithPiece = goToSquare;
                    highlightPath(true);
                    return;
                }
            }

            if (goWithPiece != null) {
                // We can move the selected piece:
                movePiece(goToSquare);
            }
        }



        private void movePiece(JButton goToSquare) {
            Chess chess = Chess.getCurrentBoard();

            PiecePosition currentSquare = (PiecePosition) goWithPiece
                    .getClientProperty("location");
            Piece piece = (Piece) chess.gettingPieceAtPosition(currentSquare);
            PiecePosition goToLocation = (PiecePosition) goToSquare
                    .getClientProperty("location");
            highlightPath(false);

            if (piece.moveTo(goToLocation)) {
                if (!chess.checkIfNotChecked()) {
                    // stop user from making this silly move!
                    chess.checkIfNotChecked();
                    chess.setMessage("I would not move the " + piece.getType()  + " there if I were you...");
                    mess.setText(chess.getMessage());
                    goWithPiece = null;
                    return;
                }
                goWithPiece.setIcon(null);
                goWithPiece = null;
                // we need to getPiece again, since it could be a new type (paw -> queen)
                piece = chess.gettingPieceAtPosition(goToLocation);
                ImageIcon icon = new ImageIcon(piece.getImageAddress());
                mess.setText(chess.getMessage());
                Image img = icon.getImage();
                Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                goToSquare.setIcon(new ImageIcon(resizedImage));
                if (chess.nextTurn().checkMate) {
                    // Update front-end if the opponent became checked from currentUser doing this move.
                    chess.setMessage(chess.getCurrentUser().getColour() + ", you are checked");
                    mess.setText(chess.getMessage());
                }

            } else {
                // If a non-colored square is chosen (Punkt 4 fran Canvas instruktionerna )
                // "Om det ar ett korrekt (*) drag sa utfors det, annars ges felmeddelande."
                chess.setMessage("Please choose one of the green squares");
                mess.setText(chess.getMessage());
            }
        }

    }
}