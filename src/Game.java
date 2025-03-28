import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker Game");

        HomeScreen homeScreen = new HomeScreen(frame);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(homeScreen);
        frame.setVisible(true);

    }
}
