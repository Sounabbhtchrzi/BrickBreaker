import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JPanel implements ActionListener {
    private JButton startButton, helpButton, highScoreButton, exitButton;
    private JFrame frame;
    private HighScoreManager highScoreManager;

    public HomeScreen(JFrame frame) {
        this.frame = frame;
        this.highScoreManager = new HighScoreManager();
        setLayout(null);
        SoundManager.playThemeSong();

        // Custom gradient background
        setBackground(Color.BLACK);

        // Title Label with Shadow Effect
        JLabel titleLabel = new JLabel("Brick Breaker Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(getCenteredX(700), 150, 700, 60);
        add(titleLabel);

        // Glassmorphism Panel for Button Area
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBounds(getCenteredX(400), 300, 400, 340);
        buttonPanel.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent black
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        add(buttonPanel);

        // Start Button
        startButton = createButton("Start", 40);
        buttonPanel.add(startButton);

        // Help Button
        helpButton = createButton("Help", 110);
        buttonPanel.add(helpButton);

        // High Score Button
        highScoreButton = createButton("High Scores", 180);
        buttonPanel.add(highScoreButton);

        // Exit Button
        exitButton = createButton("Exit", 250);
        buttonPanel.add(exitButton);

        JLabel madeByLabel = new JLabel("Made by Sounab");
        madeByLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        madeByLabel.setForeground(Color.LIGHT_GRAY);
        madeByLabel.setBounds(getWidth() - 150, getHeight() - 30, 140, 20); // Initial position

        add(madeByLabel);

        // Adjust position dynamically when frame is resized
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                madeByLabel.setBounds(getWidth() - 150, getHeight() - 30, 140, 20);
            }
        });
    }

    // Create a styled button with hover effect
    private JButton createButton(String text, int y) {
        JButton button = new JButton(text);
        button.setBounds(50, y, 300, 50);
        button.setFont(new Font("Arial", Font.BOLD, 22));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(30, 30, 30));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover and click effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(200, 0, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 30, 30));
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 50, 50));
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(200, 0, 0));
            }
        });

        button.addActionListener(this);
        return button;
    }

    // Get centered X position for any width
    private int getCenteredX(int width) {
        return (1580 - width) / 2;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Create gradient background
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, getWidth(), getHeight(), Color.BLACK);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            SoundManager.stopSound();
            startGame();
        } else if (e.getSource() == helpButton) {
            showHelp();
        } else if (e.getSource() == highScoreButton) {
            showHighScores();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    // Start Game
    private void startGame() {
        frame.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.revalidate();
        frame.repaint();
        gamePanel.requestFocus();
    }

    // Show Help
    private void showHelp() {
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        JOptionPane.showMessageDialog(this,
                "<html><div style='text-align: center; color:white;'>"
                        + "🕹️ Instructions:<br>"
                        + "- Move paddle left and right using arrow keys<br>"
                        + "- Break all the bricks to win<br>"
                        + "- Press Enter to restart if you lose<br>"
                        + "- Avoid missing the ball!<br>"
                        + "- Press Esc to exit from the game midway"
                        + "</div></html>",
                "Game Help", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show High Scores
    private void showHighScores() {
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        JOptionPane.showMessageDialog(this,
                "<html><div style='text-align: center; color:white;'>"

                        + highScoreManager.getHighScores().replaceAll("\n", "<br>")
                        + "</div></html>",
                "🏆 High Scores", JOptionPane.INFORMATION_MESSAGE);
    }
}
