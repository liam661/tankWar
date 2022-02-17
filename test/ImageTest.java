import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/17 21:32
 **/
public class ImageTest {
    @Test
    public void test(){
        try {
//         BufferedImage image1 = ImageIO.read(new File("D:/bulletD.gif"));
//           assertNotNull(image1);

            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().
                    getResourceAsStream("bulletD.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
