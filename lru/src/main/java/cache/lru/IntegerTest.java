package cache.lru;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/27 19:21
 */
public class IntegerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int i = 128;
        Integer i2 = 128;  //等价于Integer.valueOf(128)
        Integer i3 = new Integer(128);
        // Integer会自动拆箱为int，所以为true
        System.out.println(i == i2);
        System.out.println(i == i3);
        System.out.println("**************");
        Integer i5 = 127;// java在编译的时候,被翻译成-> Integer i5 = Integer.valueOf(127);
        Integer i6 = 127;
        System.out.println(i5 == i6);// true
        Integer _i5 = 128;
        Integer _i6 = 128;
        System.out.println(_i5 == _i6);//false
        Integer ii5 = new Integer(127);
        System.out.println(i5 == ii5); // false
        Integer i7 = new Integer(128);
        Integer i8 = new Integer(128);
        System.out.println(i7 == i8); // false
    }

}
