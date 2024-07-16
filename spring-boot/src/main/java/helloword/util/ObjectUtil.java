package helloword.util;

import helloword.vo.Employee;
import helloword.vo.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectUtil {
    /**
     * 替换对象为null的String属性为""
     * @param obj
     * @throws IllegalAccessException
     */
    public static void replaceNullsWithEmpty(Object obj)throws IllegalAccessException {
        if (obj == null) {
            return;
        }
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value == null) {
                    field.set(obj, "");
                }
            }
        }
    }

    /**
     * 替换list中对象为null的String属性为
     * @param list
     * @throws IllegalAccessException
     */
    public static void replaceNullsWithEmptyByList(List list)throws IllegalAccessException {
        if (list == null) {
            return;
        }
        for (Object obj : list) {
            replaceNullsWithEmpty(obj);
        }
    }

    /**
     * 反射为对象属性赋值
     * @param object
     * @param list
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setObjectProperties(Object object, List<ObjectProperties> list) throws NoSuchFieldException, IllegalAccessException {
        Class<?> objectClass = object.getClass();
        for (ObjectProperties objectProperties : list) {
            Field field = objectClass.getDeclaredField(objectProperties.getFiled());
            //给对象属性赋值
            field.setAccessible(true);
            field.set(object, objectProperties.getValue());
            System.out.println(field.get(object));
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        List<ObjectProperties> list1 = new ArrayList<>();
        list1.add(new ObjectProperties("userId", 18));
        list1.add(new ObjectProperties("name", "张三"));
        list1.add(new ObjectProperties("date", new Date()));
        setObjectProperties(user, list1);
        Employee employee = new Employee();
        List<ObjectProperties> list2 = new ArrayList<>();
        list2.add(new ObjectProperties("userId", 19));
        list2.add(new ObjectProperties("name", "李四"));
        setObjectProperties(employee, list2);
        //给老师设置属性
        System.out.println(user);
        System.out.println(employee);
    }
}



