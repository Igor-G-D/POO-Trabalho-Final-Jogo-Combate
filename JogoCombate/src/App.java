import game.*;

public class App {
    public static void main(String[] args) throws Exception {
        Board test = new Board();

        Cell testCell = test.getCell(0,0);

        int test1 = testCell.getPosx();
        int test2 = testCell.getPosy();

    }
}
