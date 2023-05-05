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

    public void removePiece() {
        this.pieceOn = null;
    }

    public void placePiece (Piece piece) {
        //TODO: error to catch when there is already a piece occupying t

        this.pieceOn = piece;
    }

    public Piece getPiece () {
        return this.pieceOn;
    } 

    public boolean getIsObstacle() {
        return this.isObstacle;

    }

    public int getPosx() {
        return this.posx;
    }

    public int getPosy() {
        return this.posy;
    }

}
