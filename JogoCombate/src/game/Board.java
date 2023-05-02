package game;

import java.util.Random;

public class Board {
    private Cell cells[][];

    public Board () {
        Random RNG = new Random();
        int obstaclePos = RNG.nextInt(5); // random number between 0 and 4 (inclusive)
        // i = rows, j = columns
        // the only non player owned row is row 2, so the obstacle must be in that row
        for(int i = 0 ; i < 5 ; i ++) {
            for(int j = 0; j < 5 ; j++) {
                boolean isObstacle = false;
                if ( j == obstaclePos ) {
                    isObstacle = true;
                }
                cells [i][j] = new Cell(isObstacle);
            }
        }
    }

    
}
