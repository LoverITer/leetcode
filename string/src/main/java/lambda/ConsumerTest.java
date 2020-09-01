package lambda;

import java.util.function.Consumer;

/**
 * lambda消费者接口测试
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/11 13:46
 */
public class ConsumerTest {

    public static void main(String[] args) {
          test((integer)->{
              System.out.println(integer);
          });
    }

    public static void test(Consumer<Integer> opt){
        for(int i=0;i<10;i++) {
            opt.accept(i);
        }
    }

}
