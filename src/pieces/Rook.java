package pieces;

public class Rook implements Piece {
    public String color;

    public Rook(String color) {
        this.color = color;
    }

    @Override
    public String getImageAddress() {
        if(this.color.equals("black")){
            return "/pieces/imgs/rook_c_b.png";
        } else {
            return "/pieces/imgs/rook_c_w.png";
        }
    }
}