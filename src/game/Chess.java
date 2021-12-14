package game;
import pieces.*;

public class Chess implements Boardgame {
    /** The main class for the game. Contains the board and the main logic for the game **/

    // THE BOARD:
    private static Chess chess = null;
    // The board is (similar to lab 2) a 2-dimensional array, containing nothing or a Piece object.
    private Piece[][] board;
    // The also store the position of the different pieces, since this will change throughout the game
    private PiecePosition[][] positions;

    // GAME LOGIC
    // Logic for the two users
    private User user1;
    private User user2;
    private User currentUserPlaying;

    // The message shown in ViewControl:
    private String currentMessage;

    protected Chess() {
        this.board = new Piece[8][8];
        this.positions = new PiecePosition[8][8];

        user1 = new User("red");
        user2 = new User("blue");
        currentUserPlaying = user1;
        currentMessage = "It is " + currentUserPlaying.getColour() + "'s turn";

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                // This creates the board (but does not add any pieces yet, that is done in placePiecesOnBoard() )
                this.positions[y][x] = new PiecePosition(y, x);
            }
        }
    }

    /** ----- Methods to initialize the game: ------ **/

    public static Chess startTheGame() {
        chess = new Chess();
        return chess;
    }


    public void placePiecesOnBoard() {
        int val = 1;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++, val++) {
                PiecePosition piecePosition = this.getPosition(x, y);

                if (val == 1 || val == 8) {
                    new Rook(piecePosition, user2);
                } else if (val == 2 || val == 7) {
                    new Knight(piecePosition, user2);
                } else if (val == 3 || val == 6) {
                    new Bishop(piecePosition, user2);
                } else if (val == 4) {
                    new Queen(piecePosition, user2);
                } else if (val == 5) {
                    new King(piecePosition, user2);
                } else if (8 < val && val < 17) {
                    new Pawn(piecePosition, user2);
                } else if (48 < val && val < 57) {
                    new Pawn(piecePosition, user1);
                } else if (val == 57 || val == 64) {
                    new Rook(piecePosition, user1);
                } else if (val == 58 || val == 63) {
                    new Knight(piecePosition, user1);
                } else if (val == 59 || val == 62) {
                    new Bishop(piecePosition, user1);
                } else if (val == 60) {
                    new Queen(piecePosition, user1);
                } else if (val == 61) {
                    new King(piecePosition, user1);
                }
            }

        }
        // Adds possible movements for piece for users:
        currentUserPlaying.checkForCheckMate();
    }

    public static Chess getCurrentBoard() { if (chess == null) chess = new Chess();
        return chess;
    }

    /** ----- Methods handle the message (That is displayed in the window) ------ **/


    public String getMessage(){
        return currentMessage;
    }

    public void setMessage(String value){
        this.currentMessage = value;
    }


    /** ----- Methods handle the logic in the game (is it check mate?)------ **/


    public boolean checkIfNotChecked() {
        User otherPlayer = getNotCurrentUser(currentUserPlaying);
        otherPlayer.checkForCheckMate();
        currentUserPlaying.checkForCheckMate();

        if(otherPlayer.checkMate){
            // current player is NOT checked
            // (but the opponent is checked.)
            // so we can return true
            return true;
        }

        // Then, we check if CURRENT player is in check, otherwise, return true:
        return !currentUserPlaying.checkMate;
    }


    /** ----- Methods handle the two players ------ **/

    public User getCurrentUser() {
        return currentUserPlaying;
    }

    public User getNotCurrentUser(User player) {
        // when we want to get the opponent - but not change who is the current user playing
        // (Maybe possible to merge with nextTurn in future?)
        if(player == user1) return user2;
        return user1;
    }

    public User nextTurn() {
        currentUserPlaying = getNotCurrentUser(currentUserPlaying);
        return currentUserPlaying;
    }

    /** ----- Methods to handle pieces (get, add, remove etc.) ------ **/

    public PiecePosition getPosition(int y, int x) { return positions[y][x]; }

    public boolean addingPiece(Piece piece, PiecePosition piecePosition) {
        if (board[piecePosition.getY()][piecePosition.getX()] == null) {
            board[piecePosition.getY()][piecePosition.getX()] = piece;
            return true;
        }
        return false;
    }

    public boolean removingPiece(PiecePosition piecePosition) {
        if (board[piecePosition.getY()][piecePosition.getX()] != null) {
            // null since there are no longer any piece at the position.
            // The adding of new piece (if any) is still handled in the addPiece method.
            board[piecePosition.getY()][piecePosition.getX()] = null;
            return true;
        }
        return false;
    }

    public Piece gettingPieceAtPosition(PiecePosition piecePosition) {
        return board[piecePosition.getY()][piecePosition.getX()];
    }

    public boolean movingPiece(Piece piece, PiecePosition piecePosition) {

        // First, check if movement is pawn -> queen situation
        if(piece.getType().equals("pawn")) {
            if((piece.getColor().equals("red") && piecePosition.getY() == 0) ||
                    (piece.getColor().equals("blue") && piecePosition.getY() == 7)) {
                // The pawn is at the row on the opposite side
                // Logic to turn pawn to queen = juicy gummy bear :-)
                removingPiece(piecePosition);
                Queen queen = new Queen(piecePosition, currentUserPlaying);
                addingPiece(queen, piecePosition);
                chess.setMessage(chess.getCurrentUser().getColour() + "'s pawn made it all the way and is now a queen - nice!");
            }
        }
        if (!removingPiece(piece.getPiecePosition())) return false;
        if (!addingPiece(piece, piecePosition)) { addingPiece(piece, piece.getPiecePosition()); return false; }

        return true;
    }
}