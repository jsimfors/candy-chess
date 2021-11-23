package pieces;

public interface Piece {
    /* A class for status of a specific piece. For every piece we need to:
    Get image (string of image address?)
    Move the piece
    Get the current position?
    Save info about HOW this piece can move

    * tänkte typ att alla pjäser kan ha en egen klass, som håller koll på hur den pjäsen får flyttas osv.
    * Men alla pjäser extendera detta interface.
    *  */

    public String getImageAddress();
    // public String checkPossibleMoves();

    // move();
}