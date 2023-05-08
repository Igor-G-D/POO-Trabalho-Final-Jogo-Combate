package graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.*;

public class Button extends JButton {
    private Cell associatedCell;
    private Piece associatedPiece;
    
    
    public Button() {
        associatedCell = null;
        associatedPiece = null;
    }

    public Button(Cell cell) {
        this.setSize(50,50);
        this.associatedPiece = null;
        associatedCell = cell;
        if( associatedCell.getIsObstacle() ) {
            this.setIcon(new ImageIcon(getClass().getResource("/images/Lake.png")));
        }
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }
    
    public Button(Piece piece) {
        this.associatedCell = null;
        this.associatedPiece = piece;
        this.setSize(30, 30);
        if(piece.getPlayerOwned()) {
            if(piece instanceof PieceBomb) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/bomb_ally.png")));
            } else if (piece instanceof PieceFlag) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/flag_ally.png")));
            } else if (piece instanceof PieceMarshall) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/10_ally.png")));
            } else if (piece instanceof PieceSoldier) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/2_ally.png")));
            } else if (piece instanceof PieceSpy) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/1_ally.png")));
            } else if (piece instanceof PieceCorporal) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/3_ally.png")));
            }
        } else {
            if(piece instanceof PieceBomb) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/bomb_enemy.png")));
            } else if (piece instanceof PieceFlag) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/flag_enemy.png")));
            } else if (piece instanceof PieceMarshall) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/10_enemy.png")));
            } else if (piece instanceof PieceSoldier) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/2_enemy.png")));
            } else if (piece instanceof PieceSpy) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/1_enemy.png")));
            } else if (piece instanceof PieceCorporal) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/3_enemy.png")));
            } 
        }
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setOpaque(true);
        this.setContentAreaFilled(false);
    }

    public void upgradeImage(){
        if (associatedCell.getIsObstacle()) {
            return;
        }

        if(associatedCell.getPiece() == null) {
            this.setIcon(null);
        } else if(associatedCell.getPiece().getPlayerOwned() == true){
            if(associatedCell.getPiece() instanceof PieceBomb) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/bomb_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceFlag) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/flag_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceMarshall) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/10_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceSoldier) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/2_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceSpy) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/1_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceCorporal) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/3_ally.png")));
            } 
        }
        else if(associatedCell.getPiece().getPlayerOwned() == false){
            if(!associatedCell.getPiece().getIsVisible()) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/piece_enemy.png")));
            }else if(associatedCell.getPiece() instanceof PieceBomb) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/bomb_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceFlag) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/flag_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceMarshall) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/10_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceSoldier) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/2_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceSpy) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/1_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceCorporal) {
                this.setIcon(new ImageIcon(getClass().getResource("/images/3_enemy.png")));
            } 

        }
    }

    public Cell getAssociatedCell() {
        return associatedCell;
    }

    public Piece getAssociatedPiece() {
        return associatedPiece;
    }
}

