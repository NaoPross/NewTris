
public class TrisEngine {

    public static enum CellType {
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

    public void setCell(int x, int y, CellType type) {
        trisBoard[x][y] = type;
    }

    public CellType getCell(int x, int y) {
        return  trisBoard[x][y];
    }

    public void nextTurn() {
        turn = (turn == CellType.CIRCLE) ? CellType.CROSS : CellType.CIRCLE;
    }

    // return EMPTY if no one has won otherwise return the type that won
    public CellType checkBoard() {
        // vetical check
        for (int i = 0; i < 3; i++) {
            if (allEqualElements(trisBoard[i])) {
                return trisBoard[i][0];
            }          
        }

        // horizontal check
        for (int i = 0; i < 3; i++) {
            CellType[] elements = {
                trisBoard[0][i],
                trisBoard[1][i],
                trisBoard[2][i]
            };
            
            if (allEqualElements(elements)) {
                return elements[0];
            }
        }

        return CellType.EMPTY;
    }

    private boolean allEqualElements(CellType[] arr) {
        for (int i = 0; i < 3; i++) {
            if (arr[0] != arr[i]) {
                return false;
            }
        }
        return true;
    }
}