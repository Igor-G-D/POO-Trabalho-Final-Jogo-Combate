import game.*;
import graphics.*;

public class App {
    public static void main(String[] args) throws Exception {
        GraphicBoard gb = new GraphicBoard();

        gb.getBoard().setStartRandom();

        gb.showWindow();
        gb.updateWindow();

        gb.getBoard().moveOrAttack(gb.getBoard().getCell(3, 2), gb.getBoard().getCell(2, 2), true);

        gb.updateWindow();

        gb.playGame();
    }
}
