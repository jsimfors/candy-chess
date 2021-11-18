import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    public Color c1;
    public String s1;
    public int i;
    public int j;

    Square(int i, int j, Color c1, String s1) {
        this.c1 = c1;
        this.s1 = s1;
        this.i = i;
        this.j = j;

        setText(s1);
        this.setColor();
        this.setOpaque(true);
        this.setBorderPainted(false);


    }

    public void setColor() {
        this.s1 = this.getText();
        if (this.s1.equals("  ")) {
            setBackground(Color.decode("#ffeb99"));
        } else {
            setBackground(c1);
        }
    }

}
