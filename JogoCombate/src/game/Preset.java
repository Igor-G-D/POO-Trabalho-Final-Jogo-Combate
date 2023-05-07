package game;

public class Preset {
    int preset[][] = new int[5][5];

    Preset(Board board) { // doesn't store if its player owned;
        for(int i = 0 ; i < 5 ; i ++) {
            for(int j = 0; j < 5 ; j++) {
                if(board.getCell(i, j).getPiece() == null) {
                    if(board.getCell(i, j).getIsObstacle()) {
                        preset[i][j] = -2;
                    } else {
                        preset[i][j] = -1;
                    }

                } else {
                    preset[i][j] = board.getCell(i, j).getPiece().getPieceValue();
                }
            }
        }
    }

    public int[][] getPreset() {
        return preset;
    }
    
}
