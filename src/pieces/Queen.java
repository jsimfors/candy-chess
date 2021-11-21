package pieces;

public class Queen implements Piece {
    public String color;

    public Queen(String color) {
        this.color = color;
    }

    @Override
    public String getImageAddress() {
        if(this.color.equals("black")){
            return "/pieces/imgs/queen_c_b.png";
        } else {
            return "/pieces/imgs/queen_c_w.png";
        }
    }
}