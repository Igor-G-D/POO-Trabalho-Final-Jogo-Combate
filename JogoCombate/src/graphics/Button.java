package graphics;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import game.*;

public class Button {
    private JButton btn;
    private Cell associatedCell;
    public Button(Cell cell) {
        btn = new JButton();
        associatedCell = cell;
        if( associatedCell.getIsObstacle() ) {
            btn.setIcon(new ImageIcon(getClass().getResource("/images/Lake.png")));
        }

    }
        //btn.setBackground(Color.LIGHT_GRAY);

    public void upgradeImage(Cell cell){
        if(cell.getPiece().getPlayerOwned() == true){
            if(associatedCell.getPiece() instanceof PieceBomb) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/bomb_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceFlag) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/flag_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceMarshall) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/10_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceSoldier) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/2_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceSpy) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/1_ally.png")));
            } else if (associatedCell.getPiece() instanceof PieceCorporal) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/3_ally.png")));
            } 
        }
        else{
            if(associatedCell.getPiece() instanceof PieceBomb) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/bomb_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceFlag) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/flag_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceMarshall) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/10_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceSoldier) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/2_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceSpy) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/1_enemy.png")));
            } else if (associatedCell.getPiece() instanceof PieceCorporal) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/3_enemy.png")));
            } 

        }
        if(!associatedCell.getIsObstacle()){
            btn.setText(null);
        }
    }
    public JButton getButton() {
        return btn;
    }

    public Cell getAssociatedCell() {
        return associatedCell;
    }

}
