package pieces;

public class Pawn implements Piece {
    public String color;

    public Pawn(String color) {
        this.color = color;
    }

    @Override
    public String getImageAddress() {
        if(this.color.equals("black")){
            return "/pieces/imgs/pawn_c_b.png";
        } else {
            return "/pieces/imgs/pawn_c_w.png";
        }
    }
}