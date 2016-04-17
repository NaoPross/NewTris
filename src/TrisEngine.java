
public class TrisEngine {

    public enum CellType {
        EMPTY, CROSS, CIRCLE
    }

    public CellType trisBoard[][] = new CellType[3][3]; 
    public CellType turn;

    public TrisEngine() {
        // "circles" start the game
        turn = CellType.CIRCLE;

        // populate the board with empty cells
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                trisBoard[row][column] = CellType.EMPTY;
            }
        }
    }

    public void setCellState(int x, int y, CellType type) {
        trisBoard[x][y] = type;
    }

    // return EMPTY if no one has won otherwise return the type that won
    public CellType checkBoard() {
        // horizontal check
        for (int column = 0; column < 3; column++) {
            CellType first = trisBoard[0][column];
            for (int row = 0; row < 3; row++) {
                if (first != trisBoard[row][column]) {
                    break;
                }
            }
        }
    }
}