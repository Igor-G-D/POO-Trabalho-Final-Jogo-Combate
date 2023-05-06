package graphics;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame {

    public void showMenu() {
        setTitle("Combate");        
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JPanel ptop = new JPanel(new GridBagLayout());
        ptop.setVisible(true);
        JLabel lblTitle = new JLabel("Bem Vindo ao Combate");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
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
