package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例:
 * <pre>
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/24 23:59
 */
public class 插入区间 {

    /**
     * 这个题和Lettcode 56题的基本思路一样，详细原理参考56题的题解.
     * 不过需要注意的是，此题需要新插入一个数组，这个也比较简单，就是模拟插入排序，
     * 按照数组的左区间的值将新数组插入原数组即可
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }
        if(intervals==null||intervals.length==0){
            return new int[][]{newInterval};
        }
        List<int[]> ans = new ArrayList<>();
        boolean inserted = false;
        for (int i = 0; i < intervals.length; ) {
            if (!inserted && intervals[i][0] >= newInterval[0]) {
                ans.add(new int[]{newInterval[0], newInterval[1]});
                inserted = true;
            } else {
                ans.add(intervals[i]);
                i++;
            }
        }
        if(!inserted){
            ans.add(new int[]{newInterval[0], newInterval[1]});
        }
        //ans中的数字按做区间的数字已经有序了，之后覆盖检查合并
        intervals = ans.toArray(new int[0][]);
        //复用ArrayList
        ans.clear();
        int i = 0;
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            while (i < intervals.length - 1 && intervals[i + 1][0] <= end) {
                i++;
                end = Math.max(intervals[i][1], end);
            }
            ans.add(new int[]{start, end});
            i++;
        }
        return ans.toArray(new int[0][]);
    }


    public static void main(String[] args) {
        for (int[] ints : insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
