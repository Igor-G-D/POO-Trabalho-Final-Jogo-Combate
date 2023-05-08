package graphics;

import game.*;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GraphicBoard extends JFrame {
    private Board bd;
    private Button btn[][];
    private Button previousButton;
    private Button enemyPieces[];
    private Button playerPieces[];
    private int[] playerPiecesLeft;
    private int[] enemyPiecesLeft;
    private Label lblPlayer[];
    private Label lblEnemy[];
    private Button startDebugB;
    private Button hintB;

    public GraphicBoard() {
        bd = new Board();
        btn = new Button[5][5];
        playerPieces = new Button[6];
        playerPiecesLeft = new int[6];
        lblPlayer = new Label[6];
        enemyPieces = new Button[6];
        enemyPiecesLeft = new int[6];
        lblEnemy = new Label[6];

        startDebugB = new Button();
        hintB = new Button();
        hintB.setEnabled(false);
        

        for( int i = 0 ; i<5 ; i++ ) {
            for( int j = 0 ; j<5 ; j++ ) {
                btn[i][j] = new Button(bd.getCell(i, j));
            }
        }

        getPiecesLeft(false);
        getPiecesLeft(true);

        getPiecesArray(true);
        getPiecesArray(false);

        previousButton = null;
    }

    //TODO: insert pieces from both player and enemy on the grid

    
    public Board getBoard() {
        return bd;
    }

    public void updateCell(int x, int y) {
        btn[x][y].upgradeImage();
    }

    public void updateWindow() {
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                btn[i][j].upgradeImage();
            }
        }
    }

    public void updateCounters() {
        getPiecesLeft(false);
        getPiecesLeft(true);
        for(int i=0;i<6;i++) {
            lblPlayer[i].setText(String.valueOf("x" + playerPiecesLeft[i]));
            lblEnemy[i].setText(String.valueOf("x" + enemyPiecesLeft[i]));
        }
    }

    public void showWindow() {
        setTitle("Combate");
        setSize(750, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        setLayout(new GridBagLayout());
        

        GridBagConstraints c = new GridBagConstraints();

        // OPERATION BUTTONS
        JPanel pOpButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pOpButtons.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        pOpButtons.setVisible(true);

        pOpButtons.add(startDebugB);
        pOpButtons.add(hintB);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 1; // 10% height of panel
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(pOpButtons, c);
        
        
        

        // ENEMY`S LEFT PIECES
        JPanel pTopButtons = new JPanel(new GridLayout());
        pTopButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pTopButtons.setVisible(true);
        
        

        for( int i = 0 ; i<6 ; i++ ) {
            pTopButtons.add(enemyPieces[i]);
        }
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 2; // 10% height of panel
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(pTopButtons, c);

        

        // PANEL 1 CONFIGURATION
        JPanel pTop = new JPanel();
        pTop.setLayout(new GridLayout());
        pTop.setVisible(true);
        pTop.setBorder(BorderFactory.createEmptyBorder(10, 70, 10, 30));

        

        /* */
        for( int i = 0 ; i<6 ; i++ ) {
            lblEnemy[i] = new Label(String.valueOf("x" + enemyPiecesLeft[i]));
            pTop.add(lblEnemy[i]);
        }

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 2; // 10% height of panel
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(pTop, c);

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
        c.weighty = 2; // 80% height of panel
        c.gridx = 0;
        c.gridy = 3;
        add(pMid, c);

        // PANEL 3 CONFIGURATION
        JPanel pBottom = new JPanel(new GridLayout());        
        pBottom.setBorder(BorderFactory.createEmptyBorder(10, 70, 10, 30));
        pBottom.setVisible(true);
        for( int i = 0 ; i<6 ; i++ ) {
            lblPlayer[i] = new Label(String.valueOf("x" + playerPiecesLeft[i]));
            pBottom.add(lblPlayer[i]);
        }

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        add(pBottom, c);

        // PLAYER`S LEFT PIECES
        JPanel pBottomButtons = new JPanel(new GridLayout());
        pBottomButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pBottomButtons.setVisible(true);

        for( int i = 0 ; i<6 ; i++ ) {
            pBottomButtons.add(playerPieces[i]);
        }
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 2; // 10% height of panel
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        add(pBottomButtons, c);

        

        //setContentPane((new JLabel(new ImageIcon(getClass().getResource("/images/jogocombate (1).png")))));
        //insert the background image in the frame, but this method puts the image above the other elements
        this.setVisible(true);
    }

    public void playerChoosePositions() {
      
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Button button = (Button) e.getSource();

                if (button.equals(startDebugB)) {
                    if(bd.getRemovedPieces().validGameStart()) {
                        //TODO: start game
                        System.out.println("Game can start!");
                    } else {
                        infoBox("Please place all your pieces!", "Game: ");
                    }
                } else if (previousButton == null) { // nothing was pressed before, or a piece without a piece was chosen, or a enemy piece was chosen
                    if(button.getAssociatedCell() != null) {
                        if(button.getAssociatedCell().getPiece() != null && button.getAssociatedCell().getPiece().getPlayerOwned()) {
                            bd.getRemovedPieces().addPiece(button.getAssociatedCell().getPiece());
                            button.getAssociatedCell().removePiece();
                            updateCell(button.getAssociatedCell().getPosx(), button.getAssociatedCell().getPosy());
                            updateCounters();
                        }
                        previousButton = null;
                    } else {
                        previousButton = button;
                    }
                } else if (previousButton.getAssociatedPiece() != null && previousButton.getAssociatedPiece().getPlayerOwned() && button.getAssociatedCell() != null) {
                    if (bd.playerPlacePieceStart(button.getAssociatedCell().getPosx(), button.getAssociatedCell().getPosy(), previousButton.getAssociatedPiece())) {
                        updateCell(button.getAssociatedCell().getPosx(), button.getAssociatedCell().getPosy());
                        updateCounters();
                    } else {
                        infoBox("Couldn't place piece! Either invalid position, or all pieces of this type have been placed already", "Game: ");
                    }
                    previousButton = null;
                    
                }
               
                updateCounters();
            }
        };

        startDebugB.setText("Start Game");

        startDebugB.addActionListener(listener);

        hintB.setEnabled(false);

        hintB.setText("Hint x" + bd.getHint());

        hintB.addActionListener(listener);

        for(int i = 0 ; i < 5 ; i ++) {
            for ( int j = 0 ; j < 5 ; j ++) {
                btn[i][j].addActionListener(listener);
            }
        }
        for(int i = 0 ; i < 6 ; i ++) {
            playerPieces[i].addActionListener(listener);    
        }
    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public void playGame() {
        
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Button button = (Button) e.getSource();
                //System.out.println("Button Pressed: x : " + button.getAssociatedCell().getPosx() + " y : " + button.getAssociatedCell().getPosy() + "\n"); // TODO: remove console print
                
                boolean finishedTurn = false;
                //        gb.getBoard().setDebug(true);

                if (button.equals(startDebugB)) {
                    bd.setDebug(true);
                    updateWindow();
                    button.setEnabled(false);
                } else if (previousButton == null) { // nothing was pressed before, or a piece without a piece was chosen, or a enemy piece was chosen
                    setPreviousButton(button);
                } else if (previousButton.equals(hintB) && button.getAssociatedCell() != null) {
                    if(bd.getHintColumn(button.getAssociatedCell().getPosy())) {
                        infoBox("There is an enemy bomb in this column", "Hint: ");
                    } else {
                        infoBox("There is not an enemy bomb in this column", "Hint: ");
                    }
                    previousButton.setText("Hint x"+bd.getHint());
                    if(bd.getHint() <= 0) {
                        previousButton.setEnabled(false);
                    }
                    
                    previousButton = null;

                } else if(previousButton.getAssociatedCell().getPiece().getPlayerOwned()) {
                    if(button.getAssociatedCell().getPiece() == null) {
                        finishedTurn = bd.moveOrAttack(previousButton.getAssociatedCell(), button.getAssociatedCell(), true);
                        updateCell(previousButton.getAssociatedCell().getPosx(), previousButton.getAssociatedCell().getPosy());
                        updateCell(button.getAssociatedCell().getPosx(), button.getAssociatedCell().getPosy());

                        setPreviousButton(null);
                    } else if (!button.getAssociatedCell().getPiece().getPlayerOwned()){
                        finishedTurn = bd.moveOrAttack(previousButton.getAssociatedCell(), button.getAssociatedCell(), true);
                        updateCell(previousButton.getAssociatedCell().getPosx(), previousButton.getAssociatedCell().getPosy());
                        updateCell(button.getAssociatedCell().getPosx(), button.getAssociatedCell().getPosy());
                        
                        setPreviousButton(null);
                    }
                } else if(!previousButton.getAssociatedCell().getPiece().getPlayerOwned()) {
                    setPreviousButton(button);
                }
               
                updateCounters();

                System.out.println("Game state:" +  bd.getRemovedPieces().gameEnd());
                if(bd.getRemovedPieces().gameEnd() == 1) {
                    removeActionListeners(this);
                } else if (bd.getRemovedPieces().gameEnd() == -1) {
                    removeActionListeners(this);
                } else if (bd.getRemovedPieces().gameEnd() == 2) {
                    removeActionListeners(this);
                }

                if(finishedTurn) {
                    System.out.println("Enemy Turn");
                    Cell cellsUsed[] = new Cell[2];
                    cellsUsed = bd.enemyRandomMove();

                    if(cellsUsed[0] == null) {
                        System.out.println("Enemy Can't move");
                    } else {
                        updateWindow();
                    }
                    updateCounters();
                }

                System.out.println("Game state:" +  bd.getRemovedPieces().gameEnd());
                if(bd.getRemovedPieces().gameEnd() == 1) {
                    removeActionListeners(this);
                } else if (bd.getRemovedPieces().gameEnd() == -1) {
                    removeActionListeners(this);
                } else if (bd.getRemovedPieces().gameEnd() == 2) {
                    removeActionListeners(this);
                }
            }
        };

        startDebugB.setText("Debug");

        startDebugB.addActionListener(listener);

        hintB.setEnabled(true);

        hintB.setText("Hint x" + bd.getHint());

        hintB.addActionListener(listener);

        for(int i = 0 ; i < 5 ; i ++) {
            for ( int j = 0 ; j < 5 ; j ++) {
                btn[i][j].addActionListener(listener);
            }
        }
    }

    private void removeActionListeners(ActionListener e) {
        for(int i = 0 ; i < 5 ; i ++) {
            for ( int j = 0 ; j < 5 ; j ++) {
                btn[i][j].removeActionListener(e);
            }
        }
    
    }
    private void setPreviousButton (Button b) {
        if(b == null) {
            this.previousButton = null;
        } else if (b.getAssociatedCell() == null) {
            this.previousButton = b;
        } else if(b.getAssociatedCell().getPiece() == null) {
            this.previousButton = null;
        } else {
            this.previousButton = b;
        }

    }
    
    private void getPiecesArray(boolean playerOwned) {
        if(playerOwned == true) {
            playerPieces[0] = new Button(new PieceBomb(playerOwned));
            playerPieces[1] = new Button(new PieceFlag(playerOwned));
            playerPieces[2] = new Button(new PieceSoldier(playerOwned));
            playerPieces[3] = new Button(new PieceSpy(playerOwned));
            playerPieces[4] = new Button(new PieceMarshall(playerOwned));
            playerPieces[5] = new Button(new PieceCorporal(playerOwned));
        } else {
            enemyPieces[0] = new Button(new PieceBomb(playerOwned));
            enemyPieces[1] = new Button(new PieceFlag(playerOwned));
            enemyPieces[2] = new Button(new PieceSoldier(playerOwned));
            enemyPieces[3] = new Button(new PieceSpy(playerOwned));
            enemyPieces[4] = new Button(new PieceMarshall(playerOwned));
            enemyPieces[5] = new Button(new PieceCorporal(playerOwned));
        }
    }

    private void getPiecesLeft(boolean b) {
        if(b == true) {
            playerPiecesLeft[0] = bd.getRemovedPieces().numberPiecesRemoved(PieceBomb.class, b);
            playerPiecesLeft[1] = bd.getRemovedPieces().numberPiecesRemoved(PieceFlag.class, b);
            playerPiecesLeft[2] = bd.getRemovedPieces().numberPiecesRemoved(PieceSoldier.class, b);
            playerPiecesLeft[3] = bd.getRemovedPieces().numberPiecesRemoved(PieceSpy.class, b);
            playerPiecesLeft[4] = bd.getRemovedPieces().numberPiecesRemoved(PieceMarshall.class, b);
            playerPiecesLeft[5] = bd.getRemovedPieces().numberPiecesRemoved(PieceCorporal.class, b);
        } else {
            enemyPiecesLeft[0] = bd.getRemovedPieces().numberPiecesRemoved(PieceBomb.class, b);
            enemyPiecesLeft[1] = bd.getRemovedPieces().numberPiecesRemoved(PieceFlag.class, b);
            enemyPiecesLeft[2] = bd.getRemovedPieces().numberPiecesRemoved(PieceSoldier.class, b);
            enemyPiecesLeft[3] = bd.getRemovedPieces().numberPiecesRemoved(PieceSpy.class, b);
            enemyPiecesLeft[4] = bd.getRemovedPieces().numberPiecesRemoved(PieceMarshall.class, b);
            enemyPiecesLeft[5] = bd.getRemovedPieces().numberPiecesRemoved(PieceCorporal.class, b);
        }
    }
}
