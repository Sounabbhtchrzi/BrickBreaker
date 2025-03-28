import java.awt.*;

public class Paddle {
    private int x, y, width, height;
    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public void moveRight() {
        if (x >= 1430) {
            x = 1430;
        } else {
            x += 30;
        }
    }

    public void moveLeft() {
        if (x <= 10) {
            x = 10;
        } else {
            x -= 30;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
