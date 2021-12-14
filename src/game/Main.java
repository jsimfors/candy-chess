package game;

class Main {
    public static void main(String[] u) {
        Chess chess = Chess.startTheGame();
        chess.placePiecesOnBoard();
        ViewControl view = ViewControl.getCurrentWindow();
    }
}