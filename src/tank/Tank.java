package tank;

import java.awt.*;
import java.util.Random;

/**
 * @Description
 * 坦克类
 * @Author liam661
 * @Date 2022/2/17 16:07
 **/
public class Tank {
    private int x,y;
    private Dir dir =  Dir.DOWN;
    private static final int SPEED = 5;
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    private Random random = new Random();
    private Group group = Group.BAD;
    private boolean moving = true;
    private boolean living = true;
    private TankFrame tf;
    Rectangle rect = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        if(!living) {
            tf.tanks.remove(this);
        }
        switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ?  ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ?  ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ?  ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ?  ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if(!moving) return;
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
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire();
        }
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            randomDir();
        }
        boundsCheck();
        // 更新rect 的值
        rect.x = this.x;
        rect.y = this.y;
    }

    // 边界监测
    private void boundsCheck() {
        if(this.x < 0) x = 2;
        else if(this.y < 30) y = 28;
        else if(this.x > TankFrame.GAME_HEIGHT - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bx, by, this.dir, this.tf, this.group));
        if(this.group == Group.GOOD) {
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    public void die() {
        this.living = false;
    }
}
