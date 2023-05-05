package game;

import java.lang.Math;

public class PieceMarshall extends Piece {

    public PieceMarshall(boolean playerOwned) {
        super(playerOwned, 10);
    }

    public int attack(Piece victim) {
        if (victim.getPieceValue() < 10) {
            return 1;
        } else if (victim.getPieceValue() == 10) {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean canMoveTo(int currx, int curry, int x, int y) {
        if(Math.abs(currx - x) == 1 && curry == y) {// moving forward or back 1 position
            return true;
        } else if (currx == x && Math.abs(curry - y) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
