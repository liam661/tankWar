package tank;

import java.awt.*;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/17 16:44
 **/
public class Bullet {
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x,y;
    private Dir dir;
    private boolean living = true;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private Group group = Group.BAD;

    TankFrame tf = null;
    public Bullet(int x, int y, Dir dir, TankFrame tankFrame,Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tankFrame;
        this.group = group;
    }

    public void paint(Graphics g){
        if(!living){
            tf.bullets.remove(this);
        }
        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
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
                || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    public void die() {
        this.living = false;
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;
        //todo : 用一个react来记录子弹的位置 子弹类和坦克类都加一个属性
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);// 矩形
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if(rect1.intersects(rect2)) {
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.bullets.add(new Bullet(eX, eY, this.dir, this.tf, this.group));
            tf.explodes.add(new Explode(eX, eY, tf));
        }
    }
}
