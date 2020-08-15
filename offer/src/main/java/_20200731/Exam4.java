package _20200731;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 给定一个字符串，要求从字符串中提取数字。要求尽可能多的考虑异常情况
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 15:07
 */
public class Exam4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        BigInteger ans = BigInteger.ZERO;
        if (input != null && input.length() != 0) {
            char[] chars = input.toCharArray();
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] >= '0' && chars[i] <= '9') {
                    char flag = input.charAt(i - 1);
                    ans = ans.multiply(BigInteger.valueOf(10)).
                            add(BigInteger.valueOf(Long.parseLong(String.valueOf(chars[i]))));
                    if (flag == '-') {
                        ans = ans.multiply(BigInteger.valueOf(-1));
                    }
                }
            }
        }
        System.out.println(ans.toString());
    }

}
