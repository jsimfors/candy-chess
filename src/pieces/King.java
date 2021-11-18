package pieces;

public class King implements Piece {

    public King() {
        String imageAddress = "/pieces/imgs/king_c.png";
    }

    @Override
    public String getImageAddress() {
        return imageAddress;
    }
}
