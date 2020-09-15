package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/31 21:46
 */
public class 两数之和 {

    /**
     * 一遍Hash,将遍历过的数放进Hash表用于保存它的下标映射，
     * 往后遍历发现map.containsKey(target - nums[i])，则直接返回map.get(target - nums[i],i)
     * @param nums   整数数组
     * @param target 目标值
     * @return
     */
    public static  int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] s = line.split(" ");
        int[] nums=new int[s.length];
        for(int i=0;i< s.length;i++){
            nums[i]=Integer.parseInt(s[i]);
        }
        int target=sc.nextInt();
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

}
