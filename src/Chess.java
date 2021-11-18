import pieces.King;
import pieces.Piece;

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
        King thePiece = new King();

        int val = 1;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (val < 64) {
                    this.board[x][y] = thePiece;
                    val++;
                }
            }
        }
        this.board[7][7] = thePiece;


        this.iemp = 7;
        this.jemp = 7;

        for(int i=0; i<200; i++){
            int newemp = (int) ((Math.random()*(4))+1); // slumpa mellan fall 0-3
            if(newemp == 1){
                // [iemp, jemp+1]
                move(iemp, jemp+1);
            } else if (newemp == 2 ){
                // [iemp, jemp-1]
                move(iemp, jemp-1);
            } else if (newemp == 3 ){
                // [iemp, jemp-1]
                move(iemp+1, jemp);
            } else if (newemp == 4 ){
                // [iemp, jemp-1]
                move(iemp-1, jemp);
            }

        }
    }

    public boolean move(int i, int j) {
        // [4,3], i = 4, j = 3.

        // kolla på i och j ligger på spelbrädet:
        // ska vara mellan index 0 och 3.
        if((i >= 0 && i < 8) && (j >= 0 && j < 8)){
            // kolla om index i,j ligger bredvid en tom ruta
            // [i-1, j] is iemp, jemp
            if(i-1 == iemp && j == jemp){
                // rutan till vänster är tom
                // vi vill byta plats på:
                this.board[i-1][j] = this.board[i][j];
                this.board[i][j] = null;
                iemp = i;
                jemp = j;

            } else if (i+1 == iemp && j == jemp){
                // rutan till höger är tom
                this.board[i+1][j] = this.board[i][j];
                this.board[i][j] = null;
                iemp = i;
                jemp = j;
            } else if (i == iemp && j-1 == jemp){
                // rutan uppåt är tom
                this.board[i][j-1] = this.board[i][j];
                this.board[i][j] = null;
                iemp = i;
                jemp = j;
            } else if (i == iemp && j+1 == jemp){
                // rutan nedåt är tom
                this.board[i][j+1] = this.board[i][j];
                this.board[i][j] = null;
                iemp = i;
                jemp = j;
            } else {
                // ligger inte brevid tom ruta
                currentMessage = "Välj en ruta bredvid en tom ruta";
                return false;
            }

        } else {
            currentMessage = "Välj en ruta på spelplanen";
            return false;
        }

        currentMessage = "Ok";
        return true;
    }

    public String getPieceImage(int i, int j){
        // tidigare metoden: getStatus():

        // i framtiden vill vi nog snarare skriva typ:
        // return board[i][j].getImageAddress();

        return "/pieces/imgs/king_c.png";
    }

    public String getMessage(){
        return currentMessage;
    }
}