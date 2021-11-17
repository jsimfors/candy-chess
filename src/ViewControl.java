// lämpliga import-satser här
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ViewControl extends JFrame implements ActionListener {

    private Boardgame game;
    private int size;
    private Square[][] board;        // Square är subklass till JButton
    private JTextField mess = new JTextField();  // JLabel funkar också

    ViewControl(Boardgame gm, int n) {  // OK med fler parametrar om ni vill!
        this.game = gm;
        this.size = n;
        this.board = new Square[n][n];

        JLabel display; // the display

        JFrame window = new JFrame();
        window.setPreferredSize(new Dimension(840,840));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.CYAN);

        buttons.setLayout(new GridLayout(n, n, 1, 1));
        display = new JLabel(gm.getMessage(), JLabel.LEFT);


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Square button;
                /* White if: i=0 and j = even */
                /* white if: i=1, and */
                if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)) {
                    button = new Square(i, j, Color.white, String.valueOf(gm.getStatus(i, j)));
                } else {
                    button = new Square(i, j, Color.black, String.valueOf(gm.getStatus(i, j)));
                }
                board[i][j] = button;
                board[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (game.move(button.i, button.j)) {
                            updateAll(n);
                        }
                        display.setText(gm.getMessage());
                    }
                });
                buttons.add(board[i][j]);
            }
        }

        // "Assemble" the layout

        Container content = window.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(BorderLayout.NORTH, display);
        content.add(BorderLayout.CENTER, buttons);

        window.pack();
        window.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) { }

    public void updateAll(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j].setText(String.valueOf(game.getStatus(i, j)));
                board[i][j].setColor();
            }
        }
    }


}