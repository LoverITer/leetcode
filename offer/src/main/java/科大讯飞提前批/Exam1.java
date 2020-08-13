package 科大讯飞提前批;

import java.util.Scanner;

/**
 * <p>
 * 第一题：给定1, 5, 10, 50, 100面额的纸币，每张面额的纸币有若干张，要求输入，
 * 计算支付K元最少需要的数量
 * </p>
 * 使用贪心策略，优先使用面额最大的
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 13:55
 */
public class Exam1 {
    //面额
    private static int[] MONEY = new int[]{1, 5, 10, 50, 100};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] money = new int[5];
        int k = 0, i = 0;
        while (i < 5) {
            money[i++] = sc.nextInt();
        }
        k = sc.nextInt();
        System.out.println(min(money, k));
    }

    public static int min(int[] money, int k) {
        if (money == null || money.length == 0) {
            return 0;
        }
        int count = 0;
        if (k != 0) {
            for (int i = 4; i >= 0; i--) {
                //使用贪心策略，优先寻找最大的钱支付
                while (k >= MONEY[i] && money[i] > 0) {
                    count++;
                    k -= MONEY[i];
                    money[i] -= 1;
                }
            }
        }
        return count;
    }


}
