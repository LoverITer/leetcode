package 道通科技;

import java.math.BigInteger;
import java.util.Scanner;

/** 输入一个数a,求a的阶乘的结果能后被10^n整除的最大的n的值
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/09 14:36
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        String factorial=factorial(a).toString();
        char[] chars = String.valueOf(factorial).toCharArray();
        int cnt = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                cnt++;
            } else {
                break;
            }
        }
        System.out.println(cnt);
    }

    //计算a！
    private static BigInteger factorial(int a) {
        if (a < 0) {
            return BigInteger.ZERO;
        } else {
            BigInteger fac=BigInteger.valueOf(1);
            for (int i = 1; i <= a; i++) {
                fac =fac.multiply(BigInteger.valueOf(i));
            }
            return fac;
        }

    }




}
