package game;

public class PieceBomb extends Piece {
    public PieceBomb(boolean playerOwned) {
        super(playerOwned, 11);
    }

    protected int attack(Piece victim) {
        //TODO: throw exception (cannot attack)
        return -2; //cannot attack
    }

    protected boolean canMoveTo(int currx, int curry, int x, int y) {
        return false; // cannot move
    }
}
