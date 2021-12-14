package pieces;
import game.Chess;
import game.PiecePosition;
import game.User;

import java.util.HashSet;

public class King extends Piece {

    public King(PiecePosition piecePosition, User user) {
        super(user, piecePosition);
        this.imageAddress = "src/pieces/imgs/" + this.colour + "/king_candyversion.png";
        this.type = "king";
    }


    public HashSet<PiecePosition> checkIfOkMovement() {
        HashSet<PiecePosition> possible_moves = new HashSet<PiecePosition>();

        movingPiece(possible_moves, 1, 0);
        movingPiece(possible_moves, -1, 0);
        movingPiece(possible_moves, 0, 1);

        movingPiece(possible_moves, 0, -1);
        movingPiece(possible_moves, 1, 1);
        movingPiece(possible_moves, 1, -1);
        movingPiece(possible_moves, -1, 1);

        movingPiece(possible_moves, -1, -1);

        // adding all possible directions for the rook
        ok_movements = possible_moves;
        return possible_moves;

    }


    protected void movingPiece(HashSet<PiecePosition> possible_moves, int horizontal_move, int vertical_move) {
        // System.out.println("Ok movements for rook are: "  int horizontal_move, int vertical_move);
        // boolean canGoDiagonal = diagonal_move == 1;

        Chess chess = Chess.getCurrentBoard();
        // The current position:
        PiecePosition moving_to = new PiecePosition(pieceposition);
        // this we are moving to by adding horizontal and vertical move:
        moving_to.updateY(horizontal_move);
        moving_to.updateX(vertical_move);
        try {

            // check if there is candy at given position:
            Piece ate_opponents_candy = chess.gettingPieceAtPosition(moving_to);

            // no while-loop since knight only moves once

            // we can only move here if it is empty, or if there is one of
            // opponents candy here
            if (ate_opponents_candy == null) {
                // none of opponents candy at this position :(
                possible_moves.add(chess.getPosition(moving_to.getY(),
                        moving_to.getX()));
            } else {
                if (!ate_opponents_candy.colour.equals(colour)) {
                    // let's see that we did not eat our own candy
                    possible_moves.add(chess.getPosition(
                            moving_to.getY(),
                            moving_to.getX()));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // king can't move outside of the board either!
        }

    }

}
