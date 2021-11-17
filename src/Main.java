class Main {
    public static void main(String[] u) {
        Boardgame theboard = new Chess();
        ViewControl view = new ViewControl(theboard, 8);
    }
}