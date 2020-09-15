package 搜狗;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/09/05 18:27
 */
public class Main2 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回能创建的房屋数
     *
     * @param t  int整型 要建的房屋面宽
     * @param xa int整型一维数组 已有房屋的值，其中 x0 a0 x1 a1 x2 a2 ... xi ai 就是所有房屋的坐标和房屋面宽。 其中坐标是有序的（由小到大）
     * @return int整型
     */
    public static int getHouses(int t, int[] xa) {
        if (xa == null || xa.length == 0||t<=0) {
            return 0;
        }
        if(xa[1]<=1&&xa[3]<=1){
            return 0;
        }
        if (xa[0] == xa[1] && xa[2] == xa[3] && xa[1] == xa[2]) {
            return 0;
        }
        if (xa[0] == xa[1] || xa[2] == xa[3]) {
            return 2;
        }
        //左边房屋的右端点
        int x1 = xa[0] + (xa[1] >> 1);
        //右边房屋的左端点
        int x2 = xa[2] - (xa[3] >> 1);
        //房屋中间的距离
        int dis = x2 - x1;
        int ans = 2;
        if (dis < t) {
            return ans;
        } else if (dis == t) {
            ans++;
        } else {
            ans += 2;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getHouses(2, new int[]{1, 0, 0, 0}));
    }

}
