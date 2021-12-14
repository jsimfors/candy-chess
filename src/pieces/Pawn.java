package pieces;
import game.*;
import java.util.HashSet;

public class Pawn extends Piece {

    public Pawn(PiecePosition piecePosition, User user) {
        super(user, piecePosition);
        this.imageAddress = "src/pieces/imgs/" + this.colour + "/pawn_candyversion.png";
        this.type = "pawn";
    }

    public HashSet<PiecePosition> checkIfOkMovement() {
        HashSet<PiecePosition> result = new HashSet<PiecePosition>();

        // red is moving up (negative steps on board)
        if(colour.equals("red")){
            // check if the pawn is at the initial position
            if (pieceposition.getY() == 6) {
                movingPiece(result, 0, -2);
            }
            movingPiece(result, 0, -1);
            goDiagonal(result, -1, 1);
            goDiagonal(result, -1, -1);
        }

        // blue is moving down (positive steps on board)
        if(colour.equals("blue")){
            // check if the pawn is at the initial position
            if (pieceposition.getY() == 1) {
                movingPiece(result, 0, 2);
            }
            movingPiece(result, 0, 1);
            goDiagonal(result, 1, 1);
            goDiagonal(result, 1, -1);
        }

        ok_movements = result;
        return result;
    }


    protected void movingPiece(HashSet<PiecePosition> result,  int horizontal_move, int vertical_move) {
            Chess chess = Chess.getCurrentBoard();
            PiecePosition goingToPosition = new PiecePosition(pieceposition);
            goingToPosition.updateY(vertical_move);

            if (!(goingToPosition.getY() < 0 || goingToPosition.getY() >= 8)){
                if(goingToPosition.getX() >= 0 && goingToPosition.getX() < 8){
                    if (chess.gettingPieceAtPosition(goingToPosition) == null) {
                        // we can't go straight if there is a piece there: pawns can only eat diagonal!
                        result.add(chess.getPosition(goingToPosition.getY(),
                                goingToPosition.getX()));
                    }
                }
        }
    }

    private void goDiagonal(HashSet<PiecePosition> possible_moves, int horizontal_move, int vertical_move) {
        Chess chess = Chess.getCurrentBoard();
        PiecePosition goingToPosition = new PiecePosition(pieceposition);
        goingToPosition.updateY(horizontal_move);
        goingToPosition.updateX(vertical_move);
        try {
            Piece ate_opponents_candy = chess.gettingPieceAtPosition(goingToPosition);
            if (ate_opponents_candy != null && !ate_opponents_candy.colour.equals(colour)) {
                // We can only go diagonal if there is something to eat at the target location
                // AND if we are eating candy of the opponent's color
                possible_moves.add(chess.getPosition(goingToPosition.getY(),
                        goingToPosition.getX()));
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }
}
