package game;

public class Cell {
    private Piece pieceOn;
    private boolean isObstacle;

    public Cell ( boolean isObstacle ) {
        this.pieceOn = null;
        this.isObstacle = isObstacle;
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

}
