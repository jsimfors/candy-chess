package pieces;

import game.Chess;
import game.PiecePosition;
import game.User;

import java.util.HashSet;

public class Queen extends Piece {


    public Queen(PiecePosition piecePosition, User user) {
        super(user, piecePosition);
        this.imageAddress = "src/pieces/imgs/" + this.colour + "/queen_candyversion.png";
        this.type = "queen";
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


        // adding all possible directions for the queen
        ok_movements = possible_moves;
        return possible_moves;

    }

    protected void movingPiece(HashSet<PiecePosition> possible_moves, int horizontal_move, int vertical_move) {
        // System.out.println("Ok movements for queen are: " int horizontal_move, int vertical_move);

        Chess chess = Chess.getCurrentBoard();
        PiecePosition moving_to = new PiecePosition(pieceposition);

        while (true){
            // since we can go more than one step at the time
            moving_to.updateY(horizontal_move);
            moving_to.updateX(vertical_move);
            try {
                Piece ate_opponents_candy = chess.gettingPieceAtPosition(moving_to);
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
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
    }

}
