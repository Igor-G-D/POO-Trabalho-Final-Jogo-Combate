package graphics;

import game.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
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

    public GraphicBoard(Preset preset) {
        bd = new Board(preset);
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
        setSize(750, 850);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        setLayout(new GridBagLayout());
        

        GridBagConstraints c = new GridBagConstraints();

        // OPERATION BUTTONS
        JPanel pOpButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pOpButtons.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        pOpButtons.setVisible(true);
        pOpButtons.setBackground(new Color(255, 225, 175));

        
        pOpButtons.add(startDebugB);
        pOpButtons.add(hintB);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(pOpButtons, c);        

        
        // ENEMY`S LEFT PIECES
        JPanel pTopButtons = new JPanel(new GridLayout(2, 6));
        pTopButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pTopButtons.setVisible(true);
        pTopButtons.setBackground(new Color(255, 225, 175));

        for( int i = 0 ; i<6 ; i++ ) {
            pTopButtons.add(enemyPieces[i]);
        }
        
        for( int i = 0 ; i<6 ; i++ ) {
            lblEnemy[i] = new Label(String.valueOf("x" + enemyPiecesLeft[i]));
            lblEnemy[i].setHorizontalAlignment(JLabel.CENTER);
            pTopButtons.add(lblEnemy[i]);
        }

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(pTopButtons, c);


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
        c.gridy = 2;
        add(pMid, c);


        // PLAYER`S LEFT PIECES
        JPanel pBottomButtons = new JPanel(new GridLayout(2, 6));
        pBottomButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pBottomButtons.setVisible(true);
        pBottomButtons.setBackground(new Color(255, 225, 175));

        for( int i = 0 ; i<6 ; i++ ) {
            lblPlayer[i] = new Label(String.valueOf("x" + playerPiecesLeft[i]));
            lblPlayer[i].setHorizontalAlignment(JLabel.CENTER);
            pBottomButtons.add(lblPlayer[i]);
        }

        for( int i = 0 ; i<6 ; i++ ) {
            pBottomButtons.add(playerPieces[i]);
        }
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; // 100% width of panel
        c.weighty = 2; // 10% height of panel
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        add(pBottomButtons, c);

        this.setVisible(true);
    }

    public void playerChoosePositions() {
      
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Button button = (Button) e.getSource();

                if (button.equals(startDebugB)) {
                    if(bd.getRemovedPieces().validGameStart()) {
  
                        Menu.setPreviousGame(new Preset(bd));

                        startDebugB.removeActionListener(this);

                        for(int i = 0 ; i < 5 ; i ++) {
                            for ( int j = 0 ; j < 5 ; j ++) {
                                btn[i][j].removeActionListener(this);
                            }
                        }
                        for(int i = 0 ; i < 6 ; i ++) {
                            playerPieces[i].removeActionListener(this);    
                        }

                        playGame();

                    } else {
                        infoBox("Please place all your pieces!", "Game: ");
                    }
                } else if (previousButton == null) { // nothing was pressed before, or a piece without a piece was chosen, or a enemy piece was chosen
                    if(button.getAssociatedCell() != null) {
                        if(button.getAssociatedCell().getPiece() != null && button.getAssociatedCell().getPiece().getPlayerOwned()) {
                            bd.getRemovedPieces().addPiece(button.getAssociatedCell().getPiece());
                            button.getAssociatedCell().removePiece();
                            updateCell(button.getAssociatedCell().getPosx(), button.getAssociatedCell().getPosy());
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
                        infoBox("Couldn't place piece! Position occupied, invalid position, or all pieces of this type have been placed already", "Game: ");
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


                boolean ended = checkGameEnd(this);


                if(finishedTurn) {
                    Cell cellsUsed[] = new Cell[2];
                    cellsUsed = bd.enemyRandomMove();

                    if(cellsUsed[0] != null) {
                        updateWindow();
                    }
                    updateCounters();
                }

                if (!ended) {
                    checkGameEnd(this);
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
    
    private GraphicBoard getGraphicBoard() {
        return this;
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

    private void closeGameScreen() {
        this.dispose();
    }

    private boolean checkGameEnd(ActionListener al) {
        if(bd.getRemovedPieces().gameEnd() != 0) {
            GameEndScreen gameEndScreen = new GameEndScreen(getGraphicBoard());
            if(bd.getRemovedPieces().gameEnd() == 1) {
                removeActionListeners(al);
                gameEndScreen.showGameEnd(bd.getRemovedPieces().gameEnd());
            } else if (bd.getRemovedPieces().gameEnd() == -1) {
                removeActionListeners(al);
                gameEndScreen.showGameEnd(bd.getRemovedPieces().gameEnd());
            } else if (bd.getRemovedPieces().gameEnd() == 2) {
                removeActionListeners(al);
                gameEndScreen.showGameEnd(bd.getRemovedPieces().gameEnd());
            }
            gameEndScreen.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) { // when .dispose() is called
                    closeGameScreen();
                }   
            });
            return true;
        }
        return false;
    }
}
