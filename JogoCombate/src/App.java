import game.*;
import graphics.*;

public class App {
    public static void main(String[] args) throws Exception {
        GraphicBoard gb = new GraphicBoard();

        gb.getBoard().setStartRandom();

        gb.showWindow();

        gb.getBoard().setDebug(true);

        gb.updateWindow();

        gb.playGame();
    }
}
