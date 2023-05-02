package game;

import java.util.Random;

public class Board {
    private Cell cells[][];

    public Board () {
        Random RNG = new Random();
        int obstaclePos = RNG.nextInt(5); // random number between 0 and 4 (inclusive)
        // i = rows, j = columns
        // the only non player owned row is row 2, so the obstacle must be in that row
        for(int i = 0 ; i < 5 ; i ++) {
            for(int j = 0; j < 5 ; j++) {
                boolean isObstacle = false;
                if ( i == 2 && j == obstaclePos ) {
                    isObstacle = true;
                }
                cells [i][j] = new Cell(isObstacle);
            }
        }
    }
    protected void clearBoard() {
        for(int i = 0 ; i < 5 ; i ++) {
            for(int j = 0; j < 5 ; j++) {
                this.cells[i][j].removePiece();
            }
        }
    }
    protected void placePiece(int x, int y, Piece piece) {
        //TODO: handle exception thrown by Cell.placePIece() when trying to place in tile that is occupied, or is an obstacle
        //TODO: at the start of the game when player is placing pieces, the exception for when its placed out of bounds is handled elsewhere

        cells[x][y].placePiece(piece);

    }

    protected void setDebug(boolean debug) {
        for(int i = 0 ; i < 5 ; i ++) {
            for(int j = 0; j < 5 ; j++) {
                Piece piece = this.cells[i][j].getPiece();
                if(piece != null) {
                    if(!piece.getPlayerOwned()) {
                        piece.setVisibility(debug); // setting visibility equal to debug for all non player owned pieces
                    }
                }
                
            }
        }
    }

    


}
