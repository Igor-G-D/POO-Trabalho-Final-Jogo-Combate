package game;

public class PieceSoldier extends Piece{
    
    public PieceSoldier(boolean playerOwned) {
        super(playerOwned, 2);
    }

    public int attack(Piece victim) {
        if (victim.getPieceValue() < 2) {
            return 1;
        } else if (victim.getPieceValue() == 2) {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean canMoveTo(int currx, int curry, int x, int y) { //TODO: check if there are any pieces "in the way"
        if ((currx == x && curry != y) || (curry == y && currx != x)) {
            return true;
        } else {
            return false;
        }
    }
    
}
