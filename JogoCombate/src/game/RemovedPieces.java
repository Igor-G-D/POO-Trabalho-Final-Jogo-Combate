package game;

public class RemovedPieces {
    private PlayerPieceSet enemyPieces;
    private PlayerPieceSet playerPieces;

    RemovedPieces(boolean empty) {
        enemyPieces = new PlayerPieceSet(false, empty);
        playerPieces = new PlayerPieceSet(true, empty);
    }

    public void addPiece(Piece piece) {
        if(piece.getPlayerOwned()) {
            if(piece instanceof PieceSpy) {
                playerPieces.addSpy(); // TODO: Handle error thrown by addSpy
            } else if(piece instanceof PieceSoldier) {
                playerPieces.addSoldier(); // TODO: Handle error thrown by addSoldier
            } else if(piece instanceof PieceCorporal) {
                playerPieces.addCorporal(); // TODO: Handle error thrown by addCorporal
            } else if(piece instanceof PieceMarshall) {
                playerPieces.addMarshall(); // TODO: Handle error thrown by addMarshall
            } else if(piece instanceof PieceBomb) {
                playerPieces.addBomb(); // TODO: Handle error thrown by addBomb
            } else if(piece instanceof PieceFlag) {
                playerPieces.addFlag(); // TODO: Handle error thrown by addFlag
            } 
        } else {
            if(piece instanceof PieceSpy) {
                enemyPieces.addSpy(); // TODO: Handle error thrown by addSpy
            } else if(piece instanceof PieceSoldier) {
                enemyPieces.addSoldier(); // TODO: Handle error thrown by addSoldier
            } else if(piece instanceof PieceCorporal) {
                enemyPieces.addCorporal(); // TODO: Handle error thrown by addCorporal
            } else if(piece instanceof PieceMarshall) {
                enemyPieces.addMarshall(); // TODO: Handle error thrown by addMarshall
            } else if(piece instanceof PieceBomb) {
                enemyPieces.addBomb(); // TODO: Handle error thrown by addBomb
            } else if(piece instanceof PieceFlag) {
                enemyPieces.addFlag(); // TODO: Handle error thrown by addFlag
            } 
        }
    }

    public boolean validGameStart () { // returns false if there are still pieces left to place, true if all pieces are on the board and it can start
        if(enemyPieces.returnPiecesLeft(false).size() == 0 && playerPieces.returnPiecesLeft(false).size() == 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public int gameEnd() { // -1 = enemy win, 0 = continue game, 1 = player win, 2 = tie (no pieces that can move are left)
        //TODO: add exception for when both enemy and player has their flags captured (invalid state)
        
        if ((!checkMovablePieces(enemyPieces)) && (!checkMovablePieces(playerPieces))) { // if no movable pieces are left on both sides
            return 2; // tie
        } else if(playerPieces.getFlag() != null || (!checkMovablePieces(playerPieces))) { // if the player's flag was removed or if enemy has no movable pieces
            return -1; // defeat
        } else if (enemyPieces.getFlag() != null || (!checkMovablePieces(enemyPieces))) { // if the enemy flag was removed or if if player has no movable pieces
            return 1; // victory
        } else {
            return 0; // game isn't over
        }
    }

    public PlayerPieceSet getPiecesSet(boolean playerOwned) {
        
        if(playerOwned) {
            return playerPieces;
        } else {
            return enemyPieces;
        }

    }

    private boolean checkMovablePieces(PlayerPieceSet pieces) { // true if there are movable pieces, false if not
        Piece spy = pieces.getSpy();
        Piece soldier[] = pieces.getSoldier();
        Piece corporal[] = pieces.getCorporal();
        Piece marshall = pieces.getMarshall();

        if (spy == null) {
            return true;
        }
        for (int i=0;i<3;i++) {
            if(soldier[i] == null) {
                return true;
            }
        }
        for (int i=0;i<2;i++) {
            if(corporal[i] == null) {
                return true;
            }
        }
        if(marshall == null) {
            return true;
        }

        return false; // means there are no movable pieces left in the board, they are all stored in this class
    }

    public <T> int numberPiecesRemoved(Class<T> type, boolean playerOwned) {
        if(type.equals(PieceSpy.class)) {
            if (getPiecesSet(playerOwned).getSpy() == null) {
                return 0;
            } else {
                return 1;
            }
        } else if(type.equals(PieceSoldier.class)) {
            int temp = 0;
            for(int i = 0 ; i < 3 ; i++) {
                if (getPiecesSet(playerOwned).getSoldier()[i] != null) {
                    temp++;
                }
            }
            return temp;
        } else if(type.equals(PieceCorporal.class)) {
            int temp = 0;
            for(int i = 0 ; i < 2 ; i++) {
                if (getPiecesSet(playerOwned).getCorporal()[i] != null) {
                    temp++;
                }
            }
            return temp;
        } else if(type.equals(PieceMarshall.class)) {
            if (getPiecesSet(playerOwned).getMarshall() == null) {
                return 0;
            } else {
                return 1;
            }
        } else if(type.equals(PieceBomb.class)) {
            int temp = 0;
            for(int i = 0 ; i < 2 ; i++) {
                if (getPiecesSet(playerOwned).getBomb()[i] != null) {
                    temp++;
                }
            }
            return temp;
        } else {
            if (getPiecesSet(playerOwned).getFlag() == null) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
