package game;

public class Cell {
    private int posx;
    private int posy;
    private Piece pieceOn;
    private boolean isObstacle;

    public Cell ( int posx, int posy, boolean isObstacle ) {
        this.pieceOn = null;
        this.isObstacle = isObstacle;
        this.posx = posx;
        this.posy = posy;
    }

    protected void removePiece() {
        this.pieceOn = null;
    }

    protected void placePiece (Piece piece) {
        //TODO: error to catch when there is already a piece occupying t

        this.pieceOn = piece;
    }

    protected Piece getPiece () {
        return this.pieceOn;
    } 

    protected boolean getIsObstacle() {
        return this.isObstacle;

    }

    protected int getPosx() {
        return this.posx;
    }

    protected int getPosy() {
        return this.posy;
    }

}
