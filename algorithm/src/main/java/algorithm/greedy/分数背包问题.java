package algorithm.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 分数背包问题：
 * 有N个物品，第i个物品的价值为vi,重量为wi，其中vi和wi均不为负数，
 * 背包的容量为W.现在要求用这些物品组合出一种不超过背包容量并且物品的价值最大的组合方式
 * 注意：这个题目中的物品可以只拿走一部分
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/13 13:08
 */
public class 分数背包问题 {


    /**
     * 这个题和0-1背包问题的基本题设一样，但是此题没有要求一个物品不可拆分，因此可以使用贪心策略，优先
     * 装那些价值高的物品。
     *
     * @param w
     * @param wight
     * @param values
     * @return
     */
    private static int helper(int w, int[] wight, int[] values) {
        int max = 0;
        float[] v=new float[values.length];
        //计算单价,map用于存储某个物品单价和它在数组中的映射关系
        Map<Float,Integer> map=new HashMap<>();
        for(int i=0;i<v.length;i++){
            v[i]=values[i]/(float)wight[i];
            map.put(v[i],i);
        }
        Arrays.sort(v);
        int i=v.length-1;
        while(w>0){
            int index=map.get(v[i]);
            if(w>wight[index]){
                max+=values[index];
                w-=wight[index];
                i--;
            }else{
                max+=values[index]*(w/(float)wight[index]);
                break;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //物品数量
        int w = sc.nextInt();   //背包重量
        int[] wight = new int[n];  //wight[i]表示第i个物品的重量
        int[] values = new int[n];  //values[i]表示第i个物品的价值
        for (int i = 0; i < wight.length; i++) {
            wight[i] = sc.nextInt();
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = sc.nextInt();
        }
        System.out.println(helper(w, wight, values));
    }

}
