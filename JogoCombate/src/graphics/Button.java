package graphics;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JButton;

import game.*;

public class Button {
    private JButton btn;
    private Cell associatedCell;
    private Image iconImage;

    public Button(Cell cell) {
        this.btn = new JButton();
        this.associatedCell = cell;
        if( this.associatedCell.getIsObstacle() ) {
            btn.setText("X");
        }
        this.btn.setBackground(Color.LIGHT_GRAY);
    }

    public JButton getButton() {
        return this.btn;
    }

    public Cell getAssociatedCell() {
        return this.associatedCell;
    }

    public void setIconImagem() {
        //TODO: implement function to change button image based on which piece is on its associated cell
    }
}
