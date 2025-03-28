import java.awt.*;

public class Brick {
    private int[][] bricks;
    private int brickWidth, brickHeight;

    // Constructor to initialize the brick grid
    public Brick(int row, int col, int brickWidth, int brickHeight) {
        bricks = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(i==row-1 || i==0) bricks[i][j]=1;
                if(j==col-1 || j==0) bricks[i][j]=1;
                if(j%2==0) bricks[i][j]=1;
            }
        }
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
    }

    // Draw bricks on the screen
    public void draw(Graphics g) {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] > 0) {
                    g.setColor(Color.RED);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    // Check collision between ball and bricks
    public int checkCollision(Ball ball, int totalBricks) {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] > 0) {
                    int brickX = j * brickWidth + 80;
                    int brickY = i * brickHeight + 50;

                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());

                    // Check if ball collides with brick
                    if (ballRect.intersects(brickRect)) {
                        bricks[i][j] = 0; // Remove brick
                        totalBricks--;
                        SoundManager.playBrickHit();

                        // Ball Collision Response
                        if (ball.getX() + ball.getDiameter() - 1 <= brickRect.x ||
                                ball.getX() + 1 >= brickRect.x + brickRect.width) {
                            ball.setDirX(-ball.getDirX()); // Reverse horizontal direction
                        } else {
                            ball.setDirY(-ball.getDirY()); // Reverse vertical direction
                        }
                    }
                }
            }
        }
        return totalBricks;
    }
}
