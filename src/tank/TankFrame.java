package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/17 11:53
 **/
public class TankFrame extends Frame {
    Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.GOOD);
    //tank.Bullet b = new tank.Bullet(300,300,tank.Dir.DOWN);
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<Tank> tanks = new ArrayList<Tank>();
    List<Explode> explodes = new ArrayList<Explode>();

    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        //匿名内部类
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+ bullets.size(), 10, 60);
        g.drawString("坦克的数量："+ tanks.size(), 10, 80);
        g.drawString("爆炸的数量："+ explodes.size(), 10, 100);

        g.setColor(c);
        myTank.paint(g);
        for(int i = 0;i < bullets.size();i++) {
            bullets.get(i).paint(g);
        }
        for(int i = 0;i < tanks.size();i++) {
            tanks.get(i).paint(g);
        }
        // 画出爆炸
        for(int i = 0; i < explodes.size(); i++) {
            for(int j = 0; j < explodes.size(); j++){
                explodes.get(i).paint(g);
            }
        }
        for(int i = 0; i < bullets.size(); i++) {
            for(int j = 0; j < tanks.size(); j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            //x += 200;
            //repaint();
            int key = e.getKeyCode(); // 获取具体是哪个键
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            System.out.println("key press");
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode(); // 获取具体是哪个键
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL && !bR && !bU && !bD){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
            }
            if(bL) myTank.setDir(Dir.LEFT);
            if(bU) myTank.setDir(Dir.UP);
            if(bD) myTank.setDir(Dir.DOWN);
            if(bR) myTank.setDir(Dir.RIGHT);
            // 四个键都没按 停止
        }
    }
}
