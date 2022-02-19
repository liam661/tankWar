package tank;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/17 11:01
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException{
        TankFrame tf = new TankFrame();
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        for(int i = 0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + i * 70, 200, Dir.DOWN, tf, Group.BAD));
        }

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
