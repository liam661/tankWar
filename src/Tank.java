import java.awt.*;

/**
 * @Description
 * 坦克类
 * @Author liam661
 * @Date 2022/2/17 16:07
 **/
public class Tank {
    private int x,y;
    private Dir dir =  Dir.DOWN;
    private static final int SPEED = 10;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g){
        switch (dir){
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
        g.fillRect(x,y,50,50);
    }
}
