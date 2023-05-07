package game;

import java.util.Random;

import java.util.ArrayList;

import java.util.Collections;

import java.lang.Math;

//import javax.swing.CellEditor;

public class Board {
    private Cell cells[][] = new Cell[5][5];
    private RemovedPieces removedPieces; //TODO: start removed pieces filled, then place all pieces on the board to start the game
    private Random RNG;

    public Board () {
        this.RNG = new Random(System.currentTimeMillis());
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

        removedPieces = new RemovedPieces(false);

    }

    public Board (Preset preset) {

        int [][] presetValues = preset.getPreset();
        for(int i = 0 ; i < 5 ; i ++) {
            boolean playerSide = false;
            if(i > 2) {
                playerSide = true;
            }
            for(int j = 0; j < 5 ; j++) {
                if(presetValues[i][j] == -2) {
                    cells[i][j] = new Cell(i,j,true);
                } else {
                    cells[i][j] = new Cell(i,j,false);
                }

                if (presetValues[i][j] == -1) {
                    continue;
                }

                if (presetValues[i][j] == 0) {
                    cells[i][j].placePiece(new PieceFlag(playerSide));
                } else if (presetValues[i][j] == 2) {
                    cells[i][j].placePiece(new PieceSoldier(playerSide));
                } else if (presetValues[i][j] == 3) {
                    cells[i][j].placePiece(new PieceCorporal(playerSide));
                } else if (presetValues[i][j] == 10) {
                    cells[i][j].placePiece(new PieceMarshall(playerSide));
                } else if (presetValues[i][j] == 11) {
                    cells[i][j].placePiece(new PieceBomb(playerSide));
                } 
            }
        }
        removedPieces = new RemovedPieces(true);
    } 



    // public void clearBoard() { 
    //     for(int i = 0 ; i < 5 ; i ++) {
    //         for(int j = 0; j < 5 ; j++) {
    //             removedPieces.addPiece(this.cells[i][j].getPiece());
    //             this.cells[i][j].removePiece();
    //         }
    //     }
    // }

    public boolean playerPlacePieceStart(int x, int y, Piece piece) { //always used by the player, returns false if it couldn't place, true if it was placed
        //TODO: handle exception thrown by Cell.placePiece() when trying to place in tile that is occupied, or is an obstacle

        if(!validPositionToPlace(x, y, true)) {
            return false;
        }

        Piece current = null;

        if(piece instanceof PieceSpy) {
            current = removedPieces.getPiecesSet(true).removeSpy(); // will be null if the piece was already placed
        }
        if(piece instanceof PieceSoldier) {
            current = removedPieces.getPiecesSet(true).removeSoldier(); // will be null if all soldier pieces were already placed
        }
        if(piece instanceof PieceCorporal) {
            current = removedPieces.getPiecesSet(true).removeCorporal();
        }
        if(piece instanceof PieceMarshall) {
            current = removedPieces.getPiecesSet(true).removeMarshall();
        }
        if(piece instanceof PieceBomb) {
            current = removedPieces.getPiecesSet(true).removeBomb();
        }
        if(piece instanceof PieceFlag) {
            current = removedPieces.getPiecesSet(true).removeFlag();
        }
        if(current == null) {
            return false;
        }

        cells[x][y].placePiece(current); 
        return true;

    }

