package pieces;

public class King implements Piece {
    public String color;

    public King(String color) {
        this.color = color;
    }

    @Override
    public String getImageAddress() {
        if(this.color.equals("black")){
            return "/pieces/imgs/king_c_b.png";
        } else {
            return "/pieces/imgs/king_c_w.png";
        }
    }
}
