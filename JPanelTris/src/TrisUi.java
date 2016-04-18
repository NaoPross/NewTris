import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrisUi extends JPanel implements MouseListener, MouseMotionListener {

    // constants
    private final int MIN_GRID_BORDER_DISTANCE = 20;
    private final Color COLOR_CROSS = Color.green;
    private final Color COLOR_CRICLE = Color.blue;

    // game logic
    TrisEngine trisEngine;

    // graphics
    private Dimension panelSize = new Dimension(400, 400);
    private Dimension cellSize = new Dimension(100, 100);
    private Point mousePos = new Point(-1, -1);

    private boolean mouseClick = false;

    public TrisUi(TrisEngine trisEngine) {
        this.trisEngine = trisEngine;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    // JPanel Graphics
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelSize = this.getParent().getSize();

        // cast to graphics2d (original instance of g)
        Graphics2D g2d = (Graphics2D) g;

        // enable antialias
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // background
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, panelSize.width, panelSize.height);

        int shorterPanelSize =
            (panelSize.width > panelSize.height) ? panelSize.height : panelSize.width;

        cellSize = new Dimension(
            (shorterPanelSize - MIN_GRID_BORDER_DISTANCE * 2) / 3,
            (shorterPanelSize - MIN_GRID_BORDER_DISTANCE * 2) / 3
        );

        Point gridPos = new Point(MIN_GRID_BORDER_DISTANCE, MIN_GRID_BORDER_DISTANCE);

        // grid
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                // background color for the cell (red or white)
                Color cellBGColor = Color.white;

                // calculate the position of the cell on the panel
                Point cellPos = new Point(
                    gridPos.x + cellSize.width*row,
                    gridPos.y + cellSize.height*col
                );

                // check if the cell is under the cursor
                Rectangle rect = new Rectangle(
                    cellPos.x, cellPos.y,
                    cellSize.width, cellSize.height
                );
                if (rect.contains(mousePos)) {
                    cellBGColor = Color.orange;
                    // check if it has been clicked
                    if (mouseClick) {
                        mouseClick = false;

                        // update the board
                        if (trisEngine.getCell(row, col) == TrisEngine.CellType.EMPTY) {
                            trisEngine.setCell(row, col, trisEngine.turn);
                            TrisEngine.CellType win = trisEngine.checkBoard();

                            // check if someone has won
                            if (win != TrisEngine.CellType.EMPTY) {
                                System.out.println(win.toString() + " won.");
                                System.exit(0);
                            }

                            trisEngine.nextTurn();
     
                        }

                   }
                }

                // paint cell background
                g2d.setColor(cellBGColor);
                g2d.fillRect(cellPos.x, cellPos.y, cellSize.width, cellSize.height);


                if (trisEngine.getCell(row, col) == TrisEngine.CellType.CROSS) {
                    g2d.setColor(COLOR_CROSS);

                    // magic shape math
                    Polygon crossPartOne = new Polygon(
                        new int[] {
                            cellPos.x + (int) (cellSize.width / 10.0),
                            cellPos.x + (int) (cellSize.width / 10.0 * (1 + Math.sqrt(2))),
                            cellPos.x + (int) (cellSize.width * (1 - 1 / 10.0)),
                            cellPos.x + (int) (cellSize.width * (1 - 1 / 10.0)),
                            cellPos.x + (int) (cellSize.width * (1 - (1/10.0 + 1/10.0 * Math.sqrt(2)))),
                            cellPos.x + (int) (cellSize.width / 10.0)
                        },
                        new int[] {
                            cellPos.y + (int) (cellSize.height / 10.0),
                            cellPos.y + (int) (cellSize.height / 10.0),
                            cellPos.y + (int) (cellSize.height * (1 - (1 / 10.0 + 1 / 10.0 * Math.sqrt(2)))),
                            cellPos.y + (int) (cellSize.height * (1 - 1 / 10.0)),
                            cellPos.y + (int) (cellSize.height * (1 - 1 / 10.0)),
                            cellPos.y + (int) (cellSize.height * (1 / 10.0  + 1 / 10.0 * Math.sqrt(2)))
                        },
                        6
                    );

                    Polygon crossPartTwo = new Polygon(
                        new int[] {
                            cellPos.x + (int) (cellSize.width * (1 - 1 / 10.0)),
                            cellPos.x + (int) (cellSize.width * (1 - 1 / 10.0)),
                            cellPos.x + (int) (cellSize.width * (1 / 10.0 + 1 / 10.0 * Math.sqrt(2))),
                            cellPos.x + (int) (cellSize.width / 10.0),
                            cellPos.x + (int) (cellSize.width / 10.0),
                            cellPos.x + (int) (cellSize.width * (1 - (1 / 10.0 + 1 / 10.0 * Math.sqrt(2))))
                        },
                        new int[] {
                            cellPos.y + (int) (cellSize.height / 10.0),
                            cellPos.y + (int) (cellSize.height * (1 / 10.0 + 1 / 10.0 * Math.sqrt(2))),
                            cellPos.y + (int) (cellSize.height * (1 - 1 / 10.0)),
                            cellPos.y + (int) (cellSize.height * (1 - 1 / 10.0)),
                            cellPos.y + (int) (cellSize.height * (1 - (1 / 10.0 + 1 / 10.0 * Math.sqrt(2)))),
                            cellPos.y + (int) (cellSize.height / 10.0)
                        },
                        6
                    );

                    g2d.setColor(COLOR_CROSS);
                    g2d.fillPolygon(crossPartOne);
                    g2d.fillPolygon(crossPartTwo);

                } else if (trisEngine.getCell(row, col) == TrisEngine.CellType.CIRCLE) {
                    g2d.setColor(COLOR_CRICLE);
                    g2d.fillOval(
                        cellPos.x + cellSize.width / 10,
                        cellPos.y + cellSize.width / 10,
                        cellSize.width - cellSize.width / 10 * 2,
                        cellSize.height - cellSize.width / 10 * 2
                    );

                    g2d.setColor(cellBGColor);
                    g2d.fillOval(
                        cellPos.x + cellSize.width / 10 * 3,
                        cellPos.y + cellSize.width / 10 * 3,
                        cellSize.width - cellSize.width / 10 * 2 * 3,
                        cellSize.height - cellSize.width / 10 * 2 * 3
                    );
                }

                g2d.setColor(Color.black);
                g2d.drawRect(cellPos.x, cellPos.y, cellSize.width, cellSize.height);
            }
        }
    }

    // for frame.pack()
    @Override
    public Dimension getPreferredSize() {
        return panelSize;
    }

    // MouseMotionListener implementation
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
        if (!mouseClick)
            repaint();
    }

    public void mouseDragged(MouseEvent e) {}

    // Mouselistener implementation
    public void mouseReleased(MouseEvent e) {
        mousePos = e.getPoint();
        mouseClick = true;
        repaint();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}