package string;

import java.util.LinkedList;

/**
 * <h5>71. 简化路径</h5>
 * <pre>
 * 示例 1：
 *
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 *
 * 示例 2：
 *
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 *
 * 示例 3：
 *
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 *
 * </pre>
 *
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/19 00:33
 */
public class 简化路径 {

    /**
     * 使用一个桟来辅助，先把路径按“/”分隔开，之后遍历数组，遇到“..”就把栈顶元素出栈（桟不空）
     * 遇到“.”或空字符串跳过，其余的字符一律入栈即可
     *
     * @param path
     * @return
     */
    public static String simplifyPath(String path) {
        if (path == null) {
            return "";
        }
        LinkedList<String> stack = new LinkedList<>();
        for (String str : path.split("/")) {
            if (str.equals("..")) {
                //桟非空，并且访问的到..，表示要回到上一级目录，那就将上一级目录出栈
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!"".equals(str) && !str.equals(".")) {
                // 如果数组非空并且当前元素不是 “.” 说明当前元素是路径信息，要入栈
                stack.push(str);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder ans = new StringBuilder();
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans.append("/").append(stack.get(i));
        }
        return ans.toString();

    }


    public static void main(String[] args) {
        System.out.println(simplifyPath("/home//foo"));
    }

}
