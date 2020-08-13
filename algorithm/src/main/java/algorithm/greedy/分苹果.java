package algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/12 15:18
 */
public class 分苹果 {


    public static int helper(int[] apples) {
        int sum = 0, avg = 0;
        for (int num : apples) {
            sum += num;
        }
        //寻找平均值
        avg = sum / apples.length;
        //有余数，说明没法平均分配
        if (avg * apples.length != sum) {
            return -1;
        }
        int min = 0;
        for (int num : apples) {
            if (num > avg) {
                int over = num - avg;
                if (over % 2 != 0) {
                    return -1;
                } else {
                    min += over / 2;
                }
            }
        }
        return min;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] apples = new int[n];
        for (int i = 0; i < n; i++) {
            apples[i] = sc.nextInt();
        }
        System.out.println(helper(apples));
    }

}
