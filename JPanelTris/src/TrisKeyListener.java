import java.awt.event.*;

public class TrisKeyListener implements KeyListener {

    public TrisKeyListener() {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    public void keyPressed(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}