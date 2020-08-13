package string;

import java.util.Stack;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/06/30 22:41
 */
public class 反转字符串 {

    /**
     * 反转字符串Ⅰ
     * 不要使用而外的空间，必须原地修改输入的数组，使用 O(1) 的额外空间解决这一问题
     * <pre>
     * 示例 ：
     *
     * 输入： "hello"
     * 输出： "olleh"
     * </pre>
     *
     * @param str 待反转字符串
     * @return 反转后的字符串
     */
    public static String reverseString(String str) {
        if (str.isEmpty()) {
            return "";
        }
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        char temp;
        while (left < right) {
            temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
        return new String(chars);
    }

    /**
     * 541. 反转字符串 II
     * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
     * <p>
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *  
     * <p>
     * 示例:
     * <pre>
     * 约定：
     * 1. 该字符串只包含小写英文字母。
     * 2. 给定字符串的长度和 k 在 [1, 10000] 范围内。
     * 输入: s = "abcdefg", k = 2
     * 输出: "bacdfeg"
     * </pre>
     *
     * @param s
     * @param k
     * @return
     */
    public static String reverseString(String s, int k) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        StringBuilder ans = new StringBuilder();
        int low = 0, high = 0;
        final int reverseLen = 2 * k;
        for (; low <= high && high < s.length(); high++) {
            //每2k个字符截取后反转
            if ((high - low + 1) == reverseLen) {
                ans.append(reverseString(s.substring(low, low + k)));
                ans.append(s, low + k, high + 1);
                low = high + 1;
            }
        }
        int subLen = s.length() - low;
        //处理剩余的字符串
        if (subLen < k) {
            //剩余的k个字符全部反转
            ans.append(reverseString(s.substring(low)));
        } else if (k <= subLen && reverseLen > subLen) {
            //剩余的k~2k个字符只反转k个
            ans.append(reverseString(s.substring(low, low + k)));
            ans.append(s.substring(low + k));
        }
        return ans.toString();
    }


    /**
     * 557. 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * <pre>
     *
     * 示例 1:
     *
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc" 
     * </pre>
     *
     * @param s 原字符串
     * @return
     */
    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] ss = s.trim().replaceAll("\\s+", " ").split(" ");
        Stack<String> stack = new Stack<>();
        for (String str : ss) {
            stack.push(str);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //System.out.println(reverseString("hello"));
        System.out.println(reverseString("abcdefg", 2));
    }

}
