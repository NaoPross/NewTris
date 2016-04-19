import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewTris {

    // constatn values (config)
    // public static final String DEFAULT_WINDOW_TITLE = "NewTris - Nao Pross";
    public static final String DEFAULT_WINDOW_TITLE = "floating";

    public static final String CARD_MENU_UI = "Main Menu";
    public static final String CARD_TRIS_UI = "Tris Board";
    public static final String CARD_SCORE_UI = "Scoreboard";

    public static final Dimension DEFAULT_PANEL_SIZE = new Dimension(450, 450);

    public static final Font DEFAULT_UI_FONT = new Font("Ubuntu Mono", Font.PLAIN, 36);
    public static final Font DEFAULT_UI_TITLE_FONT = new Font("Ubuntu Mono", Font.BOLD, 54);


    // instance variables
    private CardLayout cardLayout;
    private JPanel windowLayout;
    private TrisEngine trisEngine = new TrisEngine();

    public NewTris() {
        initUi();
    }

    private void initUi() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TrisKeyListener keyListener = new TrisKeyListener();
        frame.addKeyListener(keyListener);

        MenuUi menuUi = new MenuUi(trisEngine);
        TrisUi trisUi = new TrisUi(trisEngine);
        ScoreUi scoreUi = new ScoreUi(trisEngine);

        cardLayout = new CardLayout();
        windowLayout = new JPanel(cardLayout);

        windowLayout.add(menuUi, CARD_MENU_UI);
        windowLayout.add(trisUi, CARD_TRIS_UI);
        windowLayout.add(scoreUi, CARD_SCORE_UI);

        trisEngine.showGamePanel(TrisEngine.GameState.MENU, windowLayout);

        frame.add(windowLayout);

        frame.setTitle(DEFAULT_WINDOW_TITLE);
        frame.setSize(DEFAULT_PANEL_SIZE);
        frame.setMinimumSize(DEFAULT_PANEL_SIZE);

        frame.pack();

        frame.setLocationRelativeTo(null);
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