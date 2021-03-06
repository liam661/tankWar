package tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description
 * @Author liam661
 * @Date 2022/2/19 17:42
 **/
public class PropertyMgr {
    static Properties props = new Properties();
    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if(props == null) {
            return null;
        }
        return props.get(key);
    }
}
