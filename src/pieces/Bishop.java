package pieces;

import game.Chess;
import game.PiecePosition;
import game.User;

import java.util.HashSet;

public class Bishop extends Piece {
    public Bishop(PiecePosition piecePosition, User user) {
        super(user, piecePosition);
        this.imageAddress = "src/pieces/imgs/" + this.colour + "/bishop_candyversion.png";
        this.type = "bishop";

    }

    @Override
    public HashSet<PiecePosition> checkIfOkMovement() {
        HashSet<PiecePosition> possible_moves = new HashSet<PiecePosition>();

        // The bishop can move in the diagonal DIRECTION, but we need to also check length
        // more than just one step. We add method that contains while-loop, and sends it the ok direction.
        movingPiece(possible_moves, 1, 1);
        movingPiece(possible_moves, 1, -1);
        movingPiece(possible_moves, -1, 1);
        movingPiece(possible_moves, -1, -1);

        // adding all possible directions for the rook
        ok_movements = possible_moves;
        return possible_moves;

    }

    @Override
    protected void movingPiece(HashSet<PiecePosition> possible_moves, int horizontal_move, int vertical_move) {
        // System.out.println("Ok movements for bishop are: "  int horizontal_move, int vertical_move);

        Chess chess = Chess.getCurrentBoard();
        PiecePosition moving_to = new PiecePosition(pieceposition);

        while (true){
            // since we can go more than one step at the time
            moving_to.updateY(horizontal_move);
            moving_to.updateX(vertical_move);
            try {
                Piece ate_opponents_candy = chess.gettingPieceAtPosition(moving_to);
                if (ate_opponents_candy == null) {
                    // none of the opponents candy at this position :( sad!
                    possible_moves.add(chess.getPosition(moving_to.getY(),
                            moving_to.getX()));
                } else {
                    if (!ate_opponents_candy.colour.equals(colour)) {
                        // let's just double check we did not eat OUR OWN candy
                        possible_moves.add(chess.getPosition(
                                moving_to.getY(),
                                moving_to.getX()));
                    }
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // We want to stop at end of board. Using built-in catch "ArrayIndexOutOfBoundsException"
                // "Thrown to indicate that an array has been accessed with an illegal index.
                // The index is either negative or greater than or equal to the size of the array"
                // From: https://docs.oracle.com/javase/7/docs/api/java/lang/ArrayIndexOutOfBoundsException.html
                break;
            }
        }
    }

}
