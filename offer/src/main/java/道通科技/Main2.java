package 道通科技;

import java.util.Scanner;

/**
 * 有一个箱子的体积是V,有n个物品，每个物品的体积是v[i]，求箱子最小的剩余空间
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/09 14:59
 */
public class Main2 {

    private static int[] dp = new int[10001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();   //箱子容量
        int n = sc.nextInt();    //物体个数
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();    //物体vs[i]的体积
        }

        for (int i = 0; i < n; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + v[i]);
            }
        }

        System.out.println(V - dp[V]);
    }

}
