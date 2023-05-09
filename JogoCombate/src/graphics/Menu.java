package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.*;

public class Menu extends JFrame {

    private static Preset previousGame;
    private static boolean restart;
    private GraphicBoard gb;
    private JButton btnRandom;
    private JButton btnSort;

    private void showMenu() {

        setTitle("Combate");        
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // TOP PANEL CONFIGURATION
        JPanel ptop = new JPanel(new GridBagLayout());
        ptop.setVisible(true);
        JLabel lblTitle = new JLabel("Welcome to Combate!!");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        ptop.add(lblTitle);
        ptop.setBorder(BorderFactory.createEmptyBorder(80, 10, 0, 10));
        ptop.setBackground(new Color(255, 225, 175));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.40;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(ptop, c);

        // LEFT PANEL CONFIGURATION
        JPanel pleft = new JPanel(new GridLayout());
        pleft.setVisible(true);
        btnRandom = new JButton("Random Positions");
        btnRandom.setBackground(new Color(210, 210, 210));
        btnRandom.setFocusPainted(false);

        pleft.add(btnRandom);
        pleft.setBorder(BorderFactory.createEmptyBorder(180, 10, 180, 10));
        pleft.setBackground(new Color(255, 225, 175));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.6;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(pleft, c);

        // RIGHT PANEL CONFIGURATION
        JPanel pright = new JPanel(new GridLayout());
        pright.setVisible(true);
        btnSort = new JButton("Choose Positions");
        btnSort.setBackground(new Color(210, 210, 210));
        btnSort.setFocusPainted(false);

        pright.add(btnSort);
        pright.setBorder(BorderFactory.createEmptyBorder(180, 10, 180, 10));
        pright.setBackground(new Color(255, 225, 175));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.6;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        add(pright, c);

        setVisible(true);
    }

    public void startMenu() {
        showMenu();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.equals(btnRandom)) {
                    startRandomSetGame();
                } else {
                    startPlayerSetGame();
                }
                gb.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) { // when .dispose() is called
                        if(restart) {
                            gb.removeWindowListener(this);
                            gb = new GraphicBoard(previousGame);
                            Menu.setRestart(false);
                            gb.showWindow();
                            gb.updateWindow();
                            gb.updateCounters();
                            gb.playGame();
                            gb.addWindowListener(this);
                        } else {
                            setVisibilityMenu(true);
                        }
                    }
                });
            }
        };

        btnRandom.addActionListener(listener);
        btnSort.addActionListener(listener);

    }

    public void setVisibilityMenu(boolean b) {
        setVisible(b);
    }

    private void startRandomSetGame() {

        gb = new GraphicBoard();
        gb.getBoard().setStartRandom();
        previousGame = new Preset(gb.getBoard());
        setVisibilityMenu(false);
        gb.showWindow();
        gb.updateWindow();
        gb.updateCounters();
        gb.playGame();

    }

    private void startPlayerSetGame() {
        gb = new GraphicBoard();
        gb.getBoard().randomizePositions(0, false);;
        setVisibilityMenu(false);
        gb.showWindow();
        gb.updateWindow();
        gb.updateCounters();
        gb.playerChoosePositions();
    }

    static void setPreviousGame (Preset preset) {
        previousGame = preset;
    }
    static void setRestart (boolean r) {
        restart = r;
    }
}
