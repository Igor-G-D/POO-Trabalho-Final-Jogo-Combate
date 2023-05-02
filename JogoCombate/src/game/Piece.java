package game;

public abstract class Piece {
    private int value;
    private boolean playerOwned;
    private boolean isVisible;

    public Piece (boolean playerOwned) {
        // value is defined by the constructors of the children
        this.playerOwned = playerOwned;
        this.isVisible = playerOwned;
    }

    protected abstract boolean attack( Piece victim );
    protected abstract boolean moveTo(int x, int y);

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
