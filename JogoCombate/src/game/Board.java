package game;

import java.util.Random;

import java.lang.Math;

//import javax.swing.CellEditor;

public class Board {
    private Cell cells[][];
    private RemovedPieces removedPieces; //TODO: start removed pieces filled, then place all pieces on the board to start the game

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

        removedPieces = new RemovedPieces();
    }

    public void clearBoard() {
        for(int i = 0 ; i < 5 ; i ++) {
            for(int j = 0; j < 5 ; j++) {
                this.cells[i][j].removePiece();
            }
        }
    }
    public void placePiece(int x, int y, Piece piece) {
        //TODO: handle exception thrown by Cell.placePIece() when trying to place in tile that is occupied, or is an obstacle
        //TODO: at the start of the game when player is placing pieces, the exception for when its placed out of bounds is handled elsewhere

        cells[x][y].placePiece(piece);

    }

    public void setDebug(boolean debug) {
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

    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    public boolean moveOrAttack (Cell start, Cell destination, boolean playerAction) { // boolean return to make sure a valid move was made, so that the turn can end
        
        if(start.getPiece() == null ) {
            return false;
        }

        if(destination.getIsObstacle()) { // means the destination chosen was an obstacle
            return false;
        }

        if (playerAction && !start.getPiece().getPlayerOwned()) {
            return false; // TODO: turn this into an exception (player can't act on a non player piece)
        }
        
        if (start.getPiece() instanceof PieceBomb || start.getPiece() instanceof PieceFlag) {
            return false; // if its a bomb or flag, it can't move or attack
        } 

        if(destination.getPiece() == null) { // attempting to move
            boolean canMove = start.getPiece().canMoveTo(start.getPosx(), start.getPosy(), destination.getPosx(), destination.getPosy()); // returns if the peice can theoretically move to that space
            
            if(!canMove) {
                return false; // invalid move
            }
            
            if(start.getPiece().getPieceValue() == 2) { // means it is a soldier, need to check if there are pieces or an obstacle in between
                int checkFrom, checkTo;
                if(start.getPosx() == destination.getPosx()) { // means its moving in the y axis
                    if(start.getPosy() > destination.getPosy()) { // always check from the lower value to the higher
                        checkFrom = destination.getPosy();
                        checkTo = start.getPosy();
                    } else {
                        checkFrom = start.getPosy();
                        checkTo = destination.getPosy();
                    }
                    for(int i = checkFrom+1 ; i < checkTo; i++) { // checks between two positions, if the start is 2 and the destination is 5, checks positions 3 and 4
                        if(this.cells[start.getPosx()][i].getIsObstacle() || this.cells[start.getPosx()][i].getPiece() != null) {
                            return false; // if there is a place in between the start and the destination that is an obstacle or a piece, means that the move is invalid
                        }
                    }
                } else {// means its moving in the x axis
                    if(start.getPosx() > destination.getPosx()) {// always check from the lower value to the higher
                        checkFrom = destination.getPosx();
                        checkTo = destination.getPosx();
                    } else {
                        checkFrom = start.getPosx();
                        checkTo = destination.getPosx();
                    }
                    for(int i = checkFrom+1 ; i < checkTo; i++) { // checks between two positions, if the start is 2 and the destination is 5, checks positions 3 and 4
                        if(this.cells[i][start.getPosy()].getIsObstacle() || this.cells[i][start.getPosy()].getPiece() != null) {
                            return false; // if there is a place in between the start and the destination that is an obstacle or a piece, means that the move is invalid
                        }
                    }
                }
            } //  end testing if its a soldier

            movePiece(start, destination); //if the piece can move, and there are no objects in between, the piece is removed from the starting position and placed on the destination
            return true;
        } else if (isAdjacent(start, destination)) {// add removed piece to the removed pieces class // if there is a piece in the destination, means it is attacking
            if (destination.getPiece().getPlayerOwned() == start.getPiece().getPlayerOwned()) {
                return false; //TODO: make this into an exception (attacking piece of the same team)
            }

            int result = start.getPiece().attack(destination.getPiece());

            if (result == -1) { // attacking piece lost
                start.getPiece().setVisibility(true);
                destination.getPiece().setVisibility(true);
                
                removedPieces.addPiece(start.getPiece()); // add removed piece to the removed pieces class
                start.removePiece();
            } else if (result == 0) { // tie. both get removed
                start.getPiece().setVisibility(true);
                destination.getPiece().setVisibility(true);
                

                removedPieces.addPiece(start.getPiece()); // add removed piece to the removed pieces class
                start.removePiece();
                removedPieces.addPiece(destination.getPiece()); // add removed piece to the removed pieces class
                destination.removePiece();
            } else { // only other option is result == 1, means that the attacking piece won
                start.getPiece().setVisibility(true);
                destination.getPiece().setVisibility(true);

                removedPieces.addPiece(destination.getPiece()); // add removed piece to the removed pieces class
                destination.removePiece();
                movePiece(start, destination);// piece that attacks occupies the space of the piece it won against
            }

            //TODO: Check for game end using the method removedPieces.gameEnd()
            // the game can only end after an attack is made, moving will not change the state of the game
        }

        return false; //if it reaches this condition, means that it is trying to attack a piece that is non adjacent, which is an invalid move
    }

    private void movePiece(Cell start, Cell destination) {
        destination.placePiece(start.getPiece());
        start.removePiece();
    } 

    private boolean isAdjacent(Cell start, Cell destination) {
        if(Math.abs(start.getPosx() - destination.getPosx()) + Math.abs(start.getPosy() - destination.getPosy()) == 1) { // if adjacent, |x(start) - x(dest)| + |y(start) - y(dest)| since one must be 1, and the other 0
            return true;
        } else {
            return false;
        }
    }
}
