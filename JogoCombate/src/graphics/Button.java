package graphics;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.*;

public class Button extends JButton {
    private Cell associatedCell;
    
    
    public Button(Cell cell) {
        this.setSize(50,50);
        associatedCell = cell;
        if( associatedCell.getIsObstacle() ) {
            this.setIcon(new ImageIcon(getClass().getResource("/images/Lake.png")));
        }
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }
        //btn.setBackground(Color.LIGHT_GRAY);

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

}

