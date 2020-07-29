package top.easyblog.stack;

/**
 * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
 * <p>
 * 示例 1:
 * <pre>
 *
 * 输入: 158476531
 * 输出: 158513467
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/23 15:56
 */
public class 下一个更大元素III {

    /**
     * 分三步：
     * <p>
     * 1.将给定数字转换成数组，逆序遍历数组，找到逆序递增的断点元素的下标index
     * <p>
     * 2.如果index<=0 说明更定的数字已经是最大的了，直接返回-1；否则交换逆序区前一位和逆序区中仅仅比他大
     * 的元素
     * <p>
     * 3.反转逆序区
     * <p>
     * 按照题意最后将调整后的数组转化为数字，之后判断是否大于{@code Integer.MAX_VALUE},如果大于就返回-1，
     * 否则返回数字
     *
     * @param n
     * @return
     */
    public static int nextGreaterElement(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        //1.寻找逆序遍历数字递增的分界点
        // 1 5 8 4 [7] 6 5 3 1   在这一步找到index=4 即 7
        int index = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                index = i;
                break;
            }
        }
        //index=0说明数字已经全部是逆序的了，也即没有比他更大的下一个数了
        if (index == 0) {
            return -1;
        }
        //2.交换逆序区前一位个在逆序区中刚好大于它的数
        // 1 5 8 [4] 7 6 [5] 3 1  交换之后===>  1 5 8 [5] 7 6 [4] 3 1
        for (int i = nums.length - 1; i > 0; i++) {
            if (nums[index - 1] < nums[i]) {
                char t = nums[index - 1];
                nums[index - 1] = nums[i];
                nums[i] = t;
                break;
            }
        }
        //3.将逆序区变成顺序递增的，即反转index后面的数组
        for (int i = index, j = nums.length - 1; i < j; i++, j--) {
            char t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        long ans = Long.parseLong(new String(nums));
        if (ans > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(nextGreaterElement(123456));
    }

}
