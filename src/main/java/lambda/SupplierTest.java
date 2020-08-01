package lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Supplier;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/11 13:59
 */
public class SupplierTest {

    public static void main(String[] args) {
        Deque<Integer> stack=new ArrayDeque<>();
        stack.push(43);
        Integer pop = stack.pop();
        String res = test(() -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 0) {
                    sb.append(i);
                }
            }
            return sb.toString();
        });
        System.out.println(res);
    }


    public static String test(Supplier<String> supplier) {
        return supplier.get();
    }

}
