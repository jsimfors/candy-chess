package pieces;

import game.*;
import java.util.HashSet;

public abstract class Piece {
    /** --- Abstract class for all the pieces to follow --- **/
    public String colour;
    public PiecePosition pieceposition;
    public User user;
    public String type;
    protected HashSet<PiecePosition> ok_movements;
    protected String imageAddress;

    public Piece(User user, PiecePosition piecePosition){
        this.colour = user.getColour();
        this.pieceposition = piecePosition;
        Chess chess = Chess.getCurrentBoard();
        chess.addingPiece(this, piecePosition);
        this.user = user;
        this.user.addingPiece(this);
    }

    /** Methods for the characteristics of a piece, color, type etc. ***/

    public String getColor() {
        // Color of the team, red or blue.
        return colour;
    }
    public String getType() {
        // Returns what type of piece, ex "Pawn", for front-end message
        return type;
    }

    public PiecePosition getPiecePosition() {
        return pieceposition;
    }

    public User getUser() {
        return user;
    }

    public String getImageAddress() {
        return imageAddress;
    }


    /** Methods for the movement of piece (available movements, capture candy? etc.). ***/

    public HashSet<PiecePosition> getOk_movements() {
        return ok_movements;
    }


    public boolean moveTo(PiecePosition piecePosition) {
        if (ok_movements.contains(piecePosition)) {
            Chess chess = Chess.getCurrentBoard();
            Piece opponents_candy = chess.gettingPieceAtPosition(piecePosition);
            if (opponents_candy != null) {
                // We ate of opponents candy :D
                ateOpponentsCandy(opponents_candy);
            } else {
                // We did not eat of opponents candy :(
                String nextPlayer;
                if( chess.getCurrentUser().getColour().equals("red")){
                    nextPlayer = "blue";
                } else {
                    nextPlayer = "red";
                }
                chess.setMessage("It is " + nextPlayer + "'s turn");
            }

            chess.movingPiece(this, piecePosition);
            this.pieceposition = piecePosition;
            return true;
        }

        return false;
    }

    protected void ateOpponentsCandy(Piece opponent) {
        Chess chess = Chess.getCurrentBoard();
        String[] candy_crush_word = {"Delicious!", "Sweet!", "Tasty!", "Sugar crushing it!", "Divine!", "Sodalicious!", "Juicy!"};
        chess.setMessage(chess.getCurrentUser().getColour() + " ate a " + opponent.getType() + "! " + candy_crush_word[(int)(Math.random() * (6 + 1))]);
        chess.removingPiece(opponent.pieceposition);
    }

    abstract public HashSet<PiecePosition> checkIfOkMovement();

    abstract protected void movingPiece(HashSet<PiecePosition> result, int horizontal_move, int vertical_move);

}