package game;

public class PieceFlag extends Piece {

    public PieceFlag(boolean playerOwned) {
        super(playerOwned, 0);
    }

    public int attack(Piece victim) {
         //TODO: throw exception (cannot attack)
        return -2; //cannot attack
    }

    public boolean canMoveTo(int currx, int curry, int x, int y) {
        return false; // cannot move
    }

}
