package array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h4>力扣40. 组合总和 II</h4>
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/06 23:19
 */
public class CombinationSumⅡ {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int length;
        if (candidates == null || (length = candidates.length) == 0) {
            return res;
        }
        //先将数组排序
        Arrays.sort(candidates);
        dfs(candidates, length, 0, target, new ArrayDeque<>(), res);
        return res;
    }

    private void dfs(int[] candidates, int length, int begin, int target,
                     ArrayDeque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            if (path != null && path.size() != 0) {
                res.add(new ArrayList<>(path));
            }
        }
        for (int i = begin; i < length; i++) {
            //大剪枝
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            //这里begin=i+1 是和 之前39题的区别 begin=i
            dfs(candidates, length, i+1, target - candidates[i], path, res);
            path.removeLast();
        }

    }

}
