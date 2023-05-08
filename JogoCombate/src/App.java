import game.*;
import graphics.*;

public class App {
    public static void main(String[] args) throws Exception {
        GraphicBoard gb = new GraphicBoard();

        gb.showWindow();

        gb.updateCounters();

        gb.getBoard().randomizePositions(0,false); //randomize enemies
        
        gb.updateWindow();

        gb.playerChoosePositions();
    }
}
