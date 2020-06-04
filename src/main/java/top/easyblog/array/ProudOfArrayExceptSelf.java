package top.easyblog.array;

/**
 * <h4>238.除自身以外数组的乘积<h4/>
 * <p>你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。</p>
 * <p>
 * 示例:
 * <pre>
 *    输入: [1,2,3,4]
 *    输出: [24,12,8,6]
 * <pre/>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 *
 *
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/04 22:34
 */
public class ProudOfArrayExceptSelf {


    public static int[] productExceptSelf(int[] nums) {
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return new int[0];
        }

        int[] answer = new int[length];
        answer[0] = 1;
        //求出nums[i]的正向积
        for (int i = 1; i < answer.length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }
        //右侧所有元素的积
        int sumOfRight = 1;
        //求出nums[i]的逆向积，并一并求出最终结果
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = sumOfRight * answer[i];
            sumOfRight *= nums[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] ints = productExceptSelf(new int[]{6, 3, 2, 1, 8});
        for (int i : ints) {
            System.out.println(i);
        }
    }

}
