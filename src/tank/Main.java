package tank;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/17 11:01
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException{
        TankFrame tf = new TankFrame();
        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
