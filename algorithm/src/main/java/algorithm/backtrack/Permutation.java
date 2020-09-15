package algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/08/09 22:20
 */
public class Permutation {

    /**
     * 给定一个字符串，输出每个有每个字符组成的全排列，例如：
     * <pre>
     *   输入：123
     *   输出：123 132 213 231 321 312
     *
     * </pre>
     *
     * @param str
     * @return
     */
    public static List<List<Character>> permute(String str) {
        List<Character> list = new ArrayList<>();
        if (str == null) return null;
        char[] chars = str.toCharArray();
        boolean[] used = new boolean[chars.length];
        List<List<Character>> ans = new ArrayList<>();
        dfs(chars, used, 0, ans, list);
        return ans;
    }

    /**
     * 回溯的模板：
     * <pre>
     * result[]
     * void  backtrack(选择列表，路径){
     *   if 满足条件:
     *      //这里一般就是对结果筛选
     *      result.add(路径)
     *      return;
     *
     *   for 选择 in 选择列表:
     *      做选择
     *      backtrack(选择列表，路径)
     *      撤销选择
     *  </pre>
     */
    public static void dfs(char[] chars, boolean[] used, int depth, List<List<Character>> ans, List<Character> list) {
        if (depth == chars.length) {
            //这里需要将list父复制一份
            ans.add(new ArrayList<>(list));
            return;
        }
        //dfs
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {
                //选择
                list.add(chars[i]);
                used[i] = true;
                System.out.println("递归之前："+list.toString());
                //选择列表  是否使用  路径  最总答案 本次答案
                dfs(chars, used, depth + 1, ans, list);
                //撤销选择
                list.remove(list.size()-1);
                used[i] = false;
                System.out.println("递归之后："+list.toString());

            }
        }
    }

/*
    private static void backtrack(char[] array, int start, List<String> list) {
        if (start == array.length - 1) {
            list.add(new String(array));
        }
        //全排列，寻找任何结果
        for (int i = start; i < array.length; i++) {
            if (isSwap(array, start, i)) {
                char t = array[i];
                array[i] = array[start];
                array[start] = t;
                backtrack(array, start + 1, list);
                //恢复现场
                t = array[i];
                array[i] = array[start];
                array[start] = t;
            }
        }
    }
*/

    /*private static boolean isSwap(char[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == array[end]) {
                return false;
            }
        }
        return true;
    }
*/

    public static void main(String[] args) {
        permute("123").forEach(obj -> System.out.print(obj + " "));
    }

}