    public boolean playerRemovePieceStart(int x, int y) { // can only be used during the start of the game when the player is setting up their pieces. returns true if the piece was removed, false otherwise
        if (cells[x][y].getPiece() != null) {
            removedPieces.addPiece(cells[x][y].getPiece());
            cells[x][y].removePiece();
            return true;
        }
        return false;
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

    public Preset setStartRandom() {
        randomizePositions(3, true); //randomize player pieces;
        randomizePositions(0, false); //randomize enemy positions

        return new Preset(this);
        //TODO: exceptionn for when the board isn't empty
    }

    public void setStartPlayerChoice() {
        randomizePositions(3, false); //randomize enemy positions

        //TODO: allow player to choose the position for their own pieces;
    }

    private void randomizePositions (int posx, boolean playerPieces) { //what line it randomizes from. if 0, will randomize over rows 0 and 1. if 3, will randomize over rows 3 and 4
        
        while (removedPieces.getPiecesSet(playerPieces).getFlag() != null) {

            int randomx = RNG.nextInt(2); //choose which row to place the flag
            int flagPosx = posx + randomx; 
            int flagPosy = RNG.nextInt(5); // choose which column to place in the flag

            if(flagPosx == 3 || flagPosx == 1) { // if not on the very back row of each side
                if (isAdjacent(cells[flagPosx][flagPosy], findObstacle())) {
                    cells[flagPosx][flagPosy].placePiece(removedPieces.getPiecesSet(playerPieces).removeFlag()); // removes flag from removedPieces and sets it on the board
                    if(flagPosy == 0 || flagPosy == 4) { //means that the flag is against one of the sides
                        int bombPosy = (flagPosy == 0) ?  flagPosy+1 : flagPosy-1; // if flagposy == 0, bombposy = 1, else, bombposy = 3
                        int bombPosx = (flagPosx == 3) ?  flagPosx+1 : flagPosx-1; // if flagposx == 0, bombposx = 1, else, bombposx = 3

                        cells[flagPosx][bombPosy].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb());
                        cells[bombPosx][flagPosy].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb());
                    } else {
                        cells[flagPosx][flagPosy+1].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb()); // place bombs around the sides of the flag
                        cells[flagPosx][flagPosy-1].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb());
                    }
                } else {
                    continue; //skips current loop
                }
            } else {
                if(flagPosy == 0 || flagPosy == 4) { //means that the flag is against one of the sides
                    int bombPosy = (flagPosy == 0) ?  flagPosy+1 : flagPosy-1; // if flagposy == 0, bombposy = 1, else, bombposy = 3
                    int bombPosx = (flagPosx == 0) ?  flagPosx+1 : flagPosx-1; // if flagposx == 0, bombposx = 1, else, bombposx = 3
                    cells[flagPosx][flagPosy].placePiece(removedPieces.getPiecesSet(playerPieces).removeFlag());
                    cells[flagPosx][bombPosy].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb());
                    cells[bombPosx][flagPosy].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb());
                } else {
                    int bombPosx = (flagPosx == 0) ?  flagPosx+1 : flagPosx-1; // if flagposx == 0, bombposx = 1, else, bombposx = 3
                    cells[flagPosx][flagPosy].placePiece(removedPieces.getPiecesSet(playerPieces).removeFlag());
                    cells[bombPosx][flagPosy].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb()); // always place a bomb at the front of the flag

                    int randomPos = RNG.nextInt(2);

                    if (randomPos == 0) {
                        cells[flagPosx][flagPosy-1].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb());
                    } else {
                        cells[flagPosx][flagPosy+1].placePiece(removedPieces.getPiecesSet(playerPieces).removeBomb()); // place bombs around one of the sides of the flag

                    }
                }
            }

            //now the flag and the bombs are in position, needs to iterate through the rest of the board;

            ArrayList<Piece> piecesLeft = removedPieces.getPiecesSet(playerPieces).returnPiecesLeft(true);
            Collections.shuffle(piecesLeft, RNG); //shuffles the pieces that are to be put on the board

            for(int i = posx ; i < posx+2 ; i++) {
                for(int j = 0 ; j < 5 ; j++) {
                    if(cells[i][j].getPiece() == null) {
                        cells[i][j].placePiece(piecesLeft.get(0));
                        piecesLeft.remove(0);
                    }
                }
            }

            //TODO: throw exception when piecesLeft isn't empty after the last for
        }
    }

    private Cell findObstacle() {
        for(int i=0;i<5;i++) {
            if(cells[2][i].getIsObstacle()) {
                return cells[2][i];
            }
        }
        return null;
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

    private boolean validPositionToPlace(int x, int y, boolean playerSide) {
        if(cells[x][y].getPiece() == null && !cells[x][y].getIsObstacle() && (x == 0 || x == 1)) {
            return true;
        } else {
            return false;
        }
    }
}
