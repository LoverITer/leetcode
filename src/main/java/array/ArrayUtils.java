package array;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/04/21 22:48
 */
public class ArrayUtils {

    /**
     * 移除排序数组中的重复数据（元素）<br/>
     * 思路：使用快慢指针i和j,当nums[i]==nums[j]时,我们就增加 j 以跳过重复项。
     * 当nums[i]!=nums[j]时跳过重复项的运行已经结束,因此我们必须把此时nums[j]的值
     * 复制到 nums[i + 1]。然后递增 i,接着我们将再次重复相同的过程,直到 j 到达数组的末尾为止。
     *
     * @param nums 目标数组
     * @return int[]
     */
    public int[] removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int slower = 0;
        for (int faster = 1; faster < nums.length; faster++) {
            if (nums[faster] != nums[slower]) {
                slower++;
                nums[slower] = nums[faster];
            }
        }
        return Arrays.copyOf(nums, slower + 1);
    }


    /**
     * 移除一个数组中的所有值为target的数<br/>
     *
     * @param nums   目标数组
     * @param target 数组中要移除的数
     * @return int[]
     */
    public int[] removeAllTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        int i = 0;
        //遍历数组
        while (i < len) {
            if (nums[i] == target) {
                //把要移除的数字和数组末尾的数字交换，并且把数组长度减1
                nums[i] = nums[len - 1];
                len--;
            } else {
                i++;
            }
        }
        return Arrays.copyOf(nums, len);
    }

    /**
     * 将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 比如： 传入{1,5,6,3,7} 应该返回 {1,5,6,7,3}
     * @param nums 目标数组
     */
    public int[] nextBiggerPermutationNum(int[] nums) {
        int index = nums.length - 2;
        //顺序就是：1,2,3,4,5......
        //逆序就是：9,8,7,6,5......
        //从后往前，找到逆序递增的位置，就是要交换的数字
        while (index >= 0 && nums[index + 1] <= nums[index]) {
            index--;
        }
        if (index >= 0) {
            int j = nums.length - 1;
            //找到已近遍历过的逆序区域中仅仅比nums[index]大的数
            while (j >= 0 && nums[j] <= nums[index]) {
                j--;
            }
            //交换nums[index]和仅仅比nums[index]大的数
            swap(nums, index, j);
        }
        //将遍历过的逆序区域转换为顺序的,i一定是一次交换中较小的数，j一定是一次交换中较大的数，
        // 因此直接交换他们即可
        for (int i = index + 1, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
        return nums;
    }


    /**
     * 交换数组中索引下标为i,j的两个数
     *
     * @param nums 目标数组
     * @param i    待交换数i
     * @param j    待交换数j
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     * 数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果数组不存在中心索引，那么返回 -1.
     * 如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     *
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        int sum = 0, leftSum = 0, rightSum = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                leftSum += nums[i - 1];
            }
            rightSum = sum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <code>
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * <code/>
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int len = -1;
        if (digits == null || (len = digits.length) <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(digits[i]);
        }
        BigInteger bigInteger = new BigInteger(sb.toString());
        //+1
        String newDigits = String.valueOf(bigInteger.add(BigInteger.ONE));
        char[] ch = newDigits.toCharArray();
        int[] array = new int[ch.length];
        for (int i = 0; i < ch.length; i++) {
            array[i] = Integer.parseInt(ch[i] + "");
        }
        return array;
    }


    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     * <p>
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     * <p>
     * 如果是，则返回最大元素的索引，否则返回-1
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) <= 2) {
            return -1;
        }

        int first = 0, second = 0, index = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] > first) {
                //联动更新
                second = first;
                first = nums[i];
                index = i;
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }
        return first >= 2 * second ? index : -1;
    }

    /**
     * 对角线遍历一个二维数组
     * <pre>
     * 输入:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * <p>
     * 输出:  [1,2,4,7,5,3,6,8,9]
     * 解释：
     * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/diagonal_traverse.png">
     * </pre>
     *
     * @param matrix
     */
    public void findDiagonalOrder(int[][] matrix) {
        int m = 0;
        if (matrix == null || (m = matrix.length) <= 0) {
            return;
        }
        int n = matrix[0].length;
        int row = 0, col = 0;
        int[] result = new int[n * m];
        for (int i = 0; i < result.length; i++) {
            result[i] = matrix[row][col];
            int indexSum = row + col;
            if ((indexSum >> 1) << 1 == indexSum) {
                if (col == n - 1) {
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else {
                if (row == m - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }
    }


    /**
     * 顺时针打印二维数组
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * <pre>
     *输入:
     *     [
     *      [ 1, 2, 3 ],
     *      [ 4, 5, 6 ],
     *      [ 7, 8, 9 ]
     *     ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * </pre>
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ans;
        }
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) {
                ans.add(matrix[r1][c]);
            }
            for (int r = r1 + 1; r <= r2; r++) {
                ans.add(matrix[r][c2]);
            }
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) {
                    ans.add(matrix[r2][c]);
                }
                for (int r = r2; r > r1; r--) {
                    ans.add(matrix[r][c1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    /**
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     * <p>
     * 说明:
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素
     * 示例:
     * <pre>
     * <code>
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     * <code/>
     * </pre>
     *
     * @param numbers 排序数组
     * @param target  目标数字大小
     * @return 返回下标值
     */
    public int[] twoSumEqualsTarget(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        //由于是排序的，因此使用两个指针从两端向中间夹逼是可行的
        int left = 0, right = numbers.length - 1;
        int sum = 0;
        while (left < right) {
            sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    /**
     * 移动数组中间的所有0 到数组末尾
     *
     * <img src="https://pic.leetcode-cn.com/36d1ac5d689101cbf9947465e94753c626eab7fcb736ae2175f5d87ebc85fdf0-283_2.gif"/>
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int len;
        if(nums==null||(len=nums.length)==0){
            return;
        }
        int j=0;
        for(int i=0;i<len;i++){
            if(nums[i]!=0){
                int t=nums[i];
                nums[i]=nums[j];
                nums[j++]=t;
            }
        }
    }

}
