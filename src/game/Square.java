package game;

import pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    /** A button (square) on the board, keeps track of the front end part: what color this square is, and what
     * image should be displayed **/
    public Color c1;
    public int i;
    public int j;

    Square(int i, int j, Color c1, PiecePosition piecePosition) {
        this.c1 = c1;
        this.i = i;
        this.j = j;
        this.setColor();
        this.putClientProperty("location", piecePosition);
    }


    public void setColor() {
        this.setBackground(c1);
        this.putClientProperty("color",c1);
        this.setOpaque(true);
        this.setBorderPainted(false);

    };

    public void addPieceToSquare(Piece piece) {
        ImageIcon icon = new ImageIcon(piece.getImageAddress());
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(resizedImage));
    }

}
