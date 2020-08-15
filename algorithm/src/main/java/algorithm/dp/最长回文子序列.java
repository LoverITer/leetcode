package algorithm.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/13 20:07
 */
public class 最长回文子序列 {

    /**
     *
     * @param str
     * @return
     */
    private static int helper(String str) {
        char[] ch=str.toCharArray();
        int[][] dp=new int[ch.length][ch.length];
        ///base case
        for(int i=0;i<dp.length;i++){
            dp[i][i]=1;
        }
        //暴力遍历每一种可能
        for(int i=ch.length-1;i>=0;i--){
            for(int j=i+1;j<ch.length;j++){
                if(ch[i]==ch[j]){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        //返回s[0...len]长度中最大回文子序列长度
        return dp[0][ch.length-1];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(helper(str));
    }

}
