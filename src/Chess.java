import pieces.*;

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
        King king_w = new King("white");
        King king_b = new King("black");

        Queen queen_w = new Queen("white");
        Queen queen_b = new Queen("black");


        int val = 1;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.println(val);
                if(val == 1 || val == 8) {
                    this.board[x][y] = new Rook("black");
                    val++;
                } else if (val == 2 || val == 7){
                    this.board[x][y] = new Knight("black");
                    val++;
                }  else if (val == 3 || val == 6){
                    this.board[x][y] = new Bishop("black");
                    val++;
                } else if (val == 4){
                    this.board[x][y] = new Queen("black");
                    val++;
                } else if (val == 5){
                    this.board[x][y] = new King("black");
                    val++;
                } else if (8 < val && val < 17){
                    this.board[x][y] = new Pawn("black");
                    val++;
                } else if (48 < val && val < 57){
                    this.board[x][y] = new Pawn("white");
                    val++;
                } else if (val == 57 || val == 64) {
                    this.board[x][y] = new Rook("white");
                    val++;
                } else if (val == 58 || val == 63){
                    this.board[x][y] = new Knight("white");
                    val++;
                } else if (val == 59 || val == 62){
                    this.board[x][y] = new Bishop("white");
                    val++;
                } else if (val == 60){
                    this.board[x][y] = new Queen("white");
                    val++;
                } else if (val == 61){
                    this.board[x][y] = new King("white");
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