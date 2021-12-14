package game;
import java.util.HashSet;
import pieces.*;

public class User {
    /** This class stores all info about a specific player: what color is the user? (red/blue?) what pieces are left? Is the user checked? **/
    // info about user's current pieces:
    // (also good if we in future want to keep track of score etc. which is currently not implemented)
    protected HashSet<Piece> allpieces;
    // King-variable, saved here as well to easier keep track of potential check:
    protected King king;

    // Info about user:
    private String colour;
    // if the user is currently checked:
    public Boolean checkMate;

    public User(String colour) {
        this.colour = colour;
        this.allpieces = new HashSet<Piece>();
        this.checkMate = false;
    }

    /** ----- General methods for user ------ **/

    public String getColour() {
        return colour;
    }

    /** ----- Methods handle check mate game logic ------ **/

    public void checkForCheckMate() {
        Chess chess = Chess.getCurrentBoard();
        User otherPlayer = chess.getNotCurrentUser(this);
        otherPlayer.checkMate = false;

        for (Piece piece : allpieces) {
            // We go through all current pieces the user has
            HashSet<PiecePosition> possibleMovements = piece.checkIfOkMovement();
            // if THE PATH from this piece, contains the king: we have a check!
            if (possibleMovements.contains(otherPlayer.king.getPiecePosition())) {
                // update checkMate boolean - so front-end message updates:
                otherPlayer.checkMate = true;
            }
        }

    }

    /** ----- Methods handle user's pieces ------ **/

    public void addingPiece(Piece piece) {
        /* --- Adds a piece to users hash-set: allPieces --- */
        allpieces.add(piece);
        checkIfKing(piece);
    }

    public void checkIfKing(Piece piece) {
        if (piece instanceof King) {
            // If it is a king: we want to save it in a
            // king variable instead
            king = (King) piece;
        }
    }

}
