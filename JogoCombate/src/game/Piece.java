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

    protected abstract int attack( Piece victim ); // -1 if the agressor dies, 0 if both dies, 1 if victim dies
    protected abstract boolean canMoveTo(int currx, int curry, int x, int y); // returns if the piece can move to that position TODO: throws exception for when the move is invalid for the piece

    protected int getPieceValue() {
        return this.value;
    }

    protected void setVisibility(boolean visibility) {
        this.isVisible = visibility;
    }

    protected boolean getPlayerOwned () {
        return this.playerOwned;
    }

    protected boolean getIsVisible () {
        return this.isVisible;
    }

}
