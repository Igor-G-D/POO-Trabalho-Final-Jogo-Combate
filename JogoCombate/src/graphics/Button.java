package graphics;

import java.awt.Color;

import javax.swing.JButton;

import game.*;

public class Button {
    private JButton btn;
    private Cell associatedCell;

    public Button(Cell cell) {
        btn = new JButton();
        associatedCell = cell;
        if( associatedCell.getIsObstacle() ) {
            btn.setText("X");
        }
        if( associatedCell.getPiece() instanceof PieceBomb ) {
            btn.setText("0");
        } else if (associatedCell.getPiece() instanceof PieceFlag) {
            btn.setText("F");
        } else if (associatedCell.getPiece() instanceof PieceMarshall) {
            btn.setText("10");
        } else if (associatedCell.getPiece() instanceof PieceSoldier) {
            btn.setText("2");
        } else if (associatedCell.getPiece() instanceof PieceSpy) {
            btn.setText("1");
        } else if (associatedCell.getPiece() instanceof PieceCorporal) {
            btn.setText("3");
        }
    
        btn.setBackground(Color.LIGHT_GRAY);
    }

    public JButton getButton() {
        return btn;
    }

    public Cell getAssociatedCell() {
        return associatedCell;
    }

    public void setIconImagem() {
        //TODO: implement function to change button image based on which piece is on its associated cell
    }
}
