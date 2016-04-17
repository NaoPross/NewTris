// non avevo un IDE a portata di mano per gli import automatici
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewTris extends JFrame implements MouseListener, MouseMotionListener {

    // Constans
    final int GRID_BORDERS = 20;

    // Game related
    TrisEngine trisEngine;

    // Graphics related
    Point mousePos = new Point(-1, -1);
    Dimension window, cell;

    public NewTris() {
        super("NewTris");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.trisEngine = new TrisEngine();
    }

    public void paint(Graphics g) {
        window = new Dimension(this.getWidth(), this.getHeight());

        int shorterWindowSide = (window.width > window.height) ? window.height : window.width;
        cell = new Dimension(
            (shorterWindowSide - GRID_BORDERS*2) / 3,
            (shorterWindowSide - GRID_BORDERS*2) / 3
        );

        // Clear the window
        g.setColor(Color.white);
        g.fillRect(0, 0, window.width, window.height);

        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                int offsetX = (window.width - cell.width*3 - GRID_BORDERS*2) / 2;
                int x = row * cell.width + GRID_BORDERS + offsetX;

                int offsetY = (window.height - cell.height*3 - GRID_BORDERS*3) / 2;
                int y = column * cell.height + GRID_BORDERS*2 + offsetY;

                // highlight selected cell
                Rectangle rect = new Rectangle(x, y, cell.width, cell.height);
                if (rect.contains(mousePos)) {
                    g.setColor(Color.red);
                    g.fillRect(x, y, cell.width, cell.height);
                }

                // draw cell type
                if (trisEngine.getCell(row, column) == TrisEngine.CellType.CIRCLE) {
                    g.setColor(Color.blue);
                    g.fillRect(x +10, y +10, cell.width -20, cell.height-20);
                } else if (trisEngine.getCell(row, column) == TrisEngine.CellType.CROSS) {
                    g.setColor(Color.green);
                    g.fillRect(x +10, y +10, cell.width -20, cell.height-20);
                }

                g.setColor(Color.black);
                g.drawRect(x, y, cell.width, cell.height);
            }
        }

    }

    // Implemented methods
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        // find which cell has been pressed
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                int x = row * cell.width + GRID_BORDERS;
                int y = column * cell.height + GRID_BORDERS*2;

                Rectangle rect = new Rectangle(x, y, cell.width, cell.height);
                if (rect.contains(e.getPoint())) {
                    if (trisEngine.getCell(row, column) == TrisEngine.CellType.EMPTY) {

                        trisEngine.setCell(row, column, trisEngine.turn);

                        // check if someone has won
                        TrisEngine.CellType t = trisEngine.checkBoard();
                        if (t != TrisEngine.CellType.EMPTY) {
                            System.out.println(t.toString() + " Won!");
                            System.exit(0);
                        }

                        trisEngine.nextTurn();
                    }
                }
            }
        }
    }

    // Unused Implemented Methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}

    public static void main(String[] args) {
        NewTris nt = new NewTris();
        nt.setVisible(true);
    }
}