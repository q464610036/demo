package beanfactory;

import java.util.*;

public class BeanFactory {
    //读取bean配置文件，不需要写properties后缀
    private static final ResourceBundle bundle = ResourceBundle.getBundle("beanConfig");
    private static final Map<String, Object> beanMap = new HashMap<>();

    static{
        try {
            Enumeration<String> keys = bundle.getKeys();
            //遍历所有的key，读取value，利用反射创建bean对象
            while(keys.hasMoreElements()){
                String key = keys.nextElement();
                //读取key对应的value
                String beanPath = bundle.getString(key);
                try {
                    beanMap.put(key, Class.forName(beanPath).newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String beanName) {
        return beanMap.get(beanName);
    }
}
