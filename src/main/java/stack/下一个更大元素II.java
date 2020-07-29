package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，
 * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <pre>
 *   示例：
 *   输入：[5,4,3,2,1]
 *   输出 : [-1,5,5,5,5]
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/23 14:51
 */
public class 下一个更大元素II {

    /**
     * 由于数组是循环的，因此需要扫描两遍，除此之外的具体原理参考{@link 下一个更大元素I}
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            return new int[]{};
        }
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0, arrLen = nums.length; i < 2 * arrLen; i++) {
            int index = i % arrLen;
            while (!stack.isEmpty() && nums[index] > nums[stack.peek()]) {
                result[stack.pop()] = nums[index];
            }
            stack.push(index);
        }

        return result;
    }

    public static void main(String[] args) {

    }

}
