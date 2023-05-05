package game;

public abstract class Piece {
    private int value;
    private boolean playerOwned;
    private boolean isVisible;

    public Piece (boolean playerOwned, int value ) {
        // value is defined by the constructors of the children
        this.playerOwned = playerOwned;
        this.isVisible = playerOwned;
        this.value = value;
    }

    public abstract int attack( Piece victim ); // -1 if the agressor dies, 0 if both dies, 1 if victim dies
    public abstract boolean canMoveTo(int currx, int curry, int x, int y); // returns if the piece can move to that position TODO: throws exception for when the move is invalid for the piece

    public int getPieceValue() {
        return this.value;
    }

    public void setVisibility(boolean visibility) {
        this.isVisible = visibility;
    }

    public boolean getPlayerOwned () {
        return this.playerOwned;
    }

    public boolean getIsVisible () {
        return this.isVisible;
    }

}
