package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** leetcode 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/22 23:28
 */
public class 合并区间 {

    /**
     * 解决思路：对元素组的做区间的值排序，之后使用两个指针，左指针资者区间开始的地方，右指针向后比遍历
     * 如果发现另一个区间中无论左右的数小于等于这个做区间的数，那就说明出现了区间覆盖现象，就应该合并
     * <pre>
     *示意：
     *              i=0
     *              |
     * 开始：    [[1,3],[2,6],[8,10],[15,18]]
     *            | |
     *       start  end    end>=intervals[i+1][0] 成立，说明这里需要合并
     *
     *
     *       接着往下找：
     *                   i=1
     *                   |
     *          [[1,3],[2,6],[8,10],[15,18]]
     *            |       |
     *           start    end end>=intervals[i+1][0]不成立，说明之后没有覆盖，合并[1,3]和[2,6]
     *
     *       合并之后：
     *                         i=2
     *                          |
     *          [[1,3],[2,6],[8,10],[15,18]]
     *                        | |
     *                    start end
     * </pre>
     * @param intervals
     * @return
     */
    public  static int[][] merge(int[][] intervals) {
        List<int[]> ans=new ArrayList<>();
        if(intervals==null||intervals.length<=1){
            return intervals;
        }
        //对每个区间的左边升序排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int i=0;
        while(i<intervals.length){
            //开始
            int start=intervals[i][0];
            //结束
            int end=intervals[i][1];
            //右指针向后遍历
            while(i<intervals.length-1&& end>=intervals[i+1][0]){
                i++;
                end=Math.max(end,intervals[i][1]);
            }
            //添加区间
            ans.add(new int[]{start,end});
            //继续向下遍历
            i++;
        }
        return ans.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        for (int[] arr:merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}))
        System.out.println(Arrays.toString(arr));
    }

}
