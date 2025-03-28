import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private boolean play = true;
    private int level=1;
    private int score = 0;
    private int totalBricks;

    private Timer timer;
    private int delay = 8;

    private Paddle paddle;
    private Ball ball;
    private Brick brick;

    private HighScoreManager highScoreManager = new HighScoreManager();

    public GamePanel() {
        loadLevel(level);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Set up timer for game loop
        timer = new Timer(delay, this);
        timer.start();
    }

    private void loadLevel(int level){
        paddle = new Paddle(310, 830, 100, 8);
        ball = new Ball(310, 650, 20, 0, 1,1+((level-1)*0.25));
        int rows=5+(level-1)*2;
        int cols=23;
        brick = new Brick(rows, cols, 60, 20);
        totalBricks=2*(rows+cols)-4 + (rows-2)*(cols/2 - 1);
    }

    @Override
    public void paint(Graphics g) {
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw paddle
        paddle.draw(g);

        // Draw ball
        ball.draw(g);

        // Draw bricks
        brick.draw(g);

        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 1350, 30);
        g.drawString("Level: " + level, 50, 30);

        // Check win condition
        if (totalBricks <= 0) {
            play = false;
            ball.setDirection(0, 0);
            g.setColor(Color.GREEN);
            level++;
            SoundManager.playLevelComplete();
            if(level == 5) g.drawString(" Congratulations u have completed the game! Press Esc to exit", getWidth()/3, getHeight()/2);
            else g.drawString("Level " + (level-1) + " Completed! Press Enter for Next Level", getWidth()/3, getHeight()/2);

        }

        // Check game over condition
        if (ball.getY() > getHeight()) {
            play = false;
            ball.setDirection(0, 0);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            highScoreManager.addScore(score);
            SoundManager.playGameOver();
            g.drawString("Game Over! Press Enter to Restart", getWidth() / 3, getHeight() / 2);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (play) {
            ball.move();

            // Check paddle collision
            if (new Rectangle(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter())
                    .intersects(new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight()))) {

                // Change ball direction based on paddle hit location
                SoundManager.playBrickHit();
                int hitPosition = ball.getX() + ball.getDiameter() / 2 - paddle.getX();
                int paddleCenter = paddle.getWidth() / 2;
                double angle = (hitPosition - paddleCenter) / (double) paddleCenter * Math.PI / 3;
                ball.setDirection((int) (5 * Math.sin(angle)), -ball.getDirY());
            }


            int prevBricks = totalBricks;
            totalBricks = brick.checkCollision(ball, totalBricks);


            if (prevBricks > totalBricks) {
                score += 5;
            }
            repaint();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                if (totalBricks <= 0) {
                    loadLevel(level);
                } else {
                    resetGame();
                }
                play = true;
                repaint();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    private void resetGame() {
        score = 0;
        level = 1;
        loadLevel(level);
        ball.reset();
    }
}
