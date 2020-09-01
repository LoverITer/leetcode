package cache.lru;

import java.lang.reflect.Field;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/18 12:14
 */
public class IntegerBug {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = cache.getDeclaredField("cache");
        c.setAccessible(true);
        Integer[] array = (Integer[])c.get(cache);
        array[133]=6;

        System.out.printf("%d",2+2);
    }

}
