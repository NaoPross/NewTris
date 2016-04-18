import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewTris {

    TrisEngine trisEngine = new TrisEngine();

    public NewTris() {
        initUi();
    }

    private void initUi() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TrisUi trisUi = new TrisUi(new TrisEngine());

        frame.add(trisUi);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NewTris nt = new NewTris();
            }
        });
    }
}