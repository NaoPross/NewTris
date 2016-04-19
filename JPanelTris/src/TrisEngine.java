import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrisEngine {

    // ui management related
    public static enum GameState {
        MENU, PLAYING, SCOREBOARD
    }

    private GameState gameState;

    // gameplay related 
    public static enum CellType {
        EMPTY, CROSS, CIRCLE
    }

    public CellType trisBoard[][] = new CellType[3][3]; 
    public CellType turn;

    public ArrayList<CellType> scoreboard = new ArrayList();

    // constructor
    public TrisEngine() {
        // "circles" start the game
        turn = CellType.CIRCLE;

        // populate the board with empty cells
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                trisBoard[row][column] = CellType.EMPTY;
            }
        }

        gameState = GameState.PLAYING;
    }

    // ui management related stuff
    public void showGamePanel(GameState gs, Container c) {
        gameState = gs;

        JPanel windowLayout = (JPanel) c;
        CardLayout cardLayout = (CardLayout) windowLayout.getLayout();

        // assign every game state to a panel
        if (gs == GameState.MENU)
            cardLayout.show(windowLayout, NewTris.CARD_MENU_UI);
        else if (gs == GameState.PLAYING)
            cardLayout.show(windowLayout, NewTris.CARD_TRIS_UI);
        else if (gs == GameState.SCOREBOARD)
            cardLayout.show(windowLayout, NewTris.CARD_SCORE_UI);
    }

    // board related stuff
    public void setCell(int x, int y, CellType type) {
        trisBoard[x][y] = type;
    }

    public CellType getCell(int x, int y) {
        return  trisBoard[x][y];
    }

    public void nextTurn() {
        turn = (turn == CellType.CIRCLE) ? CellType.CROSS : CellType.CIRCLE;
    }

    public boolean isGameOver() {
        if (checkBoard() != CellType.EMPTY)
            return true;

        // check if the board is full
        boolean full = true;
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if (trisBoard[row][col] == CellType.EMPTY)
                    return false;
            }
        }
        return true;
    }

    // return EMPTY if no one has won otherwise return the type that won
    public CellType checkBoard() {
        // vertical check
        for (int i = 0; i < 3; i++) {
            if (allEqualElements(trisBoard[i]) && trisBoard[i][0] != CellType.EMPTY) {
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
            
            if (allEqualElements(elements) && elements[0] != CellType.EMPTY) {
                return elements[0];
            }
        }

        // diagonal check
        CellType[] diagonal = {
            trisBoard[0][0],
            trisBoard[1][1],
            trisBoard[2][2]
        };
        if (allEqualElements(diagonal) && diagonal[0] != CellType.EMPTY) {
            return diagonal[0];
        }

        CellType[] _diagonal = {
            trisBoard[0][2],
            trisBoard[1][1],
            trisBoard[2][0]
        };
        if (allEqualElements(_diagonal) && _diagonal[0] != CellType.EMPTY) {
            return _diagonal[0];
        }

        return CellType.EMPTY;
    }

    // other utils
    private boolean allEqualElements(CellType[] arr) {
        for (int i = 0; i < 3; i++) {
            if (arr[0] != arr[i]) {
                return false;
            }
        }
        return true;
    }
}