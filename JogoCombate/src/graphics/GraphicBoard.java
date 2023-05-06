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
        setSize(400, 600);
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
        c.weighty = 0.1; // 10% height of panel
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
        c.weighty = 0.80; // 80% height of panel
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
        c.weighty = 0.1; // 10% height of panel
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(pBottom, c);

        this.setVisible(true);
    }

    public void showMenu() {

        setTitle("Combate");        
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();


        JPanel ptop = new JPanel(new GridBagLayout());
        ptop.setVisible(true);
        JLabel lblTitle = new JLabel("Bem Vindo ao Combate!");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        ptop.add(lblTitle);
        ptop.setBorder(BorderFactory.createEmptyBorder(80, 10, 0, 10));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 0.40; // 10% height of panel
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(ptop, c);


        JPanel pleft = new JPanel(new GridLayout());
        pleft.setVisible(true);
        JButton btnRandom = new JButton("Posição Aleatória");
        // ON CLICK ACTION
        btnRandom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement to call random positioning
                dispose();
            }
        });

        pleft.add(btnRandom);
        pleft.setBorder(BorderFactory.createEmptyBorder(180, 10, 180, 10));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5; // 100% width of panel
        c.weighty = 0.6; // 10% height of panel
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(pleft, c);


        JPanel pright = new JPanel(new GridLayout());
        pright.setVisible(true);
        JButton btnSort = new JButton("Definir Posições");
        // ON CLICK ACTION
        btnSort.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement to open a window for manual piece insertion
                dispose();
            }
        });

        pright.add(btnSort);
        pright.setBorder(BorderFactory.createEmptyBorder(180, 10, 180, 10));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5; // 100% width of panel
        c.weighty = 0.6; // 10% height of panel
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        add(pright, c);

        setVisible(true);
    }
}
