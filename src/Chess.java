import pieces.King;
import pieces.Piece;
import pieces.Queen;

import javax.swing.*;

class Chess implements Boardgame {
    private String currentMessage = "No message yet";

    private Piece[][] board = new Piece[8][8];   // spelplanen
    // ^ denna raden va tidigare:
    //private String[][] board = new String[8][8];
    // tänkte att varje ruta ska ju nu innehålla mer info, så kanske kan göra ett pjäs-objekt?
    // se interfacet Piece

    private int iemp, jemp; // index till den tomma rutan

    public Chess() {
        // I framtiden: ändra så det inte endast är en King på alla rutor hehe
        King king = new King();
        Queen queen = new Queen();

        int val = 1;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (val < 17) {
                    this.board[x][y] = king;
                    val++;
                } else if (val > 48){
                    this.board[x][y] = queen;
                    val++;
                } else {
                    val++;
                }
            }
        }
    }

    public String getPieceImage(int i, int j){
        // tidigare metoden: getStatus():
        if(board[i][j] != null){
            return board[i][j].getImageAddress();
        }
        return "";
    }

    public String getMessage(){
        return currentMessage;
    }
}