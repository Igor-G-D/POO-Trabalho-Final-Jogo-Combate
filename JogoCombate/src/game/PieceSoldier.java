package game;

public class PieceSoldier extends Piece{
    
    public PieceSoldier(boolean playerOwned) {
        super(playerOwned, 2);
    }

    protected int attack(Piece victim) {
        if (victim.getPieceValue() < 2) {
            return 1;
        } else if (victim.getPieceValue() == 2) {
            return 0;
        } else {
            return -1;
        }
    }

    protected boolean canMoveTo(int currx, int curry, int x, int y) {
        if ((currx == x && curry != y) || (curry == y && currx != x)) {
            return true;
        } else {
            return false;
        }
    }
    
}
