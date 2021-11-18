import javax.swing.*;
import java.awt.*;
import java.net.URL;

import java.lang.*;

public class Square extends JButton {
    public Color c1;
    public String imageAddress;
    public int i;
    public int j;

    Square(int i, int j, Color c1, String imageAddress) {
        this.c1 = c1;
        this.i = i;
        this.j = j;
        this.imageAddress = imageAddress;
        this.setColor();
        this.setOpaque(true);
        this.setBorderPainted(false);
    }

    public void setColor() {
        ImageIcon icon = new ImageIcon(getClass().getResource(this.imageAddress));
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(resizedImage));
        setBackground(c1);
    }

}
