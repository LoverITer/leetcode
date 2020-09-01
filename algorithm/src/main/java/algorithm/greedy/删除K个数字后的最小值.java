package algorithm.greedy;

import java.util.Calendar;

/**
 * 给定一个非负整数num,从该整数中去掉k个数字，要求剩下的数据形成的新整数尽可能的小。
 * 其中整数的长度大于或等于k，给出的整数的长度大小可能超过long类型的数字范围。
 * <pre>
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/25 10:51
 */
public class 删除K个数字后的最小值 {

    /**
     * 贪心算法
     * <p>算法思路：一个很简单的道理需要明白，那就是在删除的时候应该尽可能
     * 的首先删除一个数字的高位，因为高位数字对数字的影响是最大的，因此我们
     * 可以想到：从左向右遍历原整数，当发现前一位数比后一位数大的时候就将前一位数删除
     * 比如，对于数字1270936，删除1位，1<2，2<7，7>0，因此删除7可以得到最小数：120936<p/>
     *
     * @param num 源整数
     * @param k   删除的位数
     * @return
     */
    public static String removeKDigits1(String num, int k) {

        for (int i = 0; i < k; i++) {
            boolean isCut = false;
            for (int j = 0; j < num.length() - 1; j++) {
                char ch = num.charAt(j);
                if (ch > num.charAt(j + 1)) {
                    isCut = true;
                    num=num.substring(0,j)+num.substring(j+1);
                    break;
                }
            }
            //如果没有删除，那就删除最后一个数字
            if(!isCut){
                num=num.substring(0,num.length()-1);
            }
        }
        if(num.length()==0){
            return "0";
        }
        return num;
    }

    /**
     * 贪心+桟 降低时间复杂度
     * <p>算法思路：上面贪心的思路不变，依然遍历数字字符串，
     * 在遍历的时候让每个数字都入栈，当栈顶栈顶数字比当前数字大的时候，
     * 将栈顶数字出栈，然后将栈顶数字替换为当前数字。下面以整数54127 k=2为例：
     * <pre>
     * 遍历到5,桟空，5直接入栈
     *     stack {[5][][][][]}  k=2
     * 遍历到4，桟不空 && 栈顶元素5>4 && k>0，栈顶元素出栈，并替换为当前元素4
     *     stack {[4][][][][]}  k=1
     * 遍历到1，桟不空 && 栈顶元素 4>1 && k>0,栈顶元素出栈，并且替换为当前元素1
     *     stack {[1][][][][]}  k=0
     * 遍历到2,k==0,2直接入栈
     *     stack {[1][2][][][]}  k=0
     * 遍历到7,k==0,7直接入栈
     *     stack {[1][2][7][][]}  k=0
     *
     * 遍历结束，在寻找stack以0开头的元素，把0去掉秒钟后返回数字
     *
     * </pre>
     *
     * @param num 源整数
     * @param k   删除的位数
     * @return
     */
    public static String removeKDigits2(String num, int k) {
        if (k == 0 || num == null) {
            return num;
        }
        //新数字的长度
        int newLength = num.length() - k;
        if (newLength == 0) {
            return "0";
        }
        char[] stack = new char[num.length()];
        int top = 0; //栈顶指针
        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            //如果栈顶元素比当前元素大，那就弹出栈顶元素（单调栈）
            while (top > 0 && stack[top - 1] > ch && k > 0) {
                top--;
                k--;
            }
            stack[top++] = ch;
        }

        //找到桟中第1个非零数字的位置，以此构建新的整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits1("11245535", 3));
        System.out.println(removeKDigits2("11245535", 3));
        //Integer a =Integer.valueOf(131);
    }

}
