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

        int cellSideLenght = (window.width > window.height) ? window.height : window.width;
        cell = new Dimension(
            (cellSideLenght - GRID_BORDERS*2) / 3,
            (cellSideLenght - GRID_BORDERS*2) / 3
        );

        // Clear the window
        g.setColor(Color.white);
        g.fillRect(0, 0, window.width, window.height);

        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                int x = row * cell.width + GRID_BORDERS;
                int y = column * cell.height + GRID_BORDERS*2;

                Rectangle rect = new Rectangle(x, y, cell.width, cell.height);
                if (rect.contains(mousePos)) {
                    g.setColor(Color.red);
                    g.fillRect(x, y, cell.width, cell.height);
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
                    trisEngine.setCellState(row, column, trisEngine.turn);
                    trisEngine.checkBoard();
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