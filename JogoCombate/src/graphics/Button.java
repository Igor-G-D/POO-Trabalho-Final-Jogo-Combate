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
