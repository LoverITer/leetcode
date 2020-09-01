package lambda;

import java.util.function.IntBinaryOperator;

/**
 * Java8新特性测试——lambda测试
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/11 12:28
 */
public class LambdaTest {

    public static void main(String[] args) {
        IntBinaryOperator opt = (a, b) -> a * b;
        int ans = opt.applyAsInt(5, 3);  
        System.out.println(ans);     //15
    }

}
