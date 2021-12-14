package game;

import pieces.Piece;

public interface Boardgame {
    /** Interface for the Boardgame
     * Basis of the interface comes from our Lab 2 (FifteenModel) but we have extended it further.
     * **/

    // Logic for game start:
    void placePiecesOnBoard(); // we run this from main, after game initialization.

    // Logic for the message displayed in front-end
    String getMessage();  // returnera string med status (eller felmeddelande) avseende senaste drag
    void setMessage(String value);

    // Logic for pieces:
    PiecePosition getPosition(int row, int col);
    Piece gettingPieceAtPosition(PiecePosition piecePosition);
    boolean removingPiece(PiecePosition piecePosition);
    boolean addingPiece(Piece piece, PiecePosition piecePosition);
    boolean movingPiece(Piece piece, PiecePosition piecePosition);  //ger true om draget gick bra, annars false
}