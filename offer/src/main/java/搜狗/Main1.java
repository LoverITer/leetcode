package 搜狗;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/05 18:27
 */
public class Main1 {


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回能交换奖品的最大数量
     * @param a int整型
     * @param b int整型
     * @param c int整型
     * @return int整型
     */
    public static int numberofprize (int a, int b, int c) {
        //9,3,3  ----> 5 4 4 ----> 4
        if(a == c && b == c){
            return a;
        }
        int min=findMinNum(a,b,c);
        int avg=(a+b+c)/3;
        if(avg-min<=1){
            return avg;
        }
        return min+((avg-min)>>1);
    }

    private static int findMinNum(int a, int b, int c) {
        int min=a;
        if(min>b){
            min=b;
        }
        if(min>c){
            min=c;
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(numberofprize(4,4,2));
    }

}
