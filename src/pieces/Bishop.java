package pieces;

public class Bishop implements Piece {
    public String color;

    public Bishop(String color) {
        this.color = color;
    }

    @Override
    public String getImageAddress() {
        if(this.color.equals("black")){
            return "/pieces/imgs/bishop_c_b.png";
        } else {
            return "/pieces/imgs/bishop_c_w.png";
        }
    }
}
