package tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/18 8:48
 **/
public class ResourceMgr {
    public static BufferedImage tankL, tankU,tankR,tankD;

    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tankD.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}