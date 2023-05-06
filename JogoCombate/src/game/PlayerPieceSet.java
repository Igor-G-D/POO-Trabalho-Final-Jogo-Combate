package game;

import java.util.ArrayList;

public class PlayerPieceSet {
    private Piece spy, soldier[] = new Piece[3], corporal[] = new Piece[2], marshall, bomb[] = new Piece[2], flag;
    private boolean playerOwned;

    PlayerPieceSet(boolean playerOwned, boolean empty) {
        if (empty) {
            this.spy = null;
            this.soldier[0] = null;
            this.soldier[1] = null;
            this.soldier[2] = null;
            this.corporal[0] = null;
            this.corporal[1] = null;
            this.marshall = null;
            this.bomb[0] = null;
            this.bomb[1] = null;
            this.flag = null;
            this.playerOwned = playerOwned;
        } else {
            this.spy = new PieceSpy(playerOwned);
            this.soldier[0] = new PieceSoldier(playerOwned);
            this.soldier[1] = new PieceSoldier(playerOwned);
            this.soldier[2] = new PieceSoldier(playerOwned);
            this.corporal[0] = new PieceCorporal(playerOwned);
            this.corporal[1] = new PieceCorporal(playerOwned);
            this.marshall = new PieceMarshall(playerOwned);
            this.bomb[0] = new PieceBomb(playerOwned);
            this.bomb[1] = new PieceBomb(playerOwned);
            this.flag = new PieceFlag(playerOwned);
            this.playerOwned = playerOwned;
        }
    }

    public Piece getSpy() {
        return spy;
    }

    public Piece[] getSoldier() {
        return soldier;
    }

    public Piece[] getCorporal() {
        return corporal;
    }

    public Piece getMarshall() {
        return marshall;
    }

    public Piece[] getBomb() {
        return bomb;
    }

    public Piece getFlag() {
        return flag;
    }

    public Piece removeSpy() { // returns the spy piece and removes it from "storage", if there is no spy piece, return null
        if(spy != null) {
            Piece temp = spy;
            spy = null;
            return temp;
        } else {
            return spy;
        }
    }

    public Piece removeSoldier() { // returns a soldier piece and sets one of them to null, if there are no soldier pieces left, return null
        for(int i=0;i<3;i++) {
            if(soldier[i] != null) {
                Piece temp = soldier[i];
                soldier[i] = null;
                return temp;
            }
        }
        return null;
    }

    public Piece removeCorporal() {
        for(int i=0;i<2;i++) {
            if(corporal[i] != null) {
                Piece temp = corporal[i];
                corporal[i] = null;
                return temp;
            }
        }
        return null;
    }

    public Piece removeMarshall() {
        if(spy != null) {
            Piece temp = marshall;
            marshall = null;
            return temp;
        } else {
            return marshall;
        }
    }

    public Piece removeBomb() {
        for(int i=0;i<2;i++) {
            if(bomb[i] != null) {
                Piece temp = bomb[i];
                bomb[i] = null;
                return temp;
            }
        }
        return null;
    }

    public Piece removeFlag() {
        if(flag != null) {
            Piece temp = flag;
            flag = null;
            return temp;
        } else {
            return flag;
        }
    }

    public void addSpy() { // returns the spy piece and removes it from "storage", if there is no spy piece, return null
        if(spy == null) {
            spy = new PieceSpy(playerOwned);
        } else {
            //TODO: add exception for when it tries adding more than one spy
        }
    }

    public void addSoldier() { // returns a soldier piece and sets one of them to null, if there are no soldier pieces left, return null
        for(int i=0;i<3;i++) {
            if(soldier[i] == null) {
                soldier[i] = new PieceSoldier(playerOwned);
                return;
            }
        }
        // TODO: add exception for when it tries adding more than 3 soldiers
    }

    public void addCorporal() {
        for(int i=0;i<2;i++) {
            if(corporal[i] == null) {
                corporal[i] = new PieceCorporal(playerOwned);
                return;
            }
        }
        // TODO: add exception for when it tries adding more than 2 corporals
    }

    public void addMarshall() {
        if(marshall == null) {
            marshall = new PieceMarshall(playerOwned);
        } else {
            //TODO: add exception for when it tries adding more than one marshall
        }
    }

    public void addBomb() {
        for(int i=0;i<2;i++) {
            if(bomb[i] == null) {
                bomb[i] = new PieceBomb(playerOwned);
                return;
            }
        }
        //TODO: add exception for when it tries adding more than two bombs
    }

    public void addFlag() {
        if(flag == null) {
            flag = new PieceFlag(playerOwned);
        } else {
            //TODO: add exception for when it tries adding more than one flag
        }
    }

    public ArrayList<Piece> returnPiecesLeft(boolean remove) { // returns an ArrayList with all the pieces that are stored
        ArrayList<Piece> returnArray = new ArrayList<Piece>();
        
        if(remove) {
            Piece current;
    
            current = this.removeSpy();
            if (current != null) {
                returnArray.add(current);
            }
            
            for(int i=0;i<3;i++) {
                current = this.removeSoldier();
                if(current != null) {
                    returnArray.add(current);
                }
            }
    
            for(int i=0;i<2;i++) {
                current = this.removeCorporal();
                if(current != null) {
                    returnArray.add(current);
                }
            }
    
    
            current = this.removeMarshall();
            if(current != null) {
                returnArray.add(current);
            }
    
            for(int i=0;i<2;i++) {
                current = this.removeBomb();
                if(current != null) {
                    returnArray.add(bomb[i]);
                }
            }
    
            current = this.removeFlag();
            if(current != null) {
                returnArray.add(current);
            }
    
            return returnArray;
        } else {
            if(spy != null) {
                returnArray.add(spy);
            }
            
            for(int i=0;i<3;i++) {
                if(soldier[i] != null) {
                    returnArray.add(soldier[i]);
                }
            }
    
            for(int i=0;i<2;i++) {
                if(corporal[i] != null) {
                    returnArray.add(corporal[i]);
                }
            }
    
            if(marshall != null) {
                returnArray.add(marshall);
            }
    
            for(int i=0;i<2;i++) {
                if(bomb[i] != null) {
                    returnArray.add(bomb[i]);
                }
            }
    
            if(flag != null) {
                returnArray.add(flag);
            }
    
            return returnArray;
        }
    }
}