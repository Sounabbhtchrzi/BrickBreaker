import java.awt.*;

public class Ball {
    private int x, y, diameter;
    private int dirX, dirY;
    private double speed;

    // Constructor to initialize ball properties
    public Ball(int x, int y, int diameter, int dirX, int dirY,double speed) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed=speed;
    }

    // Draw the ball on the screen
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, diameter, diameter);
    }

    // Move the ball and handle wall collisions
    public void move() {
        x += speed*dirX;
        y += speed*dirY;

        // Bounce off left and right walls
        if (x <= 0 || x >= 1530 - diameter) {
            dirX = -dirX;
        }

        // Bounce off top wall
        if (y <= 0) {
            dirY = -dirY;
        }
    }

    public void reset() {
        x = 310;
        y = 350;
        dirX = 0;
        dirY = 1;
    }

    // Set new ball direction
    public void setDirection(int dirX, int dirY) {
        // Avoid setting direction to 0, preventing the ball from getting stuck
        this.dirX = (dirX != 0) ? dirX : 1;
        this.dirY = (dirY != 0) ? dirY : -1;
    }

    // Getters for ball properties
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    // Setters to manually adjust position if needed
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }
}
