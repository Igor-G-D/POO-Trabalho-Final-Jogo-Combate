package game;

import java.util.Random;

import javax.swing.CellEditor;

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
                cells [i][j] = new Cell(i,j,isObstacle);
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

    protected Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    protected boolean moveOrAttack (Cell start, Cell destination, boolean playerAction) { // boolean return to make sure a valid move was made, so that the turn can end
        
        if(start.getPiece() == null ) {
            return false;
        }

        if(destination.getIsObstacle()) { // means the destination chosen was an obstacle
            return false;
        }

        if(playerAction && start.getPiece().getPlayerOwned()) { // player acting upon a player piece
            if(destination.getPiece() == null) { // attempting to move
                boolean canMove = start.getPiece().canMoveTo(start.getPosx(), start.getPosy(), destination.getPosx(), destination.getPosy()); // returns if the peice can theoretically move to that space
                
                if(!canMove) {
                    return false; // invalid move
                }
                
                if(start.getPiece().getPieceValue() == 2) { // means it is a soldier, need to check if there are pieces or an obstacle in between
                    int checkFrom;
                    int checkTo;
                    if(start.getPosx() == destination.getPosx()) { // means its moving in the y axis
                        if(start.getPosy() > destination.getPosy()) {
                            checkFrom = destination.getPosy();
                            checkTo = destination.getPosy();
                        }
                        for(int i = checkFrom+1 ; i < checkTo; i++) { // checks between two positions, if the start is 2 and the destination is 5, checks positions 3 and 4
                            if(this.cells[start.getPosx()][i].getIsObstacle() || this.cells[start.getPosx()][i].getPiece() != null) {
                                return false; // if there is a place in between the start and the destination that is an obstacle or a piece, means that the move is invalid
                            }
                        }
                    } else {// means its moving in the x axis
                        if(start.getPosx() > destination.getPosx()) {
                            checkFrom = destination.getPosx();
                            checkTo = destination.getPosx();
                        }
                        for(int i = checkFrom+1 ; i < checkTo; i++) { // checks between two positions, if the start is 2 and the destination is 5, checks positions 3 and 4
                            if(this.cells[i][start.getPosy()].getIsObstacle() || this.cells[i][start.getPosy()].getPiece() != null) {
                                return false; // if there is a place in between the start and the destination that is an obstacle or a piece, means that the move is invalid
                            }
                        }
                    }
                } //  end testing if its a soldier
            }
        } else if ( !playerAction && !start.getPiece().getPlayerOwned() ) {
            
        }

        

        }
    }
    


}
