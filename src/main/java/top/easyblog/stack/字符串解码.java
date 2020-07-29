package top.easyblog.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <pre>
 * 示例 1:
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * </pre>
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/11 23:30
 */
public class 字符串解码 {

    /**
     * 充分使用桟这种数据结构：构造两个桟，一个存放字串，一个存放数字，然后遍历给定的字符串
     * 当下标为i的字符是数字的时候转换并存储在time变量，当下标为i的字符是字母就拼接到ans变量，
     * 当下标为i的字符是{@code '['}时就把当前time和ans入栈，并且重置这两个变量继续上面过程，
     * 当下标为i的字符是{@code ']'}时就出栈
     *
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        //存放字符串
        Deque<String> strStack = new ArrayDeque<>();
        //存放数字
        Deque<Integer> numStack = new ArrayDeque<>();
        //存放结果
        StringBuilder ans = new StringBuilder();
        int time = 0;
        for (char c : s.toCharArray()) {
            if ('0' <= c && c <= '9') {
                //计算数字结果 ，比如形如12[aaa]34[ccc]的字串
                time = time * 10 + Integer.parseInt(c + "");
            } else if ('[' == c) {
                numStack.push(time);
                strStack.push(ans.toString());
                time = 0;
                ans = new StringBuilder();
            } else if (']' == c) {
                StringBuilder tmp = new StringBuilder();
                int nums = numStack.pop();
                for (int i = 0; i < nums; i++) {
                    tmp.append(ans);
                }
                ans = new StringBuilder(strStack.pop() + tmp);
            } else {
                //字母就字节拼接上
                ans.append(c);
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "10[a2[c]]";
        System.out.println(decodeString(s));
    }

}
