package tank;

import java.awt.*;
import java.util.Random;

/**
 * @Description
 * 爆炸类
 * @Author liam661
 * @Date 2022/2/18 11:26
 **/
public class Explode {
    private int x,y;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    TankFrame tf = null;
    private int step = 0;
    private boolean living = true;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if(step >= ResourceMgr.explodes.length) {
            step = 0;
        }
    }
}
