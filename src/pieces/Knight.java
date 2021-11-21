package pieces;

public class Knight implements Piece {
    public String color;

    public Knight(String color) {
        this.color = color;
    }

    @Override
    public String getImageAddress() {
        if(this.color.equals("black")){
            return "/pieces/imgs/knight_c_b.png";
        } else {
            return "/pieces/imgs/knight_c_w.png";
        }
    }
}