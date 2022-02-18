package tank;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/17 11:01
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException{
        TankFrame tf = new TankFrame();
        //
        for(int i = 0; i < 5; i++){
            tf.tanks.add(new Tank(50 + i * 70, 200, Dir.DOWN, tf));
        }

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
