package game;

public class PiecePosition {
    /**
     * Piece Position: keeps track of a (x,y) position on the board, and what piece stands there
     * Called PiecePosition and not Position to not collide with the built in Java.swing Position object
     * because that caused some issues :-) https://docs.oracle.com/javase/7/docs/api/javax/swing/text/Position.html
     **/
    private int y;
    private int x;

    public PiecePosition(int y, int x) {
        // x-value = move horizontal = column
        // y-value = move vertical = row
        this.y = y;
        this.x = x;
    }

    public PiecePosition(PiecePosition pos) {
        this.y = pos.y;
        this.x = pos.x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateX(int addition) {
        x += addition;
    }

    public void updateY(int addition) {
        y += addition;
    }

}
