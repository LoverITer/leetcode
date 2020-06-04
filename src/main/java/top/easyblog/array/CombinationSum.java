package top.easyblog.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h2>力扣第39题<h2/>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/01 23:35
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        doSearch(0, 0, target, candidates, new ArrayList<>(), res);
        return res;
    }

    private void doSearch(int sum, int pos, int target, int[] candidates, ArrayList<Integer> integers, List<List<Integer>> res) {
        if (sum == target) {
            //找到一条路径符合
            res.add(integers);
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            integers.add(candidates[i]);
            //递归
            doSearch(sum + candidates[i], i, target, candidates, integers, res);
            //条件不满足的时候剪枝
            integers.remove(integers.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSum().combinationSum(new int[]{2,3,6,7}, 7);
        lists.forEach(list-> list.forEach(System.out::println));
    }

}
