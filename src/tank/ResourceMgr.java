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
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage [] explodes = new BufferedImage[16];

    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("tankD.gif"));
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("bulletD.gif"));
            for(int i = 0;i < 16;i++){
                explodes[i] =  ImageIO.read(ResourceMgr.class.getClassLoader()
                        .getResourceAsStream("e"+(i+1)+".gif"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
