package graphics;

import game.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicBoard extends JFrame {
    private Board bd = new Board();
    private Button btn[][];

    public void showWindow() {

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
/**/
        for( int i = 0 ; i<5 ; i++ ) {
            for( int j = 0 ; j<5 ; j++ ) {
                btn[i][j] = new Button(bd.getCell(i, j));
                pMid.add(btn[i][j].getButton());
            }
        }
/* 
        JButton btn1 = new JButton("1");
        btn1.setPreferredSize(new Dimension(1, 1));
        btn1.setBackground(Color.LIGHT_GRAY);
        btn1.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn1);
        JButton btn2 = new JButton("2");
        btn2.setPreferredSize(new Dimension(1, 1));
        btn2.setBackground(Color.LIGHT_GRAY);
        btn2.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn2);
        JButton btn3 = new JButton("3");
        btn3.setPreferredSize(new Dimension(1, 1));
        btn3.setBackground(Color.LIGHT_GRAY);
        btn3.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn3);
        JButton btn4 = new JButton("4");
        btn4.setPreferredSize(new Dimension(1, 1));
        btn4.setBackground(Color.LIGHT_GRAY);
        btn4.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn4);
        JButton btn5 = new JButton("5");
        btn5.setPreferredSize(new Dimension(1, 1));
        btn5.setBackground(Color.LIGHT_GRAY);
        btn5.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn5);
        JButton btn6 = new JButton("6");
        btn6.setPreferredSize(new Dimension(1, 1));
        btn6.setBackground(Color.LIGHT_GRAY);
        btn6.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn6);
        JButton btn7 = new JButton("7");
        btn7.setPreferredSize(new Dimension(1, 1));
        btn7.setBackground(Color.LIGHT_GRAY);
        btn7.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn7);
        JButton btn8 = new JButton("8");
        btn8.setPreferredSize(new Dimension(1, 1));
        btn8.setBackground(Color.LIGHT_GRAY);
        btn8.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn8);
        JButton btn9 = new JButton("9");
        btn9.setPreferredSize(new Dimension(1, 1));
        btn9.setBackground(Color.LIGHT_GRAY);
        btn9.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn9);
        JButton btn10 = new JButton("10");
        btn10.setPreferredSize(new Dimension(1, 1));
        btn10.setBackground(Color.LIGHT_GRAY);
        btn10.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn10);
        JButton btn11 = new JButton("11");
        btn11.setPreferredSize(new Dimension(1, 1));
        btn11.setBackground(Color.LIGHT_GRAY);
        btn11.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn11);
        JButton btn12 = new JButton("12");
        btn12.setPreferredSize(new Dimension(1, 1));
        btn12.setBackground(Color.LIGHT_GRAY);
        btn12.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn12);
        JButton btn13 = new JButton("13");
        btn13.setPreferredSize(new Dimension(1, 1));
        btn13.setBackground(Color.LIGHT_GRAY);
        btn13.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn13);
        JButton btn14 = new JButton("14");
        btn14.setPreferredSize(new Dimension(1, 1));
        btn14.setBackground(Color.LIGHT_GRAY);
        btn14.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn14);
        JButton btn15 = new JButton("15");
        btn15.setPreferredSize(new Dimension(1, 1));
        btn15.setBackground(Color.LIGHT_GRAY);
        btn15.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn15);
        JButton btn16 = new JButton("16");
        btn16.setPreferredSize(new Dimension(1, 1));
        btn16.setBackground(Color.LIGHT_GRAY);
        btn16.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn16);
        JButton btn17 = new JButton("17");
        btn17.setPreferredSize(new Dimension(1, 1));
        btn17.setBackground(Color.LIGHT_GRAY);
        btn17.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn17);
        JButton btn18 = new JButton("18");
        btn18.setPreferredSize(new Dimension(1, 1));
        btn18.setBackground(Color.LIGHT_GRAY);
        btn18.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn18);
        JButton btn19 = new JButton("19");
        btn19.setPreferredSize(new Dimension(1, 1));
        btn19.setBackground(Color.LIGHT_GRAY);
        btn19.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn19);
        JButton btn20 = new JButton("20");
        btn20.setPreferredSize(new Dimension(1, 1));
        btn20.setBackground(Color.LIGHT_GRAY);
        btn20.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn20);
        JButton btn21 = new JButton("21");
        btn21.setPreferredSize(new Dimension(1, 1));
        btn21.setBackground(Color.LIGHT_GRAY);
        btn21.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn21);
        JButton btn22 = new JButton("22");
        btn22.setPreferredSize(new Dimension(1, 1));
        btn22.setBackground(Color.LIGHT_GRAY);
        btn22.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn22);
        JButton btn23 = new JButton("23");
        btn23.setPreferredSize(new Dimension(1, 1));
        btn23.setBackground(Color.LIGHT_GRAY);
        btn23.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn23);
        JButton btn24 = new JButton("24");
        btn24.setPreferredSize(new Dimension(1, 1));
        btn24.setBackground(Color.LIGHT_GRAY);
        btn24.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn24);
        JButton btn25 = new JButton("25");
        btn25.setPreferredSize(new Dimension(1, 1));
        btn25.setBackground(Color.LIGHT_GRAY);
        btn25.setForeground(Color.LIGHT_GRAY);
        pMid.add(btn25);*/

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

    public static void main(String[] args) {
        GraphicBoard layout = new GraphicBoard();
        layout.showWindow();
    }
}
