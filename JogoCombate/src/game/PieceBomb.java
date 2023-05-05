package game;

public class PieceBomb extends Piece {
    public PieceBomb(boolean playerOwned) {
        super(playerOwned, 11);
    }

    public int attack(Piece victim) {
        return -2; //cannot attack
    }

    public boolean canMoveTo(int currx, int curry, int x, int y) {
        return false; // cannot move
    }
}
