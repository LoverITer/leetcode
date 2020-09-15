package algorithm.greedy;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/04 16:08
 */
public class 牛牛凑数字 {

    /**
     * 得到牛牛能够凑到的最大的数字
     *
     * @param n int整型 牛牛能够承受的价格
     * @param a int整型一维数组 1-9这九个数字的价格数组
     * @return string字符串
     */
    public static String solve(int n, int[] a) {
        int minPrice = 0;
        //寻找最低价格
        for (int i = 0; i < a.length; i++) {
            if (a[minPrice] > a[i]) {
                minPrice = i;
            }
        }
        //无法承受的价格
        if (a[minPrice] > n) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        int size = n / a[minPrice];
        int left = n % a[minPrice];
        for (int i = 0; i < size; i++) {
            sb.append(minPrice + 1);
        }
        if (left == 0) {
            return sb.toString();
        }
        int r=0;
        for(int i=minPrice+1;i<a.length;i++){
            while(left>0&&left+a[minPrice]>=a[i]){
                sb.replace(r,r,String.valueOf(i+1));
                left=a[minPrice]+left-a[i];
            }
            r+=1;
            if(left==0){break;}
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(solve(15, new int[]{9,11,3,12,5,8,9,10,65}));
    }

}
