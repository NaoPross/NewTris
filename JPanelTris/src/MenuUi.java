import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuUi extends JPanel implements MouseListener {

    // instance of game logic class
    private TrisEngine trisEngine;

    // graphics
    private final int MIN_BORDER_DISTANCE = 20;
    private final Font uiFont = NewTris.DEFAULT_UI_TITLE_FONT;

    private Dimension panelSize = NewTris.DEFAULT_PANEL_SIZE;
    private Rectangle playButtonRect = new Rectangle(0, 0, panelSize.width, panelSize.height);

    public MenuUi(TrisEngine trisEngine) {
        this.trisEngine = trisEngine;
        this.addMouseListener(this);
    }

    // JPanel Graphics
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelSize = this.getParent().getSize();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, panelSize.width, panelSize.height);

        // enable antialias for text
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(uiFont);

        // draw the title
        String title = "TicTacToe";
        Dimension titleSize = getFontSize(g2d, title);

        // title background
        g2d.setColor(Color.PINK);
        g2d.fillRect(0, 0, panelSize.width, MIN_BORDER_DISTANCE * 2 + titleSize.height);

        // title text
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString(
            title,
            (panelSize.width - titleSize.width - 2 * MIN_BORDER_DISTANCE) / 2 + MIN_BORDER_DISTANCE,
            MIN_BORDER_DISTANCE + titleSize.height
        );

        // draw a play button
        String play = "PLAY";
        Dimension playSize = getFontSize(g2d, play);

        playButtonRect = new Rectangle(
            (- 2 * 30 + panelSize.width - 2 * MIN_BORDER_DISTANCE - playSize.width) / 2 + MIN_BORDER_DISTANCE,
            (- 2 * 10 + panelSize.height - 2 * MIN_BORDER_DISTANCE - titleSize.height) / 2 + MIN_BORDER_DISTANCE + titleSize.height,
            2 * 30 + playSize.width,
            2 * 10 + playSize.height
        );

        g2d.setColor(Color.DARK_GRAY);
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(6));
        g2d.draw(playButtonRect);
        g2d.setStroke(oldStroke);

        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString(
            play,
            playButtonRect.x + 30,
            playButtonRect.y + playSize.height
        );
    }

    // utils
    private Dimension getFontSize(Graphics g, String text) {
        return new Dimension(
            g.getFontMetrics().stringWidth(text),
            g.getFontMetrics().getHeight()
        );
    }

    // MouseListener implementation
    public void mouseReleased(MouseEvent e) {
        if (playButtonRect.contains(e.getPoint())) {
            trisEngine.showGamePanel(TrisEngine.GameState.PLAYING, this.getParent());
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
