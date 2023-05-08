package graphics;

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

public class GameEndScreen extends JFrame {

    private JButton btnRestart;
    private JButton btnNewGame;

    public GameEndScreen(GraphicBoard gb) {
        gb.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) { // when .dispose() is called
                closeEndGameScreen();
            }
        });
    }

    private void endGameScreen(int gameState) {

        setTitle("Result");        
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        String text;
        if(gameState == 1) {
            text = "Victory";
        } else if (gameState == -1) {
            text = "Defeat";
        } else {
            text = "Draw";
        }

        JPanel ptop = new JPanel(new GridBagLayout());
        ptop.setVisible(true);
        JLabel lblTitle = new JLabel(text);
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
        btnRestart = new JButton("Restart Game");

        pleft.add(btnRestart);
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
        btnNewGame = new JButton("New Game");

        pright.add(btnNewGame);
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

    public void showGameEnd(int gameState) {
        endGameScreen(gameState);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.equals(btnRestart)) {
                    Menu.setRestart(true); // preset was already handled
                    closeEndGameScreen();
                } else {
                   Menu.setRestart(false);
                   closeEndGameScreen();
                }
            }
        };

        btnRestart.addActionListener(listener);
        btnNewGame.addActionListener(listener);
    }

    public void setVisibilityMenu(boolean b) {
        setVisible(b);
    }

    private void closeEndGameScreen() {
        this.dispose();
    }

}
