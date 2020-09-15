package 奇安信;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/16 14:52
 */
public class Exam1 {

    public static int CalulateMethodCount (int money) {
        if(money<=0){
            return 0;
        }
        if(money==1) return 1;
        int[] dp=new int[money+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=money;i++){
            dp[i]=dp[i-1]+dp[i-2]+1;
        }
        return dp[money];
    }

    public static void main(String[] args) {
        System.out.println(CalulateMethodCount(7));
    }

}
