import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScoreUi extends JPanel implements MouseListener {

    // instance of game logic class
    private TrisEngine trisEngine;

    private Dimension panelSize = NewTris.DEFAULT_PANEL_SIZE;

    public ScoreUi(TrisEngine trisEngine) {
        this.trisEngine = trisEngine;
        this.addMouseListener(this);
    }

    // JPanel Graphics
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelSize = this.getParent().getSize();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, panelSize.width, panelSize.height);

        g2d.setColor(Color.ORANGE);
        g2d.fillRect(0, 0, panelSize.width, panelSize.height / 4);
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
