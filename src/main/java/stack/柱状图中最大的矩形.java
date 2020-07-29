package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调栈的应用：
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <pre>
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @see 下一个更大元素I
 * @since ：2020/07/22 10:29
 */
public class 柱状图中最大的矩形 {

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        //桟：用于存储高度下标
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            //当前高度：当i超出范围之后在末尾增加一个高度为0的柱体
            int curr = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && curr < heights[stack.peek()]) {
                //计算面积: 高*宽   高=当前栈顶柱体的高度    宽=i到当前栈顶元素的下标的距离
                int h = heights[stack.pop()];
                int w = !stack.isEmpty() ? i - stack.peek() - 1 : i;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        /*                  8
                         7  _
                    6   _  | |
                    _  | | | |
                   |\| |\| |\|
                   |\| |\| |\|
                   |\| |\| |\|  3
            2      |\| |\| |\|  _
            _   1  |\| |\| |\| | |
           | |  _  |\| |\| |\| | |
           |_| |_| |\| |\| |\| |_|
           最大面积：6*3
         */
        System.out.println(largestRectangleArea(new int[]{2, 1, 6, 7, 8, 3}));
    }

}
