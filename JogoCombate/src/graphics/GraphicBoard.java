package graphics;

import game.*;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicBoard extends JFrame {
    private Board bd = new Board();
    private Button btn[][] = new Button[5][5];

    //TODO: insert pieces from both player and enemy on the grid

    public Board getBoard() {
        return bd;
    }

    public void showWindow() {

        //bd.setStartPlayerChoice(); <-- waiting for implementation

        /*aa */
        setTitle("Teste");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // PANEL 1 CONFIGURATION
        JPanel pTop = new JPanel();
        pTop.setLayout(new GridBagLayout());
        pTop.setVisible(true);
        //pTop.setBackground(Color.red);

        JLabel lbl1 = new JLabel(" ENEMY");
        pTop.add(lbl1);
        pTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 0.2; // 10% height of panel
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(pTop, c);

        c.gridwidth = 2;

        // BUTTONS` GRID CONFIGURATION
        JPanel pMid = new JPanel(new GridLayout(5, 5));
        pMid.setVisible(true);

        for( int i = 0 ; i<5 ; i++ ) {
            for( int j = 0 ; j<5 ; j++ ) {
                btn[i][j] = new Button(bd.getCell(i, j));
                pMid.add(btn[i][j].getButton());
            }
        }

        pMid.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 0.60; // 80% height of panel
        c.gridx = 0;
        c.gridy = 1;
        add(pMid, c);

        // PANEL 3 CONFIGURATION
        JPanel pBottom = new JPanel(new GridBagLayout());
        pBottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pBottom.setVisible(true);

        JLabel lbl2 = new JLabel("PLAYER");
        pBottom.add(lbl2);
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 0.2; // 10% height of panel
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(pBottom, c);

        this.setVisible(true);
    }
}
