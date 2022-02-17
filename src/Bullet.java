import java.awt.*;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/17 16:44
 **/
public class Bullet {
    private static final int SPEED = 10;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private int x,y;
    private Dir dir;

    private boolean live = true;
    TankFrame tf = null;
    public Bullet(int x, int y, Dir dir, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tankFrame;
    }

    public void paint(Graphics g){
        if(!live){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if(x < 0 || y < 0 ||
                x > TankFrame.GAME_WIDTH
                || y > TankFrame.GAME_HEIGHT){
            live = false;

        }
    }
}