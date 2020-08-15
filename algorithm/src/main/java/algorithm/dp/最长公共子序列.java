package algorithm.dp;

import java.util.Scanner;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/13 18:58
 */
public class 最长公共子序列 {


    /**
     * <pre>
     * 最长公共子序列：
     * 1. 明确dp数组含义：dp[i][j]表示对于str1[0~i]和str2[0~j]的最长公共子序列长度为dp[i][j]
     * 2. 明确base case：dp[0][j]=0(str1没有即没有公共的),同理dp[i][0]=0
     * 3. 明确状态转移方程：
     *     if str1[i-1]==str2[j-1]; then
     *        dp[i][j]=dp[i-1][j-1]+1;
     *     else
     *        dp[i][j]=max(dp[i-1][j],dp[i][j-1]);
     * </pre>
     *
     * @param str1
     * @param str2
     * @return
     */
    private static int helper(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for(int i=1;i<s1.length+1;i++){
            for(int j=1;j<s2.length+1;j++){
                if(s1[i-1]==s2[j-1]){
                    //发现新的公共字符，长度+1
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    //不是公共字符，选择之前最长的长度最为str长度为i,str2长度为j时的最长公共子序列长度
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        return dp[s1.length][s2.length];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.println(helper(str1, str2));
    }

}
