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
    public static List<String> permute(String str) {
        List<String> list = new ArrayList<>();
        if (str == null) return null;
        backtrack(str.toCharArray(), 0, list);
        return list;
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

    private static boolean isSwap(char[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == array[end]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        permute("123").forEach(obj -> System.out.print(obj + " "));
    }

}
