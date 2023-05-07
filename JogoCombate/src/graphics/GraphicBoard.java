package graphics;

import game.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicBoard extends JFrame {
    private Board bd;
    private Button btn[][];

    public GraphicBoard() {
        bd = new Board();
        btn = new Button[5][5];

        for( int i = 0 ; i<5 ; i++ ) {
            for( int j = 0 ; j<5 ; j++ ) {
                btn[i][j] = new Button(bd.getCell(i, j));
            }
        }
    }

    //TODO: insert pieces from both player and enemy on the grid

    public Board getBoard() {
        return bd;
    }

    public void updateWindow() {
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                btn[i][j].upgradeImage();
            }
        }
    }

    public void showWindow() {
        
        setTitle("Teste");
        setSize(550, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        setLayout(new GridBagLayout());
        

        GridBagConstraints c = new GridBagConstraints();

        // PANEL 1 CONFIGURATION
        JPanel pTop = new JPanel();
        pTop.setLayout(new GridBagLayout());
        pTop.setVisible(true);

        JLabel lbl1 = new JLabel(" ENEMY");
        pTop.add(lbl1);
        pTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 0; // 10% height of panel
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(pTop, c);

        c.gridwidth = 2;

        // BUTTONS` GRID CONFIGURATION
        BackgroundJPanel pMid = new BackgroundJPanel();
        pMid.setVisible(true);

        for( int i = 0 ; i<5 ; i++ ) {
            for( int j = 0 ; j<5 ; j++ ) {
                pMid.add(btn[i][j]);
            }
        }

        pMid.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 5; // 80% height of panel
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
        c.weighty = 0; // 10% height of panel
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(pBottom, c);

        //setContentPane((new JLabel(new ImageIcon(getClass().getResource("/images/jogocombate (1).png")))));
        //insert the background image in the frame, but this method puts the image above the other elements
        this.setVisible(true);
    }

    public void playGame() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Button button = (Button) e.getSource();
                System.out.println("Button Pressed: x : " + button.getAssociatedCell().getPosx() + " y : " + button.getAssociatedCell().getPosy() + "\n");
            }
        };

        for(int i = 0 ; i < 5 ; i ++) {
            for ( int j = 0 ; j < 5 ; j ++) {
                btn[i][j].addActionListener(listener);
            }
        }
    }
}
